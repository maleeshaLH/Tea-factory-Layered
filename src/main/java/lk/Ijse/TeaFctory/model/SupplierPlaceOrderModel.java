package lk.Ijse.TeaFctory.model;

import lk.Ijse.TeaFctory.bo.custom.RawStockBO;
import lk.Ijse.TeaFctory.bo.custom.SupplierOrderBO;
import lk.Ijse.TeaFctory.bo.custom.SupplierOrderDetailsBO;
import lk.Ijse.TeaFctory.bo.custom.impl.RawStockBOIMpl;
import lk.Ijse.TeaFctory.bo.custom.impl.SupplierOrderBOImpl;
import lk.Ijse.TeaFctory.bo.custom.impl.SupplierOrderDetailsBOImpl;
import lk.Ijse.TeaFctory.db.DbConnection;
import lk.Ijse.TeaFctory.dto.SupplierOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierPlaceOrderModel {
    RawStockBO rawStockBO = new RawStockBOIMpl();
    SupplierOrderDetailsBO supplierOrderDetailsBO =new SupplierOrderDetailsBOImpl();
    SupplierOrderBO supplierOrderBO = new SupplierOrderBOImpl();


    public boolean supplierOrder(SupplierOrderDto supplierOrderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = supplierOrderBO.saveSupplierOrder(
                    supplierOrderDto.getS_id(),
                    supplierOrderDto.getSu_id(),
                    supplierOrderDto.getDescription(),
                    supplierOrderDto.getUnit_price(),
                    supplierOrderDto.getWeight(),
                    supplierOrderDto.getQty()
            );
            if (isOrderSaved) {
                boolean isUpdated = rawStockBO.updateStock(supplierOrderDto.getTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = supplierOrderDetailsBO.saveOrderDetail(supplierOrderDto.getS_id(),supplierOrderDto.getTmList());
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



