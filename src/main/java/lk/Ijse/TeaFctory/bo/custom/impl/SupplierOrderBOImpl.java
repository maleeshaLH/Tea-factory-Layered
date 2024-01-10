package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.SupplierOrderBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.SupplierOrderDAO;
import lk.Ijse.TeaFctory.entity.SupplierOrder;

import java.sql.SQLException;

public class SupplierOrderBOImpl implements SupplierOrderBO {
   SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.SUPPLIERORDER);
    public  boolean saveSupplierOrder(String s_id, String su_id, String description, Integer unit_price, Integer weight, Integer qty) throws SQLException {
        return supplierOrderDAO.save(new SupplierOrder(s_id,su_id,description,unit_price,weight,qty));

    }

    public String generateNextOrderId() throws SQLException {
       return supplierOrderDAO.generateId();

    }


}
