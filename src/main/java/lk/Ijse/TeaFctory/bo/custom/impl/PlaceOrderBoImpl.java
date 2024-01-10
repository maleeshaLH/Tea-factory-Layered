package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.OrderBO;
import lk.Ijse.TeaFctory.bo.custom.OrderDetailsBO;
import lk.Ijse.TeaFctory.bo.custom.PlaceOrderBO;
import lk.Ijse.TeaFctory.bo.custom.PreparedStockBO;
import lk.Ijse.TeaFctory.db.DbConnection;
import lk.Ijse.TeaFctory.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBoImpl implements PlaceOrderBO {

    PreparedStockBO preparedStockBO =new PreparedStockBOImpl();
    OrderBO orderBO = new OrderBOImpl();
    OrderDetailsBO orderDetailsBO = new OrderDetailsBOImpl();


    public boolean placeOrder(PlaceOrderDto pDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            //  boolean isOrderSaved = orderBO.saveOrder(new PlaceOrderDto(pDto.getOrderId(), pDto.getCusId(), pDto.getDate());

            boolean isOrderSaved = orderBO.saveOrder(pDto.getOrderId(), pDto.getCusId(), pDto.getDate());
            if (isOrderSaved) {
                boolean isUpdated = preparedStockBO.updateStock(pDto.getTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = orderDetailsBO.saveOrderDetail(pDto.getOrderId(), pDto.getTmList());
                    if(isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            assert connection != null;
            connection.rollback();
        } finally {
            assert connection != null;
            connection.setAutoCommit(true);
        }
        return result;
    }
}
