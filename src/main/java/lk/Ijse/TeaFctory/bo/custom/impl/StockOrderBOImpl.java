package lk.Ijse.TeaFctory.bo.custom.impl;

import lk.Ijse.TeaFctory.bo.custom.StockOrderBO;
import lk.Ijse.TeaFctory.dao.DAOFcatory;
import lk.Ijse.TeaFctory.dao.custom.StockOrderDAO;
import lk.Ijse.TeaFctory.entity.StockOrder;

import java.sql.SQLException;

public class StockOrderBOImpl implements StockOrderBO {
    StockOrderDAO stockOrderDAO =(StockOrderDAO) DAOFcatory.getDaoFcatory().
            getDAO(DAOFcatory.DAOTypes.STOCKORDER);

    public boolean saveStockOrder(String p_id, String rs_id, String description, int unitPrice, int weight, int qty) throws SQLException {
        return stockOrderDAO.save(new StockOrder(p_id,rs_id,description,unitPrice,weight,qty));
    }
}
