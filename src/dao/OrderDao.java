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
import model.Order;

/**
 *
 * @author Ruolin
 */
public class OrderDao {
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
	/*
    idfororder
order_no
Patient_id
total_price
payment_time
store_id
delivery_status

    */
    public Order getOrderById(int id) throws Exception{
		
	Order s = null;
	initConnection();
	String sql = "SELECT * FROM `order` WHERE idfororder=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, id+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Order();
            s.setId(id);
            s.setStoreId(rs.getInt("store_id"));
            s.setOrderNo(rs.getString("order_no"));
            s.setStatus(rs.getString("delivery_status"));
            s.setTotalPrice(rs.getDouble("total_price"));
            s.setUserId(rs.getInt("Patient_id"));
            s.setPaymentTime(rs.getString("payment_time"));
	}
	closeConnection();
	return s;
    }
    public int getOrderIdByOrderNo(String orderNo) throws Exception{
		
	int orderId = 0;
	initConnection();
	String sql = "SELECT * FROM `order` WHERE order_no=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, orderNo+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            orderId = rs.getInt("idfororder");
        }
	closeConnection();
	return orderId;
    }
        
    public Order getOrderByOrderNo(String orderNo) throws Exception{
		
	Order s = null;
	initConnection();
	String sql = "SELECT * FROM `order` WHERE order_no=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, orderNo+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Order();
            s.setId(rs.getInt("idfororder"));
            s.setStoreId(rs.getInt("store_id"));
            s.setOrderNo(rs.getString("order_no"));
            s.setStatus(rs.getString("delivery_status"));
            s.setTotalPrice(rs.getDouble("total_price"));
            s.setUserId(rs.getInt("Patient_id"));
            s.setPaymentTime(rs.getString("payment_time"));
	}
	closeConnection();
	return s;
    }
    
//    public ArrayList<Inventory> getAllInventory() throws Exception{
//		
//        ArrayList<Inventory> list = new ArrayList<>();
//        initConnection();
//        String sql = "SELECT * FROM Inventory";
//        Statement stat = conn.createStatement();
//        ResultSet rs = stat.executeQuery(sql);
//        while(rs.next()){
//            Inventory s = new Inventory();
//            s.setId(rs.getInt("id"));
//            s.setStoreId(rs.getInt("store_id"));
//            s.setGoodsId(rs.getInt("goods_id"));
//            s.setSellingPrice(rs.getDouble("selling_price"));
//            list.add(s);
//        }
//        closeConnection();
//        return list;	
//    }
//
    public ArrayList<Order> getOrderByUserId(int userId) throws Exception{

        ArrayList<Order> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM `order` WHERE patient_id = " +"'"+userId+"'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Order s = new Order();
            s.setId(rs.getInt("idfororder"));
            s.setStoreId(rs.getInt("store_id"));
            s.setOrderNo(rs.getString("order_no"));
            s.setPaymentTime(rs.getString("payment_time"));
            s.setStatus(rs.getString("delivery_status"));
            s.setTotalPrice(rs.getDouble("total_price"));
            s.setUserId(userId);
            list.add(s);
        }
        closeConnection();
        return list;	
    }

    public boolean addOrder(Order order) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO `order`( order_no, store_Id,Patient_id,total_price, delivery_status) "
                        + "VALUES('"+order.getOrderNo() + "','" + order.getStoreId() + "','" + order.getUserId() + "','" + order.getTotalPrice()+"','" +order.getStatus() + "')";
//        System.out.println(sql);
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

    public boolean deleteOrder(int orderId) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "DELETE FROM `order` WHERE idfororder='" + orderId + "'";

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
    public boolean updateOrderState(int orderId, String state) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE `order` SET delivery_status ='" + state +  "'" + "where idfororder = "+ orderId ;
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
    
    public boolean updateOrderPrice(int orderId, double price) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE `order` SET total_price ='" + price +  "'" + "where idfororder = "+ orderId ;
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
