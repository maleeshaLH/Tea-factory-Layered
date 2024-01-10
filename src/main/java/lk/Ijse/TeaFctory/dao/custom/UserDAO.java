package lk.Ijse.TeaFctory.dao.custom;

import lk.Ijse.TeaFctory.dao.CrudDAO;
import lk.Ijse.TeaFctory.entity.User;

public interface UserDAO extends CrudDAO<User> {
      boolean saveUser(String username, String password) ;

    }
