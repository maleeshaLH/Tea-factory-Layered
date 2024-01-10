package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
      ArrayList<SupplierDto> loadAllSupplier() throws SQLException ;
      boolean saveSupplier(SupplierDto dto) throws SQLException ;
      boolean deleteSupplier(String id) throws SQLException ;
      boolean updateSupplier(SupplierDto dto) throws SQLException ;
      SupplierDto searchSupplier(String id) throws SQLException ;
      ArrayList<SupplierDto> getAllSupplier() throws SQLException ;


    }
