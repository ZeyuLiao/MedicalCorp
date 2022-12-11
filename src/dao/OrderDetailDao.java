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
import model.OrderDetail;

/**
 *
 * @author Ruolin
 */
public class OrderDetailDao {
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
    

    public ArrayList<OrderDetail> getOrderDetailByOrderId(int orderId) throws Exception{

        ArrayList<OrderDetail> list = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Inventory WHERE order_no = " +"'"+orderId+"'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            OrderDetail s = new OrderDetail();
            s.setGoodsQuantity(rs.getInt("quantity"));
            s.setGoodsId(rs.getInt("goods_id"));
            list.add(s);
        }
        closeConnection();
        return list;	
    }

    public boolean addOrderDetail(OrderDetail orderDetail) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO OrderDetail( order_no, goods_id,quantity) "
                        + "VALUES('"+orderDetail.getOrderId() + "','" + orderDetail.getGoodsId() + "','" + orderDetail.getGoodsQuantity() + "')";
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

//    public boolean deleteOrder(int orderId) throws Exception{
//
//        boolean res = true;
//        initConnection();
//        String sql = "DELETE FROM Order WHERE idfororder='" + orderId + "'";
//
//        try {
//            Statement stat = conn.createStatement();
//            stat.executeUpdate(sql);
//        }catch(Exception e) {
//            e.printStackTrace();
//            res = false;
//        } finally {
//            closeConnection();
//        }
//        return res;
//    }

    public void closeConnection() throws Exception{
        conn.close();
    }
}
