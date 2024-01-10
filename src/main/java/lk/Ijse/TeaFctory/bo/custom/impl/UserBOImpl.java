package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.UserBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.UserDAO;
import lk.Ijse.TeaFctory.dto.UserDto;
import lk.Ijse.TeaFctory.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

        UserDAO userDAO =(UserDAO) DAOFcatory.getDaoFcatory().
                getDAO(DAOFcatory.DAOTypes.USER);
    public  boolean saveUser(UserDto dto) throws SQLException {

        return userDAO.save(new User(dto.getUsername(),dto.getEmail(),dto.getPassword()));
    }

    public  boolean saveUser(String username, String password) {
       return userDAO.saveUser(username, password);
    }
}
