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
import model.Store;

/**
 *
 * @author Ruolin
 */
public class StoreDao {
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
	
    public Store getStoreById(int id) throws Exception{
		
	Store s = null;
	initConnection();
	String sql = "SELECT * FROM Store WHERE store_id=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, id+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Store();
            s.setStoreId(id);
            s.setStoreName(rs.getString("store_name"));
            s.setCommunity(rs.getString("community"));
	}
	closeConnection();
	return s;
    }
    
    public ArrayList<Store> getAllStore() throws Exception{
		
        ArrayList<Store> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM store";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            Store s = new Store();
            s.setStoreId(rs.getInt("store_id"));
            s.setStoreName(rs.getString("store_name"));
            s.setCommunity(rs.getString("community"));
            list.add(s);
        }
        closeConnection();
        return list;	
    }

    public Store getStoreByName(String name) throws Exception{

        Store s = null;
        initConnection();
        String sql = "SELECT * FROM Store WHERE store_name=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Store();
            s.setStoreId(rs.getInt("store_id"));
            s.setStoreName(rs.getString("store_name"));
            s.setCommunity(rs.getString("community"));
	}
	closeConnection();
	return s;
    }

    public boolean addStore(Store store) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO Store( store_name) "
                        + "VALUES('" + store.getStoreName() +  "')";
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

    public boolean deleteStore(int storeId) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "DELETE FROM Store WHERE store_id='" + storeId + "'";

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
    public boolean updateStore(Store store) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE Store SET store_name='" + store.getStoreName() +"'" + "where store_id = "+ store.getStoreId() ;
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
