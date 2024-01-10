package lk.Ijse.TeaFctory.dao.custom;

import lk.Ijse.TeaFctory.dao.CrudDAO;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;
import lk.Ijse.TeaFctory.entity.SupplierOrder;

import java.sql.SQLException;
import java.util.List;

public interface SupplierOrderDetailsDAO extends CrudDAO<SupplierOrder> {
     boolean saveOrderDetail(String s_id, List<SupplierOrderTm> tmList) throws SQLException ;

    }
