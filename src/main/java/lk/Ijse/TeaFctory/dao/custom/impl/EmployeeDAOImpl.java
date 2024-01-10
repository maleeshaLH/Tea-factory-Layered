package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.EmployeeDAO;
import lk.Ijse.TeaFctory.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  boolean delete(String emp_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE emp_id =?",
                emp_id);



    }

    public ArrayList<Employee> loadAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> allEmployee = new ArrayList<>();

        while (rst.next()){
            Employee entity = new Employee(
                    rst.getString("emp_id"),
                    rst.getString("first_name"),
                    rst.getString("last_name"),
                    rst.getString("nic"),
                    rst.getString("city"),
                    rst.getString("Contact_no"));
            allEmployee.add(entity);
        }
        return allEmployee;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }


    public boolean save(Employee entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",
                entity.getEmp_id(),entity.getFirst_name(),entity.getLast_name(),entity.getNic(),
                entity.getCity(),entity.getContact_no());

    }

    public Employee search(String emp_id) throws SQLException {
        ResultSet rst =  SQLUtil.execute("SELECT * FROM employee WHERE emp_id = ?");
        rst.next();
        return new Employee(emp_id,rst.getString("first_name"),rst.getString("last_name"),
                rst.getString("nic"),rst.getString("city"),rst.getString("tel"));

    }

    public boolean update(Employee entity) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET first_name = ?,last_name = ?,nic = ?,city = ?,Contact_no =? WHERE emp_id= ?",
           entity.getFirst_name(),entity.getLast_name(),entity.getNic(),entity.getCity(),entity.getContact_no(),entity.getEmp_id());
    }



    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> allEmployee = new ArrayList<>();

        while (rst.next()){
            Employee entity = new Employee(
                    rst.getString("emp_id"),
                    rst.getString("first_name"),
                    rst.getString("last_name"),
                    rst.getString("nic"),
                    rst.getString("city"),
                    rst.getString("Contact_no"));
            allEmployee.add(entity);
        }
        return allEmployee;

    }
}
