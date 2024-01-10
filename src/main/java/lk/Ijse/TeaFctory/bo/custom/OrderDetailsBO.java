package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsBO extends SuperBO {
     boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException ;



    }
