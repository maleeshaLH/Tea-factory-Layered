package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.UserDAO;
import lk.Ijse.TeaFctory.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    public  boolean save(User entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO user VALUES (?,?,?)",
                entity.getUsername(),entity.getEmail(),entity.getPassword());
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(User dto) throws SQLException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    public  boolean saveUser(String username, String password) {
        try {

           ResultSet resultSet = SQLUtil.execute("SELECT password FROM user WHERE username = ?",username);

            if (resultSet.next()){
                if (password.equals(resultSet.getString(1))){
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
