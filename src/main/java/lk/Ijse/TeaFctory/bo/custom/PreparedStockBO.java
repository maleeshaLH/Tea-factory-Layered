package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.PreparedstockDto;
import lk.Ijse.TeaFctory.dto.tm.CartTm;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PreparedStockBO extends SuperBO {
      boolean updatePreparedStock(List<StockOrderTm> tmList) throws SQLException ;
      boolean saveStock(PreparedstockDto dto) throws SQLException ;
     PreparedstockDto searchStock(String id) throws SQLException ;
     boolean deleteStock(String id) throws SQLException ;
    ArrayList<PreparedstockDto> getAllStock() throws SQLException ;
     boolean updateStock(List<CartTm> tmList) throws SQLException ;
     boolean updateP_Stock(PreparedstockDto dto) throws SQLException;


    }
