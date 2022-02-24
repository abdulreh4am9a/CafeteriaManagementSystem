package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class changePassword extends JFrame implements ActionListener{
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel, lastPanel;
    private JLabel projectName, pageName, oldPass, email, newPass, confPass;
    private JTextField txtEmail;
    private JPasswordField txtOldPassword, txtNewPassword, txtConfPassword;
    private Font f1, f2, f3;
    private JButton btnLogin, btnUpdate;
    
    public changePassword(){
    	setTitle("Cafeteria Management System");
    	
    	topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,1));
        topPanel.setBounds(0,0,1366,200);
        topPanel.setBackground(new Color(255, 237, 211));
        projectName = new JLabel("CAFETERIA MANAGEMENT SYSTEM", SwingConstants.CENTER);
        f1 = new Font("Bookman Old Style", Font.BOLD, 48);
        projectName.setBackground(new Color(255, 237, 211));
        projectName.setForeground(new Color(18, 60, 105));
        projectName.setFont(f1);
        topPanel.add(projectName);

    	firstPanel = new JPanel();
    	firstPanel.setLayout(new GridLayout(1,1));
    	firstPanel.setBounds(0,200,1366,150);
    	firstPanel.setBackground(new Color(255, 237, 211));
        pageName = new JLabel("Change Password", SwingConstants.CENTER);
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
        pageName.setBackground(new Color(255, 237, 211));
        pageName.setForeground(new Color(18, 60, 105));
        pageName.setFont(f2);
        firstPanel.add(pageName);
    	
        secondPanel = new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	secondPanel.setBounds(0,350,1366,200);
    	secondPanel.setBackground(new Color(255, 237, 211));
    	f3 = new Font("Bookman Old Style", Font.PLAIN, 18);
    	email = new JLabel("Email:   ");
    	email.setBackground(new Color(255, 237, 211));
        email.setForeground(new Color(18, 60, 105));
        email.setFont(f3);
        txtEmail = new JTextField(15);
        txtEmail.setBackground(new Color(237, 199, 183));
        txtEmail.setForeground(new Color(18, 60, 105));
        txtEmail.setFont(f3);
        txtEmail.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        oldPass = new JLabel("Old Password:   ");
    	oldPass.setBackground(new Color(255, 237, 211));
    	oldPass.setForeground(new Color(18, 60, 105));
    	oldPass.setFont(f3);
        txtOldPassword = new JPasswordField(15);
        txtOldPassword.setBackground(new Color(237, 199, 183));
        txtOldPassword.setForeground(new Color(18, 60, 105));
        txtOldPassword.setFont(f3);
        txtOldPassword.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        newPass = new JLabel("New Password:   ");
        newPass.setBackground(new Color(255, 237, 211));
        newPass.setForeground(new Color(18, 60, 105));
        newPass.setFont(f3);
        txtNewPassword = new JPasswordField(15);
        txtNewPassword.setBackground(new Color(237, 199, 183));
        txtNewPassword.setForeground(new Color(18, 60, 105));
        txtNewPassword.setFont(f3);
        txtNewPassword.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        confPass = new JLabel("Confirm Password:   ");
        confPass.setBackground(new Color(255, 237, 211));
        confPass.setForeground(new Color(18, 60, 105));
        confPass.setFont(f3);
        txtConfPassword = new JPasswordField(15);
        txtConfPassword.setBackground(new Color(237, 199, 183));
        txtConfPassword.setForeground(new Color(18, 60, 105));
        txtConfPassword.setFont(f3);
        txtConfPassword.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.weightx = 0;
        c.weighty = 0.5;
        secondPanel.add(email, c);
        c.gridx=1;
        c.gridy=0;
        secondPanel.add(txtEmail, c);
        c.gridx=0;
        c.gridy=1;
        secondPanel.add(oldPass, c);
        c.gridx=1;
        c.gridy=1;
        secondPanel.add(txtOldPassword, c);
        c.gridx=0;
        c.gridy=2;
        secondPanel.add(newPass, c);
        c.gridx=1;
        c.gridy=2;
        secondPanel.add(txtNewPassword, c);
        c.gridx=0;
        c.gridy=3;
        secondPanel.add(confPass, c);
        c.gridx=1;
        c.gridy=3;
        secondPanel.add(txtConfPassword, c);
        
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,550,1366,100);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(255, 237, 211));
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(18, 60, 105));
        btnLogin.setForeground(new Color(255, 237, 211));
        btnLogin.addActionListener(this);
        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(18, 60, 105));
        btnUpdate.setForeground(new Color(255, 237, 211));
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(0,50,0,55);
        bottomPanel.add(btnUpdate, c);
        c.gridx=1;
        c.gridy=0;
        c.insets=new Insets(0,0,0,0);
        bottomPanel.add(btnLogin, c);
        
        lastPanel = new JPanel();
        lastPanel.setLayout(new GridLayout(1,1));
        lastPanel.setBounds(0,650,1366,125);
        lastPanel.setBackground(new Color(255, 237, 211));
        
        add(topPanel);
        add(firstPanel);
        add(secondPanel);
        add(bottomPanel);
        add(lastPanel);

        setSize(1366,800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdate){
			String email = txtEmail.getText();
			String oldPassword = String.valueOf(txtOldPassword.getPassword());
			String newPassword = String.valueOf(txtNewPassword.getPassword());
			String confPassword = String.valueOf(txtConfPassword.getPassword());
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        ResultSet rs;
		        String query = "UPDATE `User` SET `password`=? WHERE `email`=? AND `password`=?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, newPassword);
		            ps.setString(2, email);
		            ps.setString(3, oldPassword);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Password Changed Successfully!" );
		            	System.out.println("Done");
		            	query = "SELECT * FROM `User` WHERE `email`=? AND `password`=?";
			            ps = con.prepareStatement(query);
			            ps.setString(1, email);
			            ps.setString(2, newPassword);
			            rs = ps.executeQuery();
			            if(rs.next())
			            {
			            	if(rs.getString(2).equals("Admin")) {
								dispose();
								new adminLogin();
							}
							else {
								dispose();
								new registeredCustomer();
							}
			            }
		            }
		            else {
		            	JOptionPane.showMessageDialog(secondPanel,"Invalid Details!","Warning", JOptionPane.WARNING_MESSAGE);
		            }
		            con.close();
		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }
			
		}
		if(e.getSource() == btnLogin) {
			dispose();
    		new login();
		}
	}
    public void validateFields() {
    	String emailPattern = "^[^@\\s]+@([^\\.@\\s]+)(\\.[^\\.@\\s]+)+$";
    	String passPattern = "^([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\<\\>]){5,15}$";
    	if(txtEmail.getText().matches(emailPattern) && String.valueOf(txtOldPassword.getPassword()).matches(passPattern) && String.valueOf(txtNewPassword.getPassword()).matches(passPattern) && String.valueOf(txtConfPassword.getPassword()).equals(String.valueOf(txtNewPassword.getPassword()))) {
    		btnUpdate.setEnabled(true);
    	}
    	else {
    		btnUpdate.setEnabled(false);
    	}
    }
//    public static void main(String [] args)
//    {
//        changePassword obj = new changePassword();
//    }
}
