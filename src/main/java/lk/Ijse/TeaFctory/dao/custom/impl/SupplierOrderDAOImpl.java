package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.SupplierOrderDAO;
import lk.Ijse.TeaFctory.entity.SupplierOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public  boolean save(SupplierOrder entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO supplier_order VALUES(?, ?, ?,?,?,?)",
                entity.getS_id(),entity.getSu_id(),entity.getDescription(),entity.getUnit_price(),
                entity.getWeight(),entity.getQty());
    }

    @Override
    public ArrayList<SupplierOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(SupplierOrder dto) throws SQLException {
        return false;
    }

    @Override
    public SupplierOrder search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {

       ResultSet resultSet =  SQLUtil.execute("SELECT s_id FROM supplier_order ORDER BY s_id DESC LIMIT 1");
       resultSet.next();

        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);

    }

    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("Su");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "Su001";

    }
}
