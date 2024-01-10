package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.SupplierBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.SupplierDAO;
import lk.Ijse.TeaFctory.dto.SupplierDto;
import lk.Ijse.TeaFctory.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.SUPPPLIER);

    public ArrayList<SupplierDto> loadAllSupplier() throws SQLException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSu_id(), supplier.getName(),
                    supplier.getLoction(), supplier.getEmail(), supplier.getTelephone_number()));
        }
        return supplierDtos;
    }


    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save(new Supplier(dto.getS_id(), dto.getS_name(),
                dto.getS_location(), dto.getS_email(), dto.getS_tel()));


    }

    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getS_name(),
                dto.getS_location(), dto.getS_email(), dto.getS_tel(), dto.getS_id()));


    }

    public  SupplierDto searchSupplier(String id) throws SQLException {
        Supplier supplier = supplierDAO.search(id);
        SupplierDto supplierDto  = new SupplierDto(supplier.getSu_id(),supplier.getName(),
                supplier.getLoction(),supplier.getEmail(),supplier.getTelephone_number());
        return supplierDto;
    }


    public ArrayList<SupplierDto> getAllSupplier() throws SQLException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSu_id(), supplier.getName(),
                    supplier.getLoction(), supplier.getEmail(), supplier.getTelephone_number()));
        }
        return supplierDtos;

    }
}
