package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;

import java.sql.SQLException;

public interface SupplierOrderBO extends SuperBO {
      boolean saveSupplierOrder(String s_id, String su_id, String description, Integer unit_price, Integer weight, Integer qty) throws SQLException ;
      String generateNextOrderId() throws SQLException ;


    }
