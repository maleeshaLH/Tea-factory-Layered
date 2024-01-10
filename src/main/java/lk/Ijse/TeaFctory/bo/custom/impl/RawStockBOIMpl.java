package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.RawStockBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.RawStockDAO;
import lk.Ijse.TeaFctory.dto.RawStockDto;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;
import lk.Ijse.TeaFctory.entity.RawStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawStockBOIMpl implements RawStockBO {
    RawStockDAO rawStockDAO = (RawStockDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.RAWSTOCK);



    @Override
    public String generateId() throws SQLException {
        return null;
    }


    @Override
    public boolean saveRawStock(RawStockDto dto) throws SQLException {
        return rawStockDAO.save(new RawStock( dto.getRs_id(),dto.getDescription(),dto.getUnit_price(),
                dto.getWeight(),dto.getQuality()));
    }

    @Override
    public ArrayList<RawStockDto> getAllRawStock() throws SQLException {
        ArrayList<RawStock> rawStocks = rawStockDAO.getAll();
        ArrayList<RawStockDto> rawStockDtos =new ArrayList<>();
        for (RawStock rawStock :rawStocks){
            rawStockDtos.add(new RawStockDto(rawStock.getRs_id(),rawStock.getDescription(),
                    rawStock.getUnit_price(),rawStock.getWeight(),rawStock.getQty()));
        }
        return rawStockDtos;

    }

    @Override
    public boolean deleteRawStock(String id) throws SQLException {
        return rawStockDAO.delete(id);

    }

    @Override
    public boolean updateRawStock(RawStockDto dto) throws SQLException {
        return rawStockDAO.update(new RawStock(dto.getRs_id(),dto.getDescription(),dto.getUnit_price(),dto.getWeight(),
                dto.getQuality()));


    }
    @Override
    public RawStockDto searchRawStock(String id) throws SQLException {

        RawStock rawStock =rawStockDAO.search(id);
        RawStockDto rawStockDto =new RawStockDto(rawStock.getRs_id(),rawStock.getDescription(),
                rawStock.getUnit_price(),rawStock.getWeight(),rawStock.getQty());
        return rawStockDto;

    }
    public  boolean updateStock(List<SupplierOrderTm> tmList) throws SQLException {
       return rawStockDAO.updateStock(tmList);


    }

    public boolean update(List<StockOrderTm> tmList) throws SQLException {
       return rawStockDAO.update(tmList);


    }

}
