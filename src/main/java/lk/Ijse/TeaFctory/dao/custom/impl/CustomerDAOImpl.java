package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.CustomerDAO;
import lk.Ijse.TeaFctory.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
   @Override
   public boolean save(Customer entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ? , ?)",
                entity.getId(),entity.getFirst_name(),entity.getLast_name(),entity.getTelephone_number(),
                entity.getAddress(),entity.getCity());

    }

    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (rst.next()){
            Customer entity = new Customer(
                    rst.getString("id"),
                    rst.getString("first_name"),
                    rst.getString("last_name"),
                    rst.getString("telephone_number"),
                    rst.getString("address"),
                    rst.getString("city"));
            allCustomer.add(entity);
        }
        return allCustomer;

    }

    @Override
    public boolean update(Customer entity) throws SQLException {
       return SQLUtil.execute("UPDATE customer SET first_name = ?,last_name = ?, telephone_number = ?, address = ? ,city = ? WHERE id = ?",
               entity.getId(),entity.getFirst_name(),entity.getLast_name(),entity.getTelephone_number(),entity.getAddress(),entity.getCity());

    }

    @Override
    public Customer search(String id) throws SQLException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE id = ?",id);
        rst.next();

        return new Customer(id +"",rst.getString("first_name"),rst.getString("last_name"),
                rst.getString("telephone_number"),rst.getString("address"),rst.getString("city"));
    }


    @Override
    public boolean delete(String id) throws SQLException {
       return SQLUtil.execute("DELETE FROM customer WHERE id = ?",id);

    }


    @Override
    public String generateId() throws SQLException {
        return null;
    }
}
