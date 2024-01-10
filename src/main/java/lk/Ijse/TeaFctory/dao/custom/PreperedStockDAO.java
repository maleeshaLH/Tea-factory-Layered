package lk.Ijse.TeaFctory.dao.custom;

import lk.Ijse.TeaFctory.dao.CrudDAO;
import lk.Ijse.TeaFctory.dto.tm.CartTm;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.entity.PreparedStock;

import java.sql.SQLException;
import java.util.List;

public interface PreperedStockDAO extends CrudDAO<PreparedStock> {
     boolean updateStock(List<CartTm> tmList) throws SQLException ;
      boolean updatePreparedStock(List<StockOrderTm> tmList) throws SQLException ;


    }
