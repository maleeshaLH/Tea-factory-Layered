package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.SupplierOrderDetailsBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.SupplierOrderDetailsDAO;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;

import java.sql.SQLException;
import java.util.List;

public class SupplierOrderDetailsBOImpl implements SupplierOrderDetailsBO {
    SupplierOrderDetailsDAO supplierOrderDetailsDAO = (SupplierOrderDetailsDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.SUPPLIERORDERDETAILS);

    public  boolean saveOrderDetail(String s_id, List<SupplierOrderTm> tmList) throws SQLException {
        return supplierOrderDetailsDAO.saveOrderDetail(s_id,tmList);

    }

}
