/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
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
            // System.out.println(rs.getString("patient_id") + " " + rs.getString("pwd"));
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
        ResultSet rs = stmt.executeQuery("select * FROM doctorList join login using (logid) where doctor_id = " + DoctorID + " and pwd = md5('" + pwd + "');");

        Doctor doctor = null;

        // Iterate over the result set and print the results
        while (rs.next()) {
            doctor = new Doctor();
            // System.out.println(rs.getString("patient_id") + " " + rs.getString("pwd"));
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

            user.setRole(role);
        }
        rs.close();
        stmt.close();
        
        return user;
    }
}
