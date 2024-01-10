package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.PreperedStockDAO;
import lk.Ijse.TeaFctory.db.DbConnection;
import lk.Ijse.TeaFctory.dto.tm.CartTm;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.entity.PreparedStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreperedStockDAOImpl implements PreperedStockDAO {
    public  boolean updatePreparedStock(List<StockOrderTm> tmList) throws SQLException {
        for (StockOrderTm stockOrderTm : tmList) {
            if(!updatePreparedQty(stockOrderTm)) {
                return false;
            }
        }
        return true;

    }

    private static boolean updatePreparedQty(StockOrderTm stockOrderTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        System.out.println("3");
        String sql = "UPDATE prepared_stock SET qty = qty + ? WHERE p_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, stockOrderTm.getQty());
        pstm.setString(2, stockOrderTm.getPreparedStockId());

        return pstm.executeUpdate() > 0; //true
    }



    public boolean save(PreparedStock entity) throws SQLException {

       return SQLUtil.execute("INSERT INTO prepared_stock VALUES(?,?,?,?,?)",
               entity.getP_id(),entity.getDescription(),entity.getUnit_price(),
               entity.getWeight(),entity.getQty());


    }

    public boolean update(PreparedStock entity) throws SQLException {

        return SQLUtil.execute("UPDATE prepared_stock SET description = ?,unit_price =? ,weight = ?,qty = ? WHERE p_id =?",
               entity.getDescription(),entity.getUnit_price(),
                entity.getWeight(),entity.getQty(),entity.getP_id());



    }

    public PreparedStock search(String id) throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM prepared_stock WHERE p_id = ?",id);
        rst.next();

        return new PreparedStock(id+"",
                rst.getString("description"),
                rst.getInt("unit_price"),
                rst.getInt("weight"),
                rst.getInt("qty"));

    }

    public boolean delete(String id) throws SQLException {
     return  SQLUtil.execute("DELETE FROM prepared_stock WHERE p_id =?",id);

    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    public ArrayList<PreparedStock> getAll() throws SQLException {

        ResultSet rst =SQLUtil.execute("SELECT * FROM prepared_stock");
        ArrayList<PreparedStock> allPreparedStock =new ArrayList<>();
        while (rst.next()){
            PreparedStock preparedStock =new PreparedStock(
                    rst.getString("p_id"),
                    rst.getString("description"),
                    rst.getInt("unit_price"),
                    rst.getInt("weight"),
                    rst.getInt("qty"));
            allPreparedStock.add(preparedStock);
        }
        return allPreparedStock;

    }

    public boolean updateStock(List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if(!updateQty(cartTm)) {
                return false;
            }
        }
        return true;

    }

    private boolean updateQty(CartTm cartTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE prepared_stock SET qty = qty - ? WHERE p_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, cartTm.getQty());
        pstm.setString(2, cartTm.getP_id());

        return pstm.executeUpdate() > 0; //true
    }

}
