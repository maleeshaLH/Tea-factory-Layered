package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;

import java.sql.SQLException;

public interface StockOrderBO extends SuperBO {
    public boolean saveStockOrder(String p_id, String rs_id, String description, int unitPrice, int weight, int qty) throws SQLException ;

    }
