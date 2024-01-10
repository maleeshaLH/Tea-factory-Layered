package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.OrderDetailsBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.OrderDetailsDAO;
import lk.Ijse.TeaFctory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.ORDERDETAILS);
    public boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException {
        return orderDetailsDAO.saveOrder(orderId,tmList);

    }

}
