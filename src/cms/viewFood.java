package cms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewFood extends JFrame implements ActionListener{
    //private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel;
    private JLabel projectName, pageName, id, name, price;
    private JTextField txtid, txtName, txtPrice;
    private JButton btnUpdate, btnDelete, btnBack;
    private JTable food;
    private Font f1, f2, f3;
    
    public viewFood(){
    	setTitle("Cafeteria Management System");
    	
    	topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,1));
        topPanel.setBounds(0,0,1366,150);
        topPanel.setBackground(new Color(255, 237, 211));
        projectName = new JLabel("CAFETERIA MANAGEMENT SYSTEM", SwingConstants.CENTER);
        f1 = new Font("Bookman Old Style", Font.BOLD, 48);
        projectName.setBackground(new Color(255, 237, 211));
        projectName.setForeground(new Color(18, 60, 105));
        projectName.setFont(f1);
        topPanel.add(projectName);

    	firstPanel = new JPanel();
    	firstPanel.setLayout(new GridLayout(1,1));
    	firstPanel.setBounds(0,150,1366,80);
    	firstPanel.setBackground(new Color(255, 237, 211));
        pageName = new JLabel("View, Edit & Delete Food Item", SwingConstants.CENTER);
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
        pageName.setBackground(new Color(255, 237, 211));
        pageName.setForeground(new Color(18, 60, 105));
        pageName.setFont(f2);
        firstPanel.add(pageName);
    	
        secondPanel = new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	secondPanel.setBounds(0,230,1366,500);
    	secondPanel.setBackground(new Color(255, 237, 211));
    	f3 = new Font("Bookman Old Style", Font.PLAIN, 18);
    	id = new JLabel("Id:   ");
    	id.setBackground(new Color(255, 237, 211));
        id.setForeground(new Color(18, 60, 105));
        id.setFont(f3);
        txtid = new JTextField(10);
        txtid.setBackground(new Color(237, 199, 183));
        txtid.setForeground(new Color(18, 60, 105));
        txtid.setFont(f3);
        txtid.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
    	name = new JLabel("Name:   ");
    	name.setBackground(new Color(255, 237, 211));
        name.setForeground(new Color(18, 60, 105));
        name.setFont(f3);
        txtName = new JTextField(10);
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
        txtPrice = new JTextField(10);
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
        DefaultTableModel daDefaultTableModel = new DefaultTableModel(0, 0);
        String cols[] = {"ID","Name","Price"};
        daDefaultTableModel.setColumnIdentifiers(cols);
        try{
        	Connection con = dbconnection.conn();
        	PreparedStatement ps;
	        ResultSet rs;
	        String query = "SELECT * FROM `FoodItem`";
            ps = con.prepareStatement(query); 
            rs = ps.executeQuery();
            while(rs.next())
	        {
	        	Object obj[] = new Object[] {
	        		rs.getString(1),rs.getString(2),rs.getString(3)	
	        	};
	        	daDefaultTableModel.addRow(obj);
	        }
	        con.close();
	        } catch (SQLException ex) {
	        	System.out.println(ex);
	        }
        food = new JTable(daDefaultTableModel) {
        	public boolean isCellEditable(int rows, int cols) {
        		return false;
        	}
        };
        food.setBackground(new Color(237, 199, 183));
        food.setForeground(new Color(18, 60, 105));
        food.setFont(f3);
        food.getColumnModel().getColumn(0).setPreferredWidth(100);
        food.getColumnModel().getColumn(1).setPreferredWidth(300);
        food.getColumnModel().getColumn(2).setPreferredWidth(250);
		JScrollPane scrollPane = new JScrollPane(food);
		scrollPane.setVisible(true);
        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(18, 60, 105));
        btnUpdate.setForeground(new Color(255, 237, 211));
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(this);
        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(18, 60, 105));
        btnDelete.setForeground(new Color(255, 237, 211));
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(18, 60, 105));
        btnBack.setForeground(new Color(255, 237, 211));
        btnBack.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets =new Insets(0,30,0,10);
        secondPanel.add(id, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        secondPanel.add(txtid, c);
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 7;
        c.gridwidth = 3;
        c.weightx = 0.1;
        c.insets =new Insets(0,100,0,100);
        secondPanel.add(scrollPane, c);
        c.gridx=0;
        c.gridy=1;
        c.weightx=0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets =new Insets(0,30,0,10);
        secondPanel.add(name,c);
        c.gridx=1;
        c.gridy=1;
        c.gridwidth = 2;
        secondPanel.add(txtName,c);
        c.gridx=0;
        c.gridy=2;
        c.weightx=0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets =new Insets(0,30,0,10);
        secondPanel.add(price,c);
        c.gridx=1;
        c.gridy=2;
        c.gridwidth = 2;
        secondPanel.add(txtPrice,c);
        c.gridx=0;
        c.gridy=3;
        c.gridwidth = 1;
        //c.insets =new Insets(0,0,0,0);
        c.fill = GridBagConstraints.NONE;
        secondPanel.add(btnUpdate,c);
        c.gridx=1;
        c.gridy=3;
        secondPanel.add(btnDelete,c);
        c.gridx=2;
        c.gridy=3;
        //c.gridwidth = 2;
        secondPanel.add(btnBack,c);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.setBounds(0, 600,1366,200);
        bottomPanel.setBackground(new Color(255, 237, 211));
        
        add(topPanel);
        add(firstPanel);
        add(secondPanel);
        add(bottomPanel);

        setSize(1366,800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdate){
			int id = Integer.parseInt(txtid.getText());
			String name = txtName.getText();
			Double price = Double.parseDouble(txtPrice.getText());
			try{
				Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        String query = "UPDATE `FoodItem` SET `itemName`=?, `price`=? WHERE `itemID`=?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, name);
		            ps.setDouble(2, price);
		            ps.setInt(3, id);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Food Item Updated Successfully!" );
		            	dispose();
						new viewFood();
		            }
	            	else {
	            		JOptionPane.showMessageDialog(null,"Invalid ID!","Warning", JOptionPane.WARNING_MESSAGE);
	            	}
		            con.close();
		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }
			
		}
		if (e.getSource() == btnDelete){
			int id = Integer.parseInt(txtid.getText());
			try{
				Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        String query = "DELETE FROM `FoodItem` WHERE `itemID`=?";
		            ps = con.prepareStatement(query);
		            ps.setInt(1, id);
		            int result = JOptionPane.showConfirmDialog(null, "Do you want to delete this food item?");
		            switch(result) {
		            case JOptionPane.YES_OPTION:
		            	if(ps.executeUpdate()>0)
			            {
			            	dispose();
							new viewFood();
			            }
		            	else {
		            		JOptionPane.showMessageDialog(null,"Invalid ID!","Warning", JOptionPane.WARNING_MESSAGE);
		            	}
		                break;
		                case JOptionPane.NO_OPTION:
		                break;
		                case JOptionPane.CANCEL_OPTION:
		                break;
		                case JOptionPane.CLOSED_OPTION:
		                break;
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
    	String idPattern = "^([0-9])*$";
    	String namePattern = "^([a-zA-Z\\s]){3,30}$";
    	String pricePattern = "^([0-9.]){2,15}$";
    	if(txtid.getText().matches(idPattern) && txtName.getText().matches(namePattern) && txtPrice.getText().matches(pricePattern)) {
    		btnUpdate.setEnabled(true);
    	}
    	else {
    		btnUpdate.setEnabled(false);
    	}
    	if(txtid.getText().matches(idPattern)) {
    		btnDelete.setEnabled(true);
    	}
    	else {
    		btnDelete.setEnabled(true);
    	}
    }
//    public static void main(String [] args)
//    {
//        viewFood obj = new viewFood();
//    }
}
