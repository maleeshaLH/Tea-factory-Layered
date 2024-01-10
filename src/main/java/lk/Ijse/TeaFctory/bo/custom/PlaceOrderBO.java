package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.PlaceOrderDto;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
     boolean placeOrder(PlaceOrderDto pDto) throws SQLException ;

    }
