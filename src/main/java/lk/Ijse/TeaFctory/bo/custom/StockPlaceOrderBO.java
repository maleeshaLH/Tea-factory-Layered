package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.StockOrderDto;

import java.sql.SQLException;

public interface StockPlaceOrderBO extends SuperBO {
     boolean stockOrder(StockOrderDto stockOrderDto) throws SQLException ;

    }
