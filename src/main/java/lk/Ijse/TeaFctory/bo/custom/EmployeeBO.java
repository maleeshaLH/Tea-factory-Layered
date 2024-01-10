package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
      boolean deleteEmployee(String emp_id) throws SQLException ;
      ArrayList<EmployeeDto> loadAllEmployee() throws SQLException ;
      boolean saveEmployee(EmployeeDto dto) throws SQLException ;
      EmployeeDto searchEmployee(String emp_id) throws SQLException ;
      boolean updateEmployee(EmployeeDto dto) throws SQLException ;
      ArrayList<EmployeeDto> getAllEmployee() throws SQLException;

}
