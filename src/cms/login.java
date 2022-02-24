package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener{
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel, lastPanel;
    private JLabel projectName, pageName, email, password;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private Font f1, f2, f3;
    private JButton btnLogin, btnForgot, btnSignup;
    
    public login(){
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
        pageName = new JLabel("Login", SwingConstants.CENTER);
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
        pageName.setBackground(new Color(255, 237, 211));
        pageName.setForeground(new Color(18, 60, 105));
        pageName.setFont(f2);
        firstPanel.add(pageName);
    	
        secondPanel = new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	secondPanel.setBounds(0,350,1366,100);
    	secondPanel.setBackground(new Color(255, 237, 211));
    	f3 = new Font("Bookman Old Style", Font.PLAIN, 18);
        email = new JLabel("Email:   ");
    	email.setBackground(new Color(255, 237, 211));
        email.setForeground(new Color(18, 60, 105));
        email.setFont(f3);
        txtEmail = new JTextField(15);
        txtEmail.setBackground(new Color(237, 199, 183));
        txtEmail.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        txtEmail.setForeground(new Color(18, 60, 105));
        txtEmail.setFont(f3);
        txtEmail.addActionListener(this);
        password = new JLabel("Password:   ");
    	password.setBackground(new Color(255, 237, 211));
        password.setForeground(new Color(18, 60, 105));
        password.setFont(f3);
        txtPassword = new JPasswordField(15);
        txtPassword.setBackground(new Color(237, 199, 183));
        txtPassword.setForeground(new Color(18, 60, 105));
        txtPassword.setFont(f3);
        //txtPassword.addActionListener(this);
        txtPassword.addKeyListener(new KeyListener() {
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
        secondPanel.add(password, c);
        c.gridx=1;
        c.gridy=1;
        secondPanel.add(txtPassword, c);
        
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,450,1366,100);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(255, 237, 211));
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(18, 60, 105));
        btnLogin.setForeground(new Color(255, 237, 211));
        btnLogin.setEnabled(false);
        btnLogin.addActionListener(this);
        btnSignup = new JButton("Signup");
        btnSignup.setBackground(new Color(18, 60, 105));
        btnSignup.setForeground(new Color(255, 237, 211));
        btnSignup.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(0,20,0,0);
        bottomPanel.add(btnLogin, c);
        c.gridx=1;
        c.gridy=0;
        bottomPanel.add(btnSignup, c);

        lastPanel = new JPanel();
        lastPanel.setLayout(new GridLayout(1,1));
        lastPanel.setBounds(0,550,1366,225);
        lastPanel.setBackground(new Color(255, 237, 211));
        
        add(topPanel);
        add(firstPanel);
        add(secondPanel);
        add(bottomPanel);
        add(lastPanel);

        setSize(1366,800); //width and height
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void validateFields() {
    	String emailPattern = "^[^@\\s]+@([^\\.@\\s]+)(\\.[^\\.@\\s]+)+$";
    	String passPattern = "^([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\<\\>]){5,15}$";
    	if(txtEmail.getText().matches(emailPattern) && String.valueOf(txtPassword.getPassword()).matches(passPattern)) {
    		btnLogin.setEnabled(true);
    	}
    	else {
    		btnLogin.setEnabled(false);
    	}
    }
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin){
			String email = txtEmail.getText();
			String password = String.valueOf(txtPassword.getPassword());
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        ResultSet rs;
		        String query = "SELECT * FROM `User` WHERE `email` =? AND `password` =?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, email);
		            ps.setString(2, password); 
		            rs = ps.executeQuery();
		            if(rs.next())
		            {
		            	JOptionPane.showMessageDialog( null, "Login Successfull!" );
		            	if(rs.getString(2).equals("Admin")) {
							dispose();
							new adminLogin();
						}
						else {
							dispose();
							new registeredCustomer();
						}
		            }
		            else {
		            	JOptionPane.showMessageDialog(secondPanel,"Invalid Login Details!","Warning", JOptionPane.WARNING_MESSAGE);
		            }
		            con.close();
		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }
			
		}
		if(e.getSource() == btnSignup) {
			dispose();
    		new signup();
		}
	}
//    public static void main(String [] args)
//    {
//        login obj = new login();
//    }
}
