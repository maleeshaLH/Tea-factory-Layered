package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.RawStockDto;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RawStockBO extends SuperBO {


    String generateId() throws SQLException;

    boolean saveRawStock(RawStockDto dto) throws SQLException;

    ArrayList<RawStockDto> getAllRawStock() throws SQLException;

    boolean deleteRawStock(String id) throws SQLException;

    boolean updateRawStock(RawStockDto dto) throws SQLException;

    RawStockDto searchRawStock(String id) throws SQLException;
     boolean update(List<StockOrderTm> tmList) throws SQLException ;
      boolean updateStock(List<SupplierOrderTm> tmList) throws SQLException ;


    }
