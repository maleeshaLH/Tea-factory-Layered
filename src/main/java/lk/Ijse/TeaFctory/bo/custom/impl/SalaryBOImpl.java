package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.SalaryBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.SalaryDAO;
import lk.Ijse.TeaFctory.dto.SalaryDto;
import lk.Ijse.TeaFctory.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO =(SalaryDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.SALARY);
    public boolean saveSalary(SalaryDto dto) throws SQLException {
        return salaryDAO.save(new Salary(dto.getS_id(),dto.getEmp_id(),dto.getDate(),dto.getSalary()));


    }
    public List<SalaryDto> getAllCustomers() throws SQLException {
        ArrayList<Salary> salaries =salaryDAO.getAll();
        ArrayList<SalaryDto> salaryDtos =new ArrayList<>();

        for (Salary salary :salaries){
            salaryDtos.add(new SalaryDto(salary.getS_id(),salary.getEmp_id(),salary.getDate(),salary.getSalary()));
        }
        return salaryDtos;

    }


    public String generateNextOrderIds() throws SQLException {
       return salaryDAO.generateId();
    }


}
