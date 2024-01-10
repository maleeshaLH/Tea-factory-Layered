package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.OrderBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.OrderDAO;
import lk.Ijse.TeaFctory.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO =(OrderDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.ORDER);
    public String generateNextOrderId() throws SQLException {
        return orderDAO.generateId();

    }
    private String splitOrderId(String currentOrderId) {    //O008
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "O001";
    }

    public boolean saveOrder(String orderId, String cusId, LocalDate date) throws SQLException {

        return orderDAO.save(new Order(orderId,cusId,date));

    }
}
