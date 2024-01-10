package lk.Ijse.TeaFctory.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
     boolean save(T dto) throws SQLException ;
     ArrayList<T> getAll() throws SQLException ;

     boolean update(T dto) throws SQLException ;
     T search(String id) throws SQLException ;

     boolean delete(String id) throws SQLException ;
     String generateId()throws SQLException;

}
