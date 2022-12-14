/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Patient;
import model.User;

/**
 *
 * @author Ruolin
 */
public class PatientDao {
    //CRUD of Patient
    
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
	
    public Patient getPatientById(int id) throws Exception{
		
	Patient p = null;
	initConnection();
	String sql = "SELECT * FROM Patient WHERE patient_id=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, id+"");
	ResultSet rs = ps.executeQuery();
	if (rs.next()){
            p = new Patient();
            p.setLogId(rs.getInt("logId"));
            p.setPatientId(id);
            p.setCommunityName(rs.getString("community_name"));
            p.setName(rs.getString("name"));
            p.setDOB(rs.getString("DOB"));
            p.setPhoneNumber(rs.getString("phone_number"));
	}
//        System.out.print(p.toString());
	closeConnection();
	return p;
    }
    
    public ArrayList<Patient> getAllPatient() throws Exception{
		
        ArrayList<Patient> pList = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM patient";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
            Patient patient = new Patient();
            patient.setLogId(rs.getInt("logId"));
            patient.setPatientId(rs.getInt("patient_id"));
            patient.setName(rs.getString("name"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setDOB(rs.getString("DOB"));
            patient.setCommunityName(rs.getString("community_name"));
            pList.add(patient);
        }
        closeConnection();
        return pList;	
    }

    public Patient getPatientByName(String name) throws Exception{

        //ArrayList<Patient> pList = new ArrayList<>();
        initConnection();
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE name=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            patient = new Patient();
            patient.setPatientId(rs.getInt("patient_id"));
            patient.setLogId(rs.getInt("logId"));
            patient.setName(rs.getString("name"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setDOB(rs.getString("DOB"));
            patient.setCommunityName(rs.getString("community_name"));
            //pList.add(patient);
        }
        closeConnection();

        return patient;
    }

    public ArrayList<Patient> getPatientByAddress(String communityName) throws Exception{

        ArrayList<Patient> pList = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM patient WHERE community_name LIKE " +"'"+communityName+"'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Patient patient = new Patient();
            patient.setPatientId(rs.getInt("patient_id"));
            patient.setLogId(rs.getInt("logId"));
            patient.setName(rs.getString("name"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setDOB(rs.getString("DOB"));
            patient.setCommunityName(rs.getString("community_name"));
            pList.add(patient);
            }
        closeConnection();

        return pList;
    }
    public ArrayList<Patient> getPatientByDOB(String DOB) throws Exception{

        ArrayList<Patient> pList = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Patient WHERE DOB=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, DOB);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Patient patient = new Patient();
            patient.setPatientId(rs.getInt("patient_id"));
            patient.setLogId(rs.getInt("logId"));
            patient.setName(rs.getString("name"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setDOB(rs.getString("DOB"));
            patient.setCommunityName(rs.getString("community_name"));
            pList.add(patient);
        }
        closeConnection();

        return pList;
    }

    public  ArrayList<Patient> getPatientByPhoneNumber(String phoneNumber) throws Exception{
		
        ArrayList<Patient> pList = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM Patient WHERE phone_number=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();

        //must have while(rs.next()){}
        while(rs.next()){
            Patient patient = new Patient();
            patient.setPatientId(rs.getInt("patient_id"));
            patient.setLogId(rs.getInt("logId"));
            patient.setName(rs.getString("name"));
            patient.setPhoneNumber(rs.getString("phone_umber"));
            patient.setDOB(rs.getString("DOB"));
            patient.setCommunityName(rs.getString("community_name"));
            pList.add(patient);
        }

        closeConnection();

        return pList;
    }

    //logId? 
    public boolean addPatient(Patient patient,String pwd) throws Exception{

        boolean res = true;
        initConnection();
        String insertUserSql = "insert into login(user_name, role, pwd) values (?, ?, md5(?))";
        
        try {
            PreparedStatement stat = conn.prepareStatement(insertUserSql);
            stat.setString(1, patient.getName());
            stat.setString(2, "patient");
            stat.setString(3, pwd);
            stat.executeUpdate();
            stat.close();
        }catch(Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        String getUserSql = "select * from login where user_name = ?";
        
//        User user = new User();
        String logID = ""; // todo
        
        try {
            PreparedStatement stat = conn.prepareStatement(getUserSql);
            stat.setString(1, patient.getName());
            ResultSet set = stat.executeQuery();
            
            if (set.next()) {
                logID = set.getString("logid");
            }
            
            stat.close();
        }catch(Exception e) {
            e.printStackTrace();
            res = false;
        }
        
        String sql = "INSERT INTO Patient( name,phone_number,DOB,community_name,logID ) "
                        + "VALUES('" + patient.getName() + "','" + patient.getPhoneNumber() + 
                        "','" + patient.getDOB() + "','" + patient.getCommunityName() + "','" + logID + "')";
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
    //delete
    public boolean deletePatient(int patientId) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "DELETE FROM Patient WHERE patient_id='" + patientId + "'";

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
    public boolean updatePatient(Patient patient) throws Exception{

        boolean res = true;
        initConnection();
        String sql = "UPDATE Patient SET name='" + patient.getName() + "', phone_number='" + patient.getPhoneNumber() 
                + "', DOB='" + patient.getDOB() + "', community_name='" + patient.getCommunityName() + "'" + "where patient_id = "+ patient.getPatientId() ;
//        try {
//            Statement stat = conn.createStatement();
//            stat.executeUpdate(sql);
//        }catch(Exception e) {
//            e.printStackTrace();
//            res = false;
//        } finally {
//            closeConnection();
//        }
        Statement stat = conn.createStatement();
        stat.executeUpdate(sql);
        closeConnection();
        return res;
    }
    
    
    public  boolean isExist(int id) throws Exception{
		
//        ArrayList<Integer> intList = new ArrayList<>();
        initConnection();
        String sql = "SELECT patient_id FROM Patient WHERE patient_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id+"");
        ResultSet rs = ps.executeQuery();

        //must have while(rs.next()){}
        if(rs.next()){
            closeConnection();
            return true;
        }

        closeConnection();

        return false;
    }
    
	
    public void closeConnection() throws Exception{
        conn.close();
    }
    
}