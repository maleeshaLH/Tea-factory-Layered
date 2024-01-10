package lk.Ijse.TeaFctory.dao.custom.impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.OrderDetailsDAO;
import lk.Ijse.TeaFctory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    public boolean saveOrder(String orderId, List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if(!saveOrderDetail(orderId, cartTm)) {
                return false;
            }
        }
        return true;

    }
    private boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException {

       return SQLUtil.execute("INSERT INTO order_details VALUES(?, ?, ?, ? ,?)",
                orderId,cartTm.getP_id(),cartTm.getUnitPrice(),cartTm.getWeight(),cartTm.getQty());
    }

    @Override
    public boolean save(MysqlxCrud.Order dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<MysqlxCrud.Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(MysqlxCrud.Order dto) throws SQLException {
        return false;
    }

    @Override
    public MysqlxCrud.Order search(String id) throws SQLException {
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
