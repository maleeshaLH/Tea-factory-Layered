package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.PreparedStockBO;
import lk.Ijse.TeaFctory.bo.custom.RawStockBO;
import lk.Ijse.TeaFctory.bo.custom.StockOrderBO;
import lk.Ijse.TeaFctory.bo.custom.StockPlaceOrderBO;
import lk.Ijse.TeaFctory.db.DbConnection;
import lk.Ijse.TeaFctory.dto.StockOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class StockPlaceOrderBOImpl implements StockPlaceOrderBO {
    PreparedStockBO preparedStockBO = new PreparedStockBOImpl();
    RawStockBO rawStockBO = new RawStockBOIMpl();
    StockOrderBO stockOrderBO = new StockOrderBOImpl();

    public boolean stockOrder(StockOrderDto stockOrderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = stockOrderBO.saveStockOrder(
                    stockOrderDto.getP_id(),
                    stockOrderDto.getRs_id(),
                    stockOrderDto.getDescription(),
                    stockOrderDto.getUnitPrice(),
                    stockOrderDto.getWeight(),
                    stockOrderDto.getQty()
            );
            if (isOrderSaved) {
                boolean isUpdated = preparedStockBO.updatePreparedStock(stockOrderDto.getTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = rawStockBO.update(stockOrderDto.getTmList());
                    if(isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;

    }
}
