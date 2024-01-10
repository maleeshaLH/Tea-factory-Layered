package lk.Ijse.TeaFctory.dao.custom;

import lk.Ijse.TeaFctory.dao.CrudDAO;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;
import lk.Ijse.TeaFctory.entity.RawStock;

import java.sql.SQLException;
import java.util.List;

public interface RawStockDAO extends CrudDAO<RawStock> {
     boolean update(List<StockOrderTm> tmList) throws SQLException ;
      boolean updateStock(List<SupplierOrderTm> tmList) throws SQLException ;


    }
