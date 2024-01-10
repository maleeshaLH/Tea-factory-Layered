package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.PreparedStockBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.PreperedStockDAO;
import lk.Ijse.TeaFctory.dto.PreparedstockDto;
import lk.Ijse.TeaFctory.dto.tm.CartTm;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.entity.PreparedStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStockBOImpl implements PreparedStockBO {
    PreperedStockDAO preperedStockDAO =(PreperedStockDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.PREPAREDSTOCK);
    public  boolean updatePreparedStock(List<StockOrderTm> tmList) throws SQLException {
       return preperedStockDAO.updatePreparedStock(tmList);

    }

    public boolean saveStock(PreparedstockDto dto) throws SQLException {

        return preperedStockDAO.save(new PreparedStock(dto.getP_id(),dto.getDescription(),
                dto.getUnit_price(),dto.getWeight(),dto.getQty()));


    }

    public boolean updateP_Stock(PreparedstockDto dto) throws SQLException {
        return preperedStockDAO.update(new PreparedStock(dto.getP_id(),dto.getDescription(),
                dto.getUnit_price(),dto.getWeight(),dto.getQty()));



    }

    public PreparedstockDto searchStock(String id) throws SQLException {

        PreparedStock preparedStock = preperedStockDAO.search(id);
        PreparedstockDto preparedstockDto = new PreparedstockDto(preparedStock.getP_id(),
                preparedStock.getDescription(),preparedStock.getUnit_price(),
                preparedStock.getWeight(),preparedStock.getQty());
        return preparedstockDto;

    }

    public boolean deleteStock(String id) throws SQLException {

        return preperedStockDAO.delete(id);


    }
    public ArrayList<PreparedstockDto> getAllStock() throws SQLException {

        ArrayList<PreparedStock> preparedStocks = preperedStockDAO.getAll();
        ArrayList<PreparedstockDto> preparedstockDtos =new ArrayList<>();
        for (PreparedStock preparedStock : preparedStocks){
            preparedstockDtos.add(new PreparedstockDto(preparedStock.getP_id(),
                    preparedStock.getDescription(),preparedStock.getUnit_price(),
                    preparedStock.getWeight(),preparedStock.getQty()));
        }
        return preparedstockDtos;

    }

    public boolean updateStock(List<CartTm> tmList) throws SQLException {

        return preperedStockDAO.updateStock(tmList);

    }



}
