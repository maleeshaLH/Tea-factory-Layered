package lk.Ijse.TeaFctory.dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.Ijse.TeaFctory.dao.CrudDAO;
import lk.Ijse.TeaFctory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<MysqlxCrud.Order> {
    public boolean saveOrder(String orderId, List<CartTm> tmList) throws SQLException ;

    }
