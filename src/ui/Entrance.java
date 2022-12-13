/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dao.DoctorDao;
import dao.LoginDao;
import dao.PatientDao;
import static java.awt.Image.SCALE_DEFAULT;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Doctor;
import model.Patient;
import model.User;
import ui.community.CommunityCRUD;
import ui.deliver.DeliverManagePanel;
import ui.deliver.DeliverPanel;
import ui.doctors.DoctorHomePage;
import ui.goods.GoodsCrud;
import ui.hospital.HospitalCrud;
import ui.patient.AddPatientJFrame;
import ui.patient.Appointment;

/**
 *
 * @author ZeyuLiao
 */
public class Entrance extends javax.swing.JFrame {

    /**
     * Creates new form Entrance
     */
//    public void theme(){
//        SwingTheme swingTheme=new SwingTheme();
//        swingTheme.init();
//        
//    }
    static public ImageIcon logo = new ImageIcon("src//image//ImageLogo.png");

    public Entrance() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabelSlogan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxUserType = new javax.swing.JComboBox<>();
        txtpwd = new javax.swing.JTextField();
        jButtonlogin = new javax.swing.JButton();
        jLabelSignUP = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OnlineHospitalSystem");
        setIconImage(logo.getImage());
        setLocation(new java.awt.Point(0, 0));

        jPanel2.setBackground(new java.awt.Color(149, 177, 182));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jLabelSlogan.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        jLabelSlogan.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSlogan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSlogan.setText("SERVE ANY TIME");
        jPanel2.add(jLabelSlogan);
        jLabelSlogan.setBounds(120, 540, 280, 40);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("User type:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(100, 260, 93, 17);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("User ID: ");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(110, 300, 93, 17);

        jComboBoxUserType.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBoxUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patient", "Doctors", "Others" }));
        jPanel1.add(jComboBoxUserType);
        jComboBoxUserType.setBounds(200, 260, 190, 23);

        txtpwd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txtpwd);
        txtpwd.setBounds(200, 350, 190, 23);

        jButtonlogin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButtonlogin.setText("Login");
        jButtonlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonloginActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonlogin);
        jButtonlogin.setBounds(169, 445, 185, 40);

        jLabelSignUP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabelSignUP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSignUP.setText("Don't have account? Sign up here!");
        jLabelSignUP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSignUPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelSignUPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelSignUPMouseExited(evt);
            }
        });
        jPanel1.add(jLabelSignUP);
        jLabelSignUP.setBounds(169, 514, 195, 17);
        jPanel1.add(jLabelLogo);
        jLabelLogo.setBounds(80, 90, 340, 70);
        ImageIcon logoHospital = new ImageIcon("src//image//Logo.png");
        logoHospital.setImage(logoHospital.getImage().getScaledInstance(jLabelLogo.getWidth(),jLabelLogo.getHeight(),SCALE_DEFAULT));
        jLabelLogo.setIcon(logoHospital);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(220, 410, 78, 23);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Password:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(110, 360, 93, 17);

        txtUserID.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(txtUserID);
        txtUserID.setBounds(200, 300, 190, 23);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(530, 10, 460, 580);
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 530, 540);
        ImageIcon bgDoctor = new ImageIcon("src//image//doctors.png");
        bgDoctor.setImage(bgDoctor.getImage().getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(),SCALE_DEFAULT));
        jLabel1.setIcon(bgDoctor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonloginActionPerformed
        try {
            // TODO add your handling code here:
            LoginDao ldao = new LoginDao();
            ldao.initConnection();
            if (txtUserID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Please input your id!");
                return;
            }
            MainMenu menu = new MainMenu();
            switch (jComboBoxUserType.getSelectedIndex()) {
                case 0:
                    Patient pRes = ldao.isValidPatient(Integer.parseInt(txtUserID.getText()), txtpwd.getText());
                    if (pRes != null) {
                        menu.MainMenu(0, Integer.parseInt(txtUserID.getText()));
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Wrong account No. or wrong password!");
                    }

                    return;

                case 1:
                    Doctor dRes = ldao.isValidDoctor(Integer.parseInt(txtUserID.getText()), txtpwd.getText());
                    if (dRes != null) {
                        menu.MainMenu(1, Integer.parseInt(txtUserID.getText()));
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Wrong account No. or wrong password!");
                    }

                    return;

                case 2:
                    User rRes = ldao.isValidOtherUser(Integer.parseInt(txtUserID.getText()), txtpwd.getText());
                    if (rRes != null) {
                        switch (rRes.getRole()) {
                            case "Sysadmin":
                                menu.MainMenu(4, Integer.parseInt(txtUserID.getText()));
                                dispose();
                                return;
                            case "CommunityAdmin":
                                menu.MainMenu(3, Integer.parseInt(txtUserID.getText()));
                                dispose();
                                return;

                            case "HospitalAdmin":
                                menu.MainMenu(2, Integer.parseInt(txtUserID.getText()));
                                dispose();
                                return;

                            case "DeliveryAdmin":
                                menu.MainMenu(5, Integer.parseInt(txtUserID.getText()));
                                dispose();
                                return;

                            case "GoodsAdmin":
                                menu.MainMenu(6, Integer.parseInt(txtUserID.getText()));
                                dispose();
                                return;

                            case "DHL":
                                new DeliverPanel(rRes.getName(), rRes.getRole());

                            case "FedEx":
                                

                            case "Express":

                            case "UPS":

                        }

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Wrong account No. or wrong password!");
                    }
                    break;

                default:
                    break;
            }

        } catch (Exception ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonloginActionPerformed

    private void jLabelSignUPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSignUPMouseEntered
        // TODO add your handling code here:
        jLabelSignUP.setForeground(new java.awt.Color(51, 51, 255));
    }//GEN-LAST:event_jLabelSignUPMouseEntered

    private void jLabelSignUPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSignUPMouseExited
        // TODO add your handling code here:
        jLabelSignUP.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_jLabelSignUPMouseExited

    private void jLabelSignUPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSignUPMouseClicked
        // TODO add your handling code here:
        AddPatientJFrame add = new AddPatientJFrame();
        add.setVisible(true);
    }//GEN-LAST:event_jLabelSignUPMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String Delivery_NO = "NotAssigned";
        try {
            new DeliverPanel("Zeyu Liao", "DHL");
        } catch (Exception ex) {
            Logger.getLogger(Entrance.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error Theme");
            e.printStackTrace();
        }
        // /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entrance().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonlogin;
    private javax.swing.JComboBox<String> jComboBoxUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelSignUP;
    private javax.swing.JLabel jLabelSlogan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtpwd;
    // End of variables declaration//GEN-END:variables
}
