package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;

import java.sql.SQLException;
import java.util.List;

public interface SupplierOrderDetailsBO extends SuperBO {
      boolean saveOrderDetail(String s_id, List<SupplierOrderTm> tmList) throws SQLException ;

    }
