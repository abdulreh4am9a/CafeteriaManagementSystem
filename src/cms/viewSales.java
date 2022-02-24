package cms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class viewSales extends JFrame{
    //private JFrame frame;
    private JPanel topPanel, firstPanel, datePanel, secondPanel, bottomPanel, lastPanel;
    private JLabel projectName, pageName, filter, instruction;
    //private JTextField date;
    private JTable orders;
    private JButton btnBack;
    private Font f1, f2, f3;
    
    public viewSales(){
    	setTitle("Cafeteria Management System");
    	
    	topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,1));
        topPanel.setBounds(0,0,1366,130);
        topPanel.setBackground(new Color(255, 237, 211));
        projectName = new JLabel("CAFETERIA MANAGEMENT SYSTEM", SwingConstants.CENTER);
        f1 = new Font("Bookman Old Style", Font.BOLD, 48);
        projectName.setBackground(new Color(255, 237, 211));
        projectName.setForeground(new Color(18, 60, 105));
        projectName.setFont(f1);
        topPanel.add(projectName);

    	firstPanel = new JPanel();
    	firstPanel.setLayout(new GridLayout(1,1));
    	firstPanel.setBounds(0,130,1366,50);
    	firstPanel.setBackground(new Color(255, 237, 211));
        pageName = new JLabel("View Sales", SwingConstants.CENTER);
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
        pageName.setBackground(new Color(255, 237, 211));
        pageName.setForeground(new Color(18, 60, 105));
        pageName.setFont(f2);
        firstPanel.add(pageName);
        
        secondPanel = new JPanel();
        secondPanel.setLayout(null);
    	secondPanel.setBounds(0,180,1366,500);
    	secondPanel.setBackground(new Color(255, 237, 211));
        String cols[] = {"Order ID","Name","Email","Mobile", "Date", "Total"};
        DefaultTableModel daDefaultTableModel = new DefaultTableModel(0,0);
        daDefaultTableModel.setColumnIdentifiers(cols);
        try{
        	Connection con = dbconnection.conn();
        	PreparedStatement ps;
	        ResultSet rs;
	        String query = "SELECT * FROM `Order`";
            ps = con.prepareStatement(query); 
            rs = ps.executeQuery();
            while(rs.next())
	        {
            	int id = Integer.parseInt(rs.getString(1));
            	String name = rs.getString(5);
            	String email = rs.getString(2);
            	String mobile = rs.getString(3);
            	String date = rs.getString(4);
            	double total = 0;
            	String q = "SELECT * FROM `ItemList` WHERE `orderID` =?";
            	PreparedStatement ps1;
    	        ResultSet rs1;
    	        ps1 = con.prepareStatement(q);
    	        //System.out.println(rs.getString(1));
    	        ps1.setInt(1, id);
	            rs1 = ps1.executeQuery();
	            while(rs1.next()) {
	            	int itemid = Integer.parseInt(rs1.getString(2));
	            	int quantity = Integer.parseInt(rs1.getString(3));
	            	String q1 = "SELECT * FROM `FoodItem` WHERE `itemID` =?";
	            	PreparedStatement ps2;
	    	        ResultSet rs2;
	    	        ps2 = con.prepareStatement(q1);
	    	        ps2.setInt(1, itemid);
		            rs2 = ps2.executeQuery();
		            if(rs2.next()) {
		            	double price = Double.parseDouble(rs2.getString(3));
		            	total+= price*quantity;
		            }
	            }
                
	        	Object obj[] = new Object[] {
	        		id,name,email,mobile,date,total	
	        	};
	        	daDefaultTableModel.addRow(obj);
	        }
	        con.close();
	        } catch (SQLException ex) {
	        	System.out.println(ex);
	        }
        orders = new JTable(daDefaultTableModel) {
        	public boolean isCellEditable(int rows, int cols) {
        		return false;
        	}
        };
        orders.setBackground(new Color(237, 199, 183));
		orders.setForeground(new Color(18, 60, 105));
		orders.setFont(f3);
		orders.getColumnModel().getColumn(0).setPreferredWidth(500);
		orders.getColumnModel().getColumn(1).setPreferredWidth(1000);
		orders.getColumnModel().getColumn(2).setPreferredWidth(1000);
		orders.getColumnModel().getColumn(3).setPreferredWidth(1000);
		orders.getColumnModel().getColumn(4).setPreferredWidth(1000);
		orders.getColumnModel().getColumn(5).setPreferredWidth(1000);
		JScrollPane scrollPane = new JScrollPane(orders);
		scrollPane.setVisible(true);
		scrollPane.setBounds(183, 0, 1000, 500);
		secondPanel.add(scrollPane);
        
        lastPanel = new JPanel();
        lastPanel.setLayout(new GridBagLayout());
        lastPanel.setBounds(0,680,1366,120);
        lastPanel.setBackground(new Color(255, 237, 211));
        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(18, 60, 105));
        btnBack.setForeground(new Color(255, 237, 211));
        btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	        	new adminLogin();
			}
        });
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        lastPanel.add(btnBack, c);
        
        add(topPanel);
        add(firstPanel);
        add(secondPanel);
        add(lastPanel);

        setSize(1366,800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String [] args)
//    {
//        viewSales obj = new viewSales();
//    }
}
