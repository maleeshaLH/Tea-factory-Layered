package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.StockOrderDAO;
import lk.Ijse.TeaFctory.entity.StockOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockOrderDAOImpl implements StockOrderDAO {
    public boolean save(StockOrder entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO stock_details VALUES(?, ?, ? ,? ,? ,?)",
                entity.getP_id(),entity.getRs_id(),entity.getDescription(),entity.getUnitPrice(),
                entity.getWeight(),entity.getQty());
    }

    @Override
    public ArrayList<StockOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(StockOrder dto) throws SQLException {
        return false;
    }

    @Override
    public StockOrder search(String id) throws SQLException {
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
