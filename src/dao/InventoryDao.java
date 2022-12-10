/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Inventory;

/**
 *
 * @author Ruolin
 */
public class InventoryDao {
    private Connection conn = null;

    // MySQL 8.0  - JDBC Driver+Database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //localhost:3306/+database name
    static final String DB_URL = "jdbc:mysql://localhost:3306/neu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
 
    static final String USER = "root";
    static final String PASS = "123";
    
    public void initConnection() throws Exception{
		
	Class.forName(JDBC_DRIVER);    
    	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	}
	
    public Inventory getInventoryById(int id) throws Exception{
		
	Inventory s = null;
	initConnection();
	String sql = "SELECT * FROM Inventory WHERE id=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, id+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Inventory();
            s.setId(id);
            s.setStoreId(rs.getInt("store_id"));
            s.setGoodsId(rs.getInt("goods_id"));
            s.setSellingPrice(rs.getDouble("selling_price"));
	}
	closeConnection();
	return s;
    }
    
    public Inventory getInventoryByStoreIdAndGoodsId(int storeId,int goodsId) throws Exception{
	Inventory s = null;
	initConnection();
	String sql = "SELECT * FROM Inventory WHERE store_id="+storeId+" AND goods_id="+goodsId;
	PreparedStatement ps = conn.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Inventory();
            s.setId(rs.getInt("id"));
            s.setStoreId(rs.getInt("store_id"));
            s.setGoodsId(rs.getInt("goods_id"));
            s.setSellingPrice(rs.getDouble("selling_price"));
	}
	closeConnection();
	return s;
    }
    
    public ArrayList<Inventory> getAllInventory() throws Exception{
		
        ArrayList<Inventory> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Inventory";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            Inventory s = new Inventory();
            s.setId(rs.getInt("id"));
            s.setStoreId(rs.getInt("store_id"));
            s.setGoodsId(rs.getInt("goods_id"));
            s.setSellingPrice(rs.getDouble("selling_price"));
            list.add(s);
        }
        closeConnection();
        return list;	
    }

    public ArrayList<Inventory> getInventoryByStoreId(int storeId) throws Exception{

        ArrayList<Inventory> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Inventory WHERE store_id = " +"'"+storeId+"'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Inventory s = new Inventory();
            s.setId(rs.getInt("id"));
            s.setStoreId(rs.getInt("store_id"));
            s.setGoodsId(rs.getInt("goods_id"));
            s.setSellingPrice(rs.getDouble("selling_price"));
            list.add(s);
        }
        closeConnection();
        return list;	
    }

    public boolean addInventory(Inventory inventory) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO Inventory( store_id,goods_id,selling_price ) "
                        + "VALUES('" + inventory.getStoreId() + "','" + inventory.getGoodsId() + "','" + inventory.getSellingPrice() + "')";
        //System.out.println(sql);
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        }catch(Exception e) {
            e.printStackTrace();
            res = false;
        } finally {
            closeConnection();
        }
        return res;
    }

    public boolean deleteInventory(int inventoryId) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "DELETE FROM Inventory WHERE id='" + inventoryId + "'";

        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        }catch(Exception e) {
            e.printStackTrace();
            res = false;
        } finally {
            closeConnection();
        }
        return res;
    }
	//update
    public boolean updateInventory(Inventory inventory) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE Inventory SET store_id ='" + inventory.getStoreId() + "', goods_id='" + inventory.getGoodsId() 
                + "', selling_price='"+ inventory.getSellingPrice() +  "'" + "where id = "+ inventory.getId() ;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
        }catch(Exception e) {
            e.printStackTrace();
            res = false;
        } finally {
            closeConnection();
        }
        return res;
    }

    public void closeConnection() throws Exception{
        conn.close();
    }
}
