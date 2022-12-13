/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.examinationCop;

import model.Diagnose;

import com.alibaba.fastjson.JSON;
import dao.LoginDao;
import dao.PatientDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ziyu
 */
public class test {

    public static void main(String[] args) throws Exception {

        Diagnose diagnose = new Diagnose();

        // Set its properties
        diagnose.setDiagnose("Fluent");
        List<Diagnose.Examin> dlist = new ArrayList<>();
        dlist.add(new Diagnose.Examin("X-ray", "Good"));
        dlist.add(new Diagnose.Examin("CT", "Good"));
        
//        diagnose.getExamins().add(new Diagnose.Examin("X-ray", "Good"));
//        diagnose.getExamins().add(new Diagnose.Examin("CT", "Good"));
        diagnose.setExamins(dlist);
        
        List<String> tlist = new ArrayList<>();
        tlist.add("glucose21");
        tlist.add("Amoxicillin");
//        diagnose.getTreatment().add("glucose");
//        diagnose.getTreatment().add("Amoxicillin");

        // Convert the Diagnose instance to JSON
        String json = JSON.toJSONString(diagnose);

//        new test().updateDiagnose(json, "2");
//        System.out.println(new test().getDiagnose("1"));
        testPatient();
    }

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
    
    public static void testPatient() {
        try {
            LoginDao dao = new LoginDao();
            dao.initConnection();
            System.out.println(dao.isValidPatient(1, "123"));
            
            System.out.println(dao.isValidDoctor(1, "d123"));
           
            System.out.println(dao.isValidOtherUser(1, "123"));
            
        } catch (Exception ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
   

    public boolean updateDiagnose(Object diagnose, String encounterId) throws Exception {

        boolean res = true;
        initConnection();
        String sql = "UPDATE Encounter SET diagnosis='" + diagnose + "'" + "where encounter_id = " + encounterId;

        //System.out.println(sql);
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
             //stat.setString(1, encounterId);
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        } finally {
            closeConnection();
        }
        return res;
    }
    
    
      public Diagnose getDiagnose(String encounterId) throws Exception {

//        boolean res = true;
        initConnection();
        String sql = "select diagnosis from Encounter where encounter_id = ?;";
        Diagnose diag = null;
        //System.out.println(sql);
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, encounterId);
//            stat.executeUpdate(sql);
            ResultSet set = stat.executeQuery();
            while (set.next()) {
                String json = set.getString("diagnosis");
                diag = JSON.parseObject(json, Diagnose.class);
            }
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return diag;
    }
    
    

    public void closeConnection() throws Exception {
        conn.close();
    }

}
