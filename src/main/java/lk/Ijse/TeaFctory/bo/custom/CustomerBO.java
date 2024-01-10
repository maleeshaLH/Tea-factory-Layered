package lk.Ijse.TeaFctory.bo.custom;

import lk.Ijse.TeaFctory.bo.SuperBO;
import lk.Ijse.TeaFctory.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
     boolean saveCustomer(CustomerDto dto) throws SQLException ;
     ArrayList<CustomerDto> getAllCustomers() throws SQLException ;
    boolean updateCustomer(CustomerDto dto) throws SQLException ;
    CustomerDto searchCustomer(String id) throws SQLException ;
    boolean deleteCustomer(String id) throws SQLException ;



}
