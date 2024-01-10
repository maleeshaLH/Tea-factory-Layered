package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.SalaryDto;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {
     boolean saveSalary(SalaryDto dto) throws SQLException ;
      List<SalaryDto> getAllCustomers() throws SQLException ;
      String generateNextOrderIds() throws SQLException ;

}
