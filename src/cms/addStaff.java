package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addStaff extends JFrame implements ActionListener{
    //private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel, lastPanel;
    private JLabel projectName, pageName, name, gender, desig;
    private JTextField txtName, txtDesig;
    private JComboBox txtGender;
    private Font f1, f2, f3;
    private JButton btnSave, btnBack;
    
    public addStaff(){
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
        pageName = new JLabel("Add Staff", SwingConstants.CENTER);
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
        gender = new JLabel("Gender:   ");
        gender.setBackground(new Color(255, 237, 211));
        gender.setForeground(new Color(18, 60, 105));
        gender.setFont(f3);
        String genders[] = {"Male", "Female", "Other"};
        txtGender = new JComboBox(genders);
        txtGender.setBackground(new Color(237, 199, 183));
        txtGender.setForeground(new Color(18, 60, 105));
        txtGender.setFont(f3);
        desig = new JLabel("Designation:   ");
        desig.setBackground(new Color(255, 237, 211));
        desig.setForeground(new Color(18, 60, 105));
        desig.setFont(f3);
        txtDesig = new JTextField(15);
        txtDesig.setBackground(new Color(237, 199, 183));
        txtDesig.setForeground(new Color(18, 60, 105));
        txtDesig.setFont(f3);
        txtDesig.addKeyListener(new KeyListener() {
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
        secondPanel.add(gender, c);
        c.gridx=1;
        c.gridy=1;
        c.fill = GridBagConstraints.HORIZONTAL;
        secondPanel.add(txtGender, c);
        c.gridx=0;
        c.gridy=2;
        c.fill = 0;
        secondPanel.add(desig, c);
        c.gridx=1;
        c.gridy=2;
        secondPanel.add(txtDesig, c);
        
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,550,1366,100);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(new Color(255, 237, 211));
        btnSave = new JButton("Save");
        btnSave.setBackground(new Color(18, 60, 105));
        btnSave.setForeground(new Color(255, 237, 211));
        btnSave.setEnabled(false);
        btnSave.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(18, 60, 105));
        btnBack.setForeground(new Color(255, 237, 211));
        btnBack.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        c.insets=new Insets(0,20,0,20);
        bottomPanel.add(btnSave, c);
        c.gridx=1;
        c.gridy=0;
        //c.insets=new Insets(0,0,0,55);
        bottomPanel.add(btnBack, c);
        
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
		if (e.getSource() == btnSave){
			String name = txtName.getText();
			String gender = String.valueOf(txtGender.getSelectedItem());
			String designation = txtDesig.getText();
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        String query = "INSERT INTO `Staff` (`staffName`, `gender`, `designation`) VALUES (?,?,?)";
		            ps = con.prepareStatement(query);
		            ps.setString(1, name);
		            ps.setString(2, gender);
		            ps.setString(3, designation);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Staff Member Added Successfully!" );
		            	dispose();
						new adminLogin();
		            }
		            con.close();
		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }
			
		}
		if(e.getSource() == btnBack) {
			dispose();
    		new adminLogin();
		}
	}
    public void validateFields() {
    	String namePattern = "^([a-zA-Z\\s]){3,30}$";
    	if(txtName.getText().matches(namePattern) && txtDesig.getText().matches(namePattern)) {
    		btnSave.setEnabled(true);
    	}
    	else {
    		btnSave.setEnabled(false);
    	}
    }
//    public static void main(String [] args)
//    {
//    	addStaff obj = new addStaff();
//    }
}
