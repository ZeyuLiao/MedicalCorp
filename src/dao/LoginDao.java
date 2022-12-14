/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.*;

/**
 *
 * @author ziyu
 */
public class LoginDao {

    private Connection conn = null;

    // MySQL 8.0  - JDBC Driver+Database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //localhost:3306/+database name
    static final String DB_URL = "jdbc:mysql://localhost:3306/neu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "123";

    public void initConnection() throws Exception {

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public Patient isValidPatient(int patientID, String pwd) throws Exception {

        // Create a Statement object to execute the query
        Statement stmt = conn.createStatement();

        // Execute the query and retrieve the result set
        ResultSet rs = stmt.executeQuery("SELECT * FROM patient JOIN login USING (logid) WHERE patient_id = " + patientID + " AND pwd = MD5('" + pwd + "');");

        Patient patient = null;

        // Iterate over the result set and print the results
        while (rs.next()) {
            patient = new Patient();
            String logID = rs.getString("logID");
            String name = rs.getString("name");
            String phoneNumber = rs.getString("phone_number");
            String communityName = rs.getString("community_name");
            String role = rs.getString("role");
            String dob = rs.getString("DOB");

            patient.setPatientId(patientID);
            patient.setName(name);
            patient.setCommunityName(communityName);
            patient.setLogId(Integer.parseInt(logID));
            patient.setPhoneNumber(phoneNumber);
            patient.setRole(role);
            patient.setDOB(dob);

        }

        // Close the result set, statement, and connection objects
        rs.close();
        stmt.close();


        return patient;
    }

    public Doctor isValidDoctor(int DoctorID, String pwd) throws Exception {
        // Create a Statement object to execute the query
        Statement stmt = conn.createStatement();
        // Execute the query and retrieve the result set
        ResultSet rs = stmt.executeQuery("select * FROM doctorList join login using (logid) where doctor_id = " + DoctorID + " and pwd = md5('" + pwd + "')");
        
        Doctor doctor = null;

        // Iterate over the result set and print the results
        while (rs.next()) {
            doctor = new Doctor();
            String doctorID = rs.getString("doctor_id");
            String name = rs.getString("doctor_name");
            String hospitalname = rs.getString("hospital_name");
            String department = rs.getString("department");
            String PhoneNumber = rs.getString("Phone_number");
            String role = rs.getString("role");
            String photoAddress = rs.getString("photo_address");

            doctor.setDoctorID(Integer.parseInt(doctorID));
            doctor.setName(name);
            doctor.setHospitalName(hospitalname);
            doctor.setDepartment(department);
            doctor.setPhoneNumber(PhoneNumber);
            doctor.setRole(role);
            doctor.setPhotoAddress(photoAddress);

        }

        // Close the result set, statement, and connection objects
        rs.close();
        stmt.close();
        return doctor;
    }

    public User isValidOtherUser(int logid, String pwd) throws Exception {
        Statement stmt = conn.createStatement();
        User user = null;

        // Execute the query and retrieve the result set
        ResultSet rs = stmt.executeQuery("select * FROM login where logid = " + logid + " and pwd = md5('" + pwd + "');");
        while (rs.next()) {
            user = new User();
            String role = rs.getString("role");
            user.setLogId(rs.getInt("logid"));
            user.setName(rs.getString("user_name"));
            user.setRole(role);
        }
        rs.close();
        stmt.close();     
        return user;
    }    
    
    public int addNewLogin(String accountName, String role, String pwd) throws SQLException, Exception{
        initConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO login (user_name,role,pwd) VALUES('" + accountName + "','" + role + "',md5('" + pwd + "'))");
        String sql2 = "SELECT logid FROM login WHERE user_name= '"+accountName+"'";
        ResultSet rs = stmt.executeQuery(sql2);
        int logid = rs.getInt("logid");    
        stmt.close(); 
        return logid;
    }
    
    public ArrayList<DeliverMan> getAllDeliverMen() throws Exception{

        ArrayList<DeliverMan> deliverList = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM login";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            DeliverMan dm = new DeliverMan();
            dm.setLoginId(rs.getInt("logid"));
            dm.setAccountName(rs.getString("user_name"));
            dm.setRole(rs.getString("role"));
            deliverList.add(dm);
        }
        stmt.close();
        return deliverList;	
    }
    
      //delete
    public boolean deleteDeliverMan(int loginId) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "DELETE FROM login WHERE logid='" + loginId + "'";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e) {
            e.printStackTrace();
            res = false;
        } 
        return res;
    }
    
//    public DeliverMan getDeliverManbyLoginID(int LoginId) throws Exception{
//
//
//        initConnection();
//        String sql = "SELECT * FROM Patient WHERE logid= '"+LoginId+"'";
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(sql);
//        DeliverMan dm = new DeliverMan();
//        while(rs.next()){
//            dm.setLoginId(rs.getInt("user_name"));
//            dm.setAccountName(rs.getString("user_name"));
//            dm.setRole(rs.getString("role"));
//        }
//        stmt.close();
//        return dm;	
//    }
}
