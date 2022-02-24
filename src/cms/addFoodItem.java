package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addFoodItem extends JFrame implements ActionListener{
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel, lastPanel;
    private JLabel projectName, pageName, name, price;
    private JTextField txtName, txtPrice;
    private Font f1, f2, f3;
    private JButton btnSave, btnBack;
    
    public addFoodItem(){
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
        pageName = new JLabel("Add Food Item", SwingConstants.CENTER);
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
        price = new JLabel("Price:   ");
    	price.setBackground(new Color(255, 237, 211));
        price.setForeground(new Color(18, 60, 105));
        price.setFont(f3);
        txtPrice = new JTextField(15);
        txtPrice.setBackground(new Color(237, 199, 183));
        txtPrice.setForeground(new Color(18, 60, 105));
        txtPrice.setFont(f3);
        txtPrice.addKeyListener(new KeyListener() {
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
        secondPanel.add(price, c);
        c.gridx=1;
        c.gridy=1;
        secondPanel.add(txtPrice, c);
        
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0,450,1366,100);
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
        c.insets=new Insets(0,20,0,0);
        bottomPanel.add(btnSave, c);
        c.gridx=1;
        c.gridy=0;
        //c.insets=new Insets(0,0,0,55);
        bottomPanel.add(btnBack, c);
        
        lastPanel = new JPanel();
        lastPanel.setLayout(new GridLayout(1,1));
        lastPanel.setBounds(0,550,1366,225);
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
			Double price = Double.parseDouble(txtPrice.getText());
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        String query = "INSERT INTO `FoodItem` (`itemName`, `price`) VALUES (?,?)";
		            ps = con.prepareStatement(query);
		            ps.setString(1, name);
		            ps.setDouble(2, price);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Food Item Added Successfully!" );
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
    	String pricePattern = "^([0-9.]){2,15}$";
    	if(txtName.getText().matches(namePattern) && txtPrice.getText().matches(pricePattern)) {
    		btnSave.setEnabled(true);
    	}
    	else {
    		btnSave.setEnabled(false);
    	}
    }
//    public static void main(String [] args)
//    {
//    	addFoodItem obj = new addFoodItem();
//    }
}
