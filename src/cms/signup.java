package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signup extends JFrame implements ActionListener {
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel, lastPanel;
    private JLabel projectName, pageName, name, email, mobile, password;
    private JTextField txtName, txtEmail, txtMobile;
    private JPasswordField txtPassword;
    private Font f1, f2, f3;
    private JButton btnLogin, btnSignup;
    
    public signup(){
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
        pageName = new JLabel("Signup", SwingConstants.CENTER);
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
    	name = new JLabel("Name:   ");
    	name.setBackground(new Color(255, 237, 211));
        name.setForeground(new Color(18, 60, 105));
        name.setFont(f3);
        txtName = new JTextField(15);
        txtName.setBackground(new Color(237, 199, 183));
        txtName.setForeground(new Color(18, 60, 105));
        txtName.setFont(f3);
        txtName.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
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
        mobile = new JLabel("Mobile:   ");
    	mobile.setBackground(new Color(255, 237, 211));
        mobile.setForeground(new Color(18, 60, 105));
        mobile.setFont(f3);
        txtMobile = new JTextField(15);
        txtMobile.setBackground(new Color(237, 199, 183));
        txtMobile.setForeground(new Color(18, 60, 105));
        txtMobile.setFont(f3);
        txtMobile.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        password = new JLabel("Password:   ");
    	password.setBackground(new Color(255, 237, 211));
        password.setForeground(new Color(18, 60, 105));
        password.setFont(f3);
        txtPassword = new JPasswordField(15);
        txtPassword.setBackground(new Color(237, 199, 183));
        txtPassword.setForeground(new Color(18, 60, 105));
        txtPassword.setFont(f3);
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
        secondPanel.add(name, c);
        c.gridx=1;
        c.gridy=0;
        secondPanel.add(txtName, c);
        c.gridx=0;
        c.gridy=1;
        secondPanel.add(email, c);
        c.gridx=1;
        c.gridy=1;
        secondPanel.add(txtEmail, c);
        c.gridx=0;
        c.gridy=2;
        secondPanel.add(mobile, c);
        c.gridx=1;
        c.gridy=2;
        secondPanel.add(txtMobile, c);
        c.gridx=0;
        c.gridy=3;
        secondPanel.add(password, c);
        c.gridx=1;
        c.gridy=3;
        secondPanel.add(txtPassword, c);
        
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,550,1366,100);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(255, 237, 211));
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(18, 60, 105));
        btnLogin.setForeground(new Color(255, 237, 211));
        btnLogin.addActionListener(this);
        btnSignup = new JButton("Signup");
        btnSignup.setBackground(new Color(18, 60, 105));
        btnSignup.setForeground(new Color(255, 237, 211));
        btnSignup.setEnabled(false);
        btnSignup.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(0,50,0,55);
        bottomPanel.add(btnSignup, c);
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
		if (e.getSource() == btnLogin){
			dispose();
	        new login();
		}
		if(e.getSource() == btnSignup) {
			String n = txtName.getText();
			if(n.equals("Admin")) {
				JOptionPane.showMessageDialog(null,"Name can not be Admin!","Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
			String em = txtEmail.getText();
			String m = txtMobile.getText();
			String pass = String.valueOf(txtPassword.getPassword());
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
	        	ResultSet rs;
	        	String query = "SELECT * FROM `User` WHERE `email` =?";
	            ps = con.prepareStatement(query);
	            ps.setString(1, em); 
	            rs = ps.executeQuery();
	            if(rs.next())
	            {
	            	int result = JOptionPane.showConfirmDialog(null, "An account with this email already exists. Do you want to login?");
		            switch(result) {
		            case JOptionPane.YES_OPTION:
		            	dispose();
		            	new login();
		                break;
		                case JOptionPane.NO_OPTION:
		                break;
		                case JOptionPane.CANCEL_OPTION:
		                break;
		                case JOptionPane.CLOSED_OPTION:
		                break;
		            }
	            }
	            else {
		        query = "INSERT INTO `User` (`userName`, `email`, `mobile`, `password`) VALUES (?,?,?,?)";
		            ps = con.prepareStatement(query);
		            ps.setString(1, n);
		            ps.setString(2, em);
		            ps.setString(3, m);
		            ps.setString(4, pass);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Account created Successfully!" );
		            	dispose();
		            	new registeredCustomer();
		            }
	            }
		            con.close();
		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }
			}
		}
	}
    public void validateFields() {
    	String namePattern = "^([a-zA-Z\\s]){3,30}$";
    	String mobilePattern = "^(\\+923|00923|03)[0-4][0-9]\\-?\\d{7}$";
    	String emailPattern = "^[^@\\s]+@([^\\.@\\s]+)(\\.[^\\.@\\s]+)+$";
    	String passPattern = "^([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\<\\>]){5,15}$";
    	if(txtName.getText().matches(namePattern) && txtMobile.getText().matches(mobilePattern) && txtEmail.getText().matches(emailPattern) && String.valueOf(txtPassword.getPassword()).matches(passPattern)) {
    		btnSignup.setEnabled(true);
    	}
    	else {
    		btnSignup.setEnabled(false);
    	}
    }
//    public static void main(String [] args)
//    {
//        signup obj = new signup();
//    }
}
