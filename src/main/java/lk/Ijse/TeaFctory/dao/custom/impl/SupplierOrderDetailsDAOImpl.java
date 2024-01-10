package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.custom.SupplierOrderDetailsDAO;
import lk.Ijse.TeaFctory.db.DbConnection;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;
import lk.Ijse.TeaFctory.entity.SupplierOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderDetailsDAOImpl implements SupplierOrderDetailsDAO {
    public  boolean saveOrderDetail(String s_id, List<SupplierOrderTm> tmList) throws SQLException {
        for (SupplierOrderTm supplierOrderTm : tmList) {
            if(!saveOrderDetail(s_id, supplierOrderTm)) {
                return false;
            }
        }
        return true;

    }

    private static boolean saveOrderDetail(String s_id, SupplierOrderTm supplierOrderTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier_order_details VALUES(?, ?, ?, ? )";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, s_id);
        pstm.setString(2, supplierOrderTm.getRs_id());
        pstm.setInt(3, supplierOrderTm.getUnit_price());
        // pstm.setDouble(4, supplierOrderTm.getWeight());
        pstm.setInt(4, supplierOrderTm.getQty());


        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean save(SupplierOrder dto) throws SQLException {
        return false;
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
        return null;
    }
}
