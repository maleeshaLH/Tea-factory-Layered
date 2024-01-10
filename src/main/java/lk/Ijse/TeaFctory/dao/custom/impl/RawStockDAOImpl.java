package lk.Ijse.TeaFctory.dao.custom.impl;

import lk.Ijse.TeaFctory.dao.SQLUtil;
import lk.Ijse.TeaFctory.dao.custom.RawStockDAO;
import lk.Ijse.TeaFctory.db.DbConnection;
import lk.Ijse.TeaFctory.dto.tm.StockOrderTm;
import lk.Ijse.TeaFctory.dto.tm.SupplierOrderTm;
import lk.Ijse.TeaFctory.entity.RawStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawStockDAOImpl implements RawStockDAO {

    @Override
    public String generateId() throws SQLException {
        return null;
    }


   @Override
   public boolean save(RawStock entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO raw_stock VALUES(?, ?, ?, ?,?)",
                entity.getRs_id(),entity.getDescription(),entity.getUnit_price(),
               entity.getWeight(),entity.getQty());
    }

   @Override
   public ArrayList<RawStock> getAll() throws SQLException {
        ResultSet rst =  SQLUtil.execute("SELECT * FROM  raw_stock");
        ArrayList<RawStock> allRawStock =new ArrayList<>();
        while (rst.next()){
            RawStock entity = new  RawStock(
                    rst.getString("rs_id"),
                    rst.getString("description"),
                    rst.getInt("unit_price"),
                    rst.getInt("weight"),
                    rst.getInt("qty"));
            allRawStock.add(entity);
        }
        return allRawStock;

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM raw_stock WHERE rs_id = ?",id);

    }

   @Override
   public boolean update(RawStock entity) throws SQLException {
        return SQLUtil.execute("UPDATE raw_stock SET description = ?,unit_price = ? ,weight = ?, quality = ? WHERE rs_id = ?",
                entity.getDescription(),entity.getUnit_price(),entity.getWeight(),
                entity.getQty(),entity.getRs_id());


    }


    @Override
    public RawStock search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM raw_stock WHERE rs_id = ?",id);
        rst.next();
        return new RawStock(id+"",rst.getString("description"),rst.getInt("unit_price"),
                rst.getInt("weight"),rst.getInt("qty"));

    }
    public  boolean updateStock(List<SupplierOrderTm> tmList) throws SQLException {
        for (SupplierOrderTm supplierOrderTm : tmList) {
            if(!updateQty(supplierOrderTm)) {
                return false;
            }
        }
        return true;


    }

    private static boolean updateQty(SupplierOrderTm supplierOrderTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE raw_stock SET qty = qty - ? WHERE rs_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, supplierOrderTm.getQty());
        pstm.setString(2, supplierOrderTm.getS_id());

        return pstm.executeUpdate() > 0; //true
    }



    public boolean update(List<StockOrderTm> tmList) throws SQLException {
        for (StockOrderTm stockOrderTm : tmList) {
            if(!updateStockOrder(stockOrderTm)) {
                return false;
            }
        }
        return true;

    }

    private boolean updateStockOrder(StockOrderTm stockOrderTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE raw_stock SET qty = qty - ? WHERE rs_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, stockOrderTm.getQty());
        pstm.setString(2, stockOrderTm.getRawStockId());

        return pstm.executeUpdate() > 0; //true

    }

//    public RawStock search(String rs_id) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM raw_stock WHERE description = ?",rs_id);
//        rst.next();
//        return new RawStock(rs_id+"",rst.getString("description"),rst.getInt("unit_price"),
//                rst.getInt("weight"),rst.getInt("quality"));
//
//
//    }
}
