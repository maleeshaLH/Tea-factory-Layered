package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.SalaryDAO;
import lk.Ijse.TeaFctory.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
   @Override
   public boolean save(Salary entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO salary VALUES(?, ?, ?, ?)",
                entity.getS_id(), entity.getEmp_id(), entity.getDate(), entity.getSalary());

    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");
        ArrayList<Salary> allSalary =new ArrayList<>();

        while (rst.next()){
            Salary salary =new Salary(
                    rst.getString("s_id"),
                    rst.getString("emp_id"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("salary"));
            allSalary.add(salary);
        }
        return allSalary;
    }

    @Override
    public boolean update(Salary dto) throws SQLException {
        return false;
    }

    @Override
    public Salary search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }


    @Override
    public String generateId() throws SQLException {
       ResultSet rst = SQLUtil.execute("SELECT s_id FROM salary ORDER BY s_id DESC LIMIT 1");
        String currentOrderId = null;

        if (rst.next()) {
            currentOrderId = rst.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);
    }
    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("S");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "S00" + id;
        }
        return "S001";

    }
}
