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
    id
order_num
Customer_id
total_price
payment_time
store_ID
delivery_status

    */
    public Order getOrderById(int id) throws Exception{
		
	Order s = null;
	initConnection();
	String sql = "SELECT * FROM Order WHERE id=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, id+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            s = new Order();
            s.setId(id);
            s.setStoreId(rs.getInt("store_id"));
            s.setOrderNo(rs.getString("order_num"));
            s.setStatus(rs.getString("delivery_status"));
            s.setTotalPrice(rs.getDouble("total_price"));
            s.setUserId(rs.getInt("Customer_id"));
            s.setPaymentTime(rs.getDate("payment_time").toLocalDate());
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
//    public ArrayList<Inventory> getInventoryByUserId(int userId) throws Exception{
//
//        ArrayList<Inventory> list = new ArrayList<>();
//        initConnection();
//        String sql = "SELECT * FROM Inventory WHERE store_id = " +"'"+storeId+"'";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
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

    public boolean addOrder(Order order) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO `order`( order_num, store_ID,Customer_id,total_price, delivery_status) "
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
        String sql = "DELETE FROM Order WHERE id='" + orderId + "'";

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
        String sql = "UPDATE Order SET delivery_status ='" + state +  "'" + "where id = "+ orderId ;
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
