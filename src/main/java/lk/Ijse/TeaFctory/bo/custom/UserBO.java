package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.UserDto;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
      boolean saveUser(UserDto dto) throws SQLException ;
      boolean saveUser(String username, String password) ;


    }
