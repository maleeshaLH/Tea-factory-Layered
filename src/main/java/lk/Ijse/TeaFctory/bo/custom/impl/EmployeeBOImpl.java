package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.EmployeeBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.EmployeeDAO;
import lk.Ijse.TeaFctory.dto.EmployeeDto;
import lk.Ijse.TeaFctory.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.EMPLOYEE);


    public  boolean deleteEmployee(String emp_id) throws SQLException {
    return employeeDAO.delete(emp_id);

    }

    public ArrayList<EmployeeDto> loadAllEmployee() throws SQLException {
        ArrayList<Employee> employees =employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee :employees){
           employeeDtos.add( new EmployeeDto(employee.getEmp_id(),employee.getFirst_name(),
                    employee.getLast_name(),employee.getNic(),employee.getCity(),employee.getContact_no()));
        }
        return employeeDtos;
    }



    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO .save(new Employee(dto.getEmp_id(),
                dto.getFirst_name(),dto.getLast_name(),dto.getNic(),
                dto.getCity(),dto.getTel()));

    }

    public EmployeeDto searchEmployee(String emp_id) throws SQLException {

      Employee employee = employeeDAO.search(emp_id);

     return  new EmployeeDto(employee.getEmp_id(),employee.getFirst_name(),
              employee.getLast_name(),employee.getNic(),employee.getCity(),employee.getContact_no());

    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO .update(new Employee(dto.getEmp_id(),
                dto.getFirst_name(),dto.getLast_name(),dto.getNic(),
                dto.getCity(),dto.getTel()));

    }



    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException {
        ArrayList<Employee> employees =employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee :employees){
            employeeDtos.add( new EmployeeDto(employee.getEmp_id(),employee.getFirst_name(),
                    employee.getLast_name(),employee.getNic(),employee.getCity(),employee.getContact_no()));
        }
        return employeeDtos;

    }
}
