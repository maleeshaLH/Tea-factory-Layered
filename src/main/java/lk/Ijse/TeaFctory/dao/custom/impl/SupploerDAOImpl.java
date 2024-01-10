package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.SupplierDAO;
import lk.Ijse.TeaFctory.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupploerDAOImpl implements SupplierDAO {
    public  ArrayList<Supplier> loadAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> allSuppllier = new ArrayList<>();

        while (rst.next()){
            Supplier entity = new Supplier(
                    rst.getString("su_id"),
                    rst.getString("name"),
                    rst.getString("loction"),
                    rst.getString("email"),
                    rst.getString("telephone_number"));
            allSuppllier.add(entity);
        }
        return allSuppllier;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }


    public boolean save(Supplier entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",
                entity.getSu_id(),entity.getName(),entity.getLoction(),
                entity.getEmail(),entity.getTelephone_number());


    }

    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM supplier WHERE su_id =?",id);

    }

    public boolean update(Supplier entity) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET name = ?,loction = ?,email = ?,telephone_number = ? WHERE su_id =?",
                entity.getSu_id(),entity.getName(),entity.getLoction(),
                entity.getEmail(),entity.getTelephone_number());

    }

    public  Supplier search(String id) throws SQLException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM supplier WHERE su_id = ?",id);
        rst.next();
        return new Supplier(id+"",
                rst.getString("name"),
                rst.getString("loction"),
                rst.getString("email"),
                rst.getString("telephone_number"));

    }


    public ArrayList<Supplier> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> allSuppllier = new ArrayList<>();

        while (rst.next()){
            Supplier entity = new Supplier(
                    rst.getString("su_id"),
                    rst.getString("name"),
                    rst.getString("loction"),
                    rst.getString("email"),
                    rst.getString("telephone_number"));
            allSuppllier.add(entity);
        }
        return allSuppllier;


    }


}
