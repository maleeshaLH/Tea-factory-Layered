package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.CustomerBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.CustomerDAO;
import lk.Ijse.TeaFctory.dto.CustomerDto;
import lk.Ijse.TeaFctory.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.CUSTOMER);
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getId(),dto.getFirst_name(),
                dto.getLast_name(),dto.getTel(), dto.getAddress(), dto.getCity()));

    }

    public ArrayList<CustomerDto> getAllCustomers() throws SQLException {

        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer: customers){
            customerDtos.add(new CustomerDto(customer.getId(),customer.getFirst_name(),
                    customer.getLast_name(),customer.getTelephone_number(),customer.getAddress(),customer.getCity()));
        }
        return customerDtos;

    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getFirst_name(),
                dto.getLast_name(),dto.getTel(),dto.getAddress(),dto.getCity(),dto.getId()));

    }

    public  CustomerDto searchCustomer(String id) throws SQLException {

        Customer customer = customerDAO.search(id);
        CustomerDto customerDto = new CustomerDto(customer.getId(),customer.getFirst_name(),
                customer.getLast_name(),customer.getTelephone_number(),customer.getAddress(),customer.getCity());
        return customerDto;

    }


    public boolean deleteCustomer(String id) throws SQLException {
       return customerDAO.delete(id);

    }



}
