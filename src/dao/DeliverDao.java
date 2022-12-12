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
import model.Deliver;

/**
 *
 * @author ZeyuLiao
 */
public class DeliverDao {
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

    public Deliver getDeliverByNo(String Delivery_No) throws Exception{
		
	Deliver d = null;
	initConnection();
	String sql = "SELECT * FROM delivery WHERE Delivery_No=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, Delivery_No+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            d = new Deliver();
            d.setDelivery_NO(rs.getString("Delivery_NO"));
            d.setOrder_id(rs.getString("order_id"));
            d.setDelivery_company(rs.getString("delivery_company"));
            d.setDelivery_status(rs.getString("delivery_status"));
            d.setDelivered_time(rs.getString("Delivered_time"));

	}
	closeConnection();
	return d;
    }
    
        public ArrayList<Deliver> getAllDeliver() throws Exception{
		
        ArrayList<Deliver> deliverList = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM delivery";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            Deliver d = new Deliver();
            d.setDelivery_NO(rs.getString("Delivery_NO"));
            d.setOrder_id(rs.getString("order_id"));
            d.setDelivery_company(rs.getString("delivery_company"));
            d.setDelivery_status(rs.getString("delivery_status"));
            d.setDelivered_time(rs.getString("Delivered_time"));
            deliverList.add(d);
        }
        closeConnection();
        return deliverList;	
    }

    public boolean addOrder(Deliver del) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "INSERT INTO `delivery`(order_id,delivery_company, delivery_status) "
                        + "VALUES('" + del.getOrder_id()+ "','" + del.getDelivery_company()+ "','" + del.getDelivery_status() + "')";
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

    public boolean deleteOrder(int Delivery_No) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "DELETE FROM delivery WHERE Delivery_No='" + Delivery_No + "'";

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
    public boolean updateDeliverState(String Delivery_No, String status,String company) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE delivery SET delivery_status ='" + status + "', delivery_company ='" + company + "' WHERE Delivery_No = " + Delivery_No;
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
