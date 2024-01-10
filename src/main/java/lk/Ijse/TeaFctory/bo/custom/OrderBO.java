package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderBO extends SuperBO {
     String generateNextOrderId() throws SQLException ;

     boolean saveOrder(String orderId, String cusId, LocalDate date) throws SQLException ;
     }




