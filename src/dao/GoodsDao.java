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
import model.Goods;

/**
 *
 * @author Ruolin
 */
public class GoodsDao {
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
	
    public Goods getGoodsById(int id) throws Exception{
		
	Goods s = null;
	initConnection();
	String sql = "SELECT * FROM Goods WHERE goods_id=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, id+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Goods();
            s.setGoodsId(id);
            s.setGoodsName(rs.getString("goods_name"));
            s.setState(rs.getBoolean("goods_status"));
	}
	closeConnection();
	return s;
    }
    
    public ArrayList<Goods> getAllGoods() throws Exception{
		
        ArrayList<Goods> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Goods";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            Goods s = new Goods();
            s.setGoodsId(rs.getInt("goods_id"));
            s.setGoodsName(rs.getString("goods_name"));
            s.setState(rs.getBoolean("goods_status"));
            list.add(s);
        }
        closeConnection();
        return list;	
    }
    
    public ArrayList<Goods> getGoodsByStatus(boolean status) throws Exception{
		
        ArrayList<Goods> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Goods where goods_status="+status+"";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            Goods s = new Goods();
            s.setGoodsId(rs.getInt("goods_id"));
            s.setGoodsName(rs.getString("goods_name"));
            s.setState(rs.getBoolean("goods_status"));
            list.add(s);
        }
        closeConnection();
        return list;	
    }

    public Goods getGoodsByName(String name) throws Exception{

        Goods s = null;
        initConnection();
        String sql = "SELECT * FROM Goods WHERE goods_name=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Goods();
            s.setGoodsId(rs.getInt("goods_id"));
            s.setGoodsName(rs.getString("goods_name"));
            s.setState(rs.getBoolean("goods_status"));
	}
	closeConnection();
	return s;
    }

    public boolean addGoods(Goods goods) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO Goods( goods_name) "
                        + "VALUES('" + goods.getGoodsName() +  "')";
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

    public boolean deleteGoods(int goodsId) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE Goods SET goods_status= '1' where goods_id = "+ goodsId ;

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
    public boolean updateGoods(Goods goods) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE Goods SET goods_name='" + goods.getGoodsName() + "'" + "where goods_id = "+ goods.getGoodsId() ;
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
