package cms;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class placeOrder extends JFrame implements ActionListener{
    //private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel;
    private JLabel projectName, pageName, orderID, orderType, name, price, customerDetails, cName, search, quantity, total, mobile, email, totalText;
    private JTextField txtName, txtPrice, txtCName, txtSearch, txtQuantity, txtTotal, txtMobile, txtEmail;
    private JButton addToCart, generateBill, btnBack;
    private JComboBox type;
    private JList itemList, b;
    //private JTable bill;
    private Font f1, f2, f3;
    private int orderId;
    private DefaultListModel cartModel;
    private double totBill;
    
    public placeOrder(){
    	setTitle("Cafeteria Management System");
    	orderId = 0;
    	totBill = 0;
    	try{
        	Connection con = dbconnection.conn();
        	PreparedStatement ps;
        	ResultSet rs;
        	String q = "SELECT * FROM `Order` WHERE `name` =?";
            ps = con.prepareStatement(q);
            ps.setString(1, "Random");
            rs = ps.executeQuery();
            if(rs.next())
            {
            	orderId = Integer.parseInt(rs.getString(1));
            }
            else {
	        String query = "INSERT INTO `Order` (`name`) VALUES (?)";
	            ps = con.prepareStatement(query);
	            ps.setString(1, "Random");
	            if(ps.executeUpdate()>0)
	            {
	            	q = "SELECT * FROM `Order` WHERE `name` =?";
		            ps = con.prepareStatement(q);
		            ps.setString(1, "Random");
		            rs = ps.executeQuery();
		            if(rs.next())
		            {
		            	orderId = Integer.parseInt(rs.getString(1));
		            }
	            }
            }
	            con.close();
	        } catch (SQLException ex) {
	        	System.out.println(ex);
	        }
    	
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
    	firstPanel.setBounds(0,150,1366,100);
    	firstPanel.setBackground(new Color(255, 237, 211));
        pageName = new JLabel("Place Order", SwingConstants.CENTER);
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
        pageName.setBackground(new Color(255, 237, 211));
        pageName.setForeground(new Color(18, 60, 105));
        pageName.setFont(f2);
        firstPanel.add(pageName);
    	
        secondPanel = new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	secondPanel.setBounds(0,250,1366,500);
    	secondPanel.setBackground(new Color(255, 237, 211));
    	f3 = new Font("Bookman Old Style", Font.PLAIN, 18);
    	String types[] = {"Dine In", "Take Away"};
        type = new JComboBox(types);
        type.setBackground(new Color(237, 199, 183));
        type.setForeground(new Color(18, 60, 105));
        type.setFont(f3);
        DefaultListModel listModel = new DefaultListModel();
        try{
        	Connection con = dbconnection.conn();
        	PreparedStatement ps;
	        ResultSet rs;
	        String query = "SELECT * FROM `FoodItem`";
	            ps = con.prepareStatement(query);
	            rs = ps.executeQuery();
	            while(rs.next())
	            {
	            	String f = rs.getString(2);
	            	listModel.addElement(f);
	            }
	            con.close();
	        } catch (SQLException ex) {
	        	System.out.println(ex);
	        }
        itemList = new JList(listModel);
        itemList.setBackground(new Color(237, 199, 183));
        itemList.setForeground(new Color(18, 60, 105));
        itemList.setFont(f3);
        itemList.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				int index = itemList.getSelectedIndex();
				String x = listModel.get(index).toString();
				txtName.setText(x);
				validateFields();
				try{
		        	Connection con = dbconnection.conn();
		        	PreparedStatement ps;
			        ResultSet rs;
			        String n = txtName.getText();
			        String query = "SELECT * FROM `FoodItem` WHERE `itemName`=?";
			            ps = con.prepareStatement(query);
			            ps.setString(1, n);
			            rs = ps.executeQuery();
			            if(rs.next())
			            {
			            	String p = rs.getString(3);
			            	txtPrice.setText(p);
			            	if(txtQuantity.getText().equals("")) {
			            		txtQuantity.setText("1");
			            	}
			            	int q = Integer.parseInt(txtQuantity.getText());
							double pr = Double.parseDouble(txtPrice.getText());
							double t = q*pr;
							txtTotal.setText(String.valueOf(t));
			            }
			            else {
			            	txtPrice.setText("");
			            	txtQuantity.setText("");
			            	txtTotal.setText("");
			            }
			            con.close();
			        } catch (SQLException ex) {
			        	System.out.println(ex);
			        }
				validateFields();
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
        	
        });
        JScrollPane s = new JScrollPane(itemList);
		s.setVisible(true);
		cartModel = new DefaultListModel();
        b = new JList(cartModel);
        b.setBackground(new Color(237, 199, 183));
        b.setForeground(new Color(18, 60, 105));
        b.setFont(f3);
        b.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				int index = b.getSelectedIndex();
				String x = cartModel.get(index).toString();
				String[] words=x.split("\\s\\s\\s\\s");
		        x= words[1];
				int result = JOptionPane.showConfirmDialog(null, "Do you want to remove "+x+" from cart?");
	            switch(result) {
	            case JOptionPane.YES_OPTION:
					try{
						Connection con = dbconnection.conn();
			        	PreparedStatement ps;
				        ResultSet rs;
				        String query = "SELECT * FROM `FoodItem` WHERE `itemName`=?";
				        String[] qq = words[0].split("x");
				        int q = Integer.parseInt(qq[0]);
				        ps = con.prepareStatement(query);
				            ps.setString(1, x);
				            rs = ps.executeQuery();
				            if(rs.next())
				            {
				            	int i = rs.getInt(1);
				            	double p = rs.getDouble(3);
				            	double toremove = p*q;
				            	totBill-=toremove;
				            	totalText.setText("Total "+String.valueOf(totBill));
				            	query = "DELETE FROM `ItemList` WHERE `orderID`=? AND `itemID`=?";
					            ps = con.prepareStatement(query);
					            ps.setInt(1, orderId);
					            ps.setInt(2, i);
					            ps.executeUpdate();
					            con.close();
				            }
			        	
				        } catch (SQLException ex) {
				        	System.out.println(ex);
				        }
					cartModel.remove(index);
	                break;
	                case JOptionPane.NO_OPTION:
	                break;
	                case JOptionPane.CANCEL_OPTION:
	                break;
	                case JOptionPane.CLOSED_OPTION:
	                break;
	            }
				validateFields();
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
        	
        });
        JScrollPane scrollPaneTable = new JScrollPane(b);
		scrollPaneTable.setVisible(true);
    	
    	String txt = "Order ID: "+String.valueOf(orderId);
        orderID = new JLabel(txt, SwingConstants.LEFT);
        orderID.setFont(f3);
        orderID.setForeground(new Color(18, 60, 105));
        orderType = new JLabel("Order Type: ", SwingConstants.LEFT);
        orderType.setFont(f3);
        orderType.setForeground(new Color(18, 60, 105));
        name = new JLabel("Name:", SwingConstants.LEFT);
        name.setFont(f3);
        name.setForeground(new Color(18, 60, 105));
        price = new JLabel("Price:", SwingConstants.LEFT);
        price.setFont(f3);
        price.setForeground(new Color(18, 60, 105));
        customerDetails = new JLabel("Customer Details", SwingConstants.LEFT);
        customerDetails.setFont(f3);
        customerDetails.setForeground(new Color(18, 60, 105));
        cName = new JLabel("Name:", SwingConstants.LEFT);
        cName.setFont(f3);
        cName.setForeground(new Color(18, 60, 105));
        search = new JLabel("Search:", SwingConstants.LEFT);
        search.setFont(f3);
        search.setForeground(new Color(18, 60, 105));
        quantity = new JLabel("Quantity:", SwingConstants.LEFT);
        quantity.setFont(f3);
        quantity.setForeground(new Color(18, 60, 105));
        total = new JLabel("Total:", SwingConstants.LEFT);
        total.setFont(f3);
        total.setForeground(new Color(18, 60, 105));
        mobile = new JLabel("Mobile Number:", SwingConstants.LEFT);
        mobile.setFont(f3);
        mobile.setForeground(new Color(18, 60, 105));
        email = new JLabel("Email:", SwingConstants.LEFT);
        email.setFont(f3);
        email.setForeground(new Color(18, 60, 105));
        totalText = new JLabel("Total 0.00", SwingConstants.LEFT);
        totalText.setFont(f3);
        totalText.setForeground(new Color(18, 60, 105));
        txtName = new JTextField(8);
        txtName.setBackground(new Color(237, 199, 183));
        txtName.setForeground(new Color(18, 60, 105));
        txtName.setFont(f3);
        txtName.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
				try{
		        	Connection con = dbconnection.conn();
		        	PreparedStatement ps;
			        ResultSet rs;
			        String n = txtName.getText();
			        String query = "SELECT * FROM `FoodItem` WHERE `itemName`=?";
			            ps = con.prepareStatement(query);
			            ps.setString(1, n);
			            rs = ps.executeQuery();
			            if(rs.next())
			            {
			            	String p = rs.getString(3);
			            	txtPrice.setText(p);
			            	if(txtQuantity.getText().equals("")) {
			            		txtQuantity.setText("1");
			            	}
			            	int q = Integer.parseInt(txtQuantity.getText());
							double pr = Double.parseDouble(txtPrice.getText());
							double t = q*pr;
							txtTotal.setText(String.valueOf(t));
			            }
			            else {
			            	txtPrice.setText("");
			            	txtQuantity.setText("");
			            	txtTotal.setText("");
			            }
			            con.close();
			        } catch (SQLException ex) {
			        	System.out.println(ex);
			        }
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        txtPrice = new JTextField(8);
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
        txtPrice.setEditable(false);
        txtCName = new JTextField(8);
        txtCName.setBackground(new Color(237, 199, 183));
        txtCName.setForeground(new Color(18, 60, 105));
        txtCName.setFont(f3);
        txtCName.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        txtSearch = new JTextField(8);
        txtSearch.setBackground(new Color(237, 199, 183));
        txtSearch.setForeground(new Color(18, 60, 105));
        txtSearch.setFont(f3);
        txtSearch.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
				listModel.clear();
				try{
		        	Connection con = dbconnection.conn();
		        	PreparedStatement ps;
			        ResultSet rs;
			        String query = "SELECT * FROM `FoodItem` WHERE `itemName` LIKE '%"+txtSearch.getText()+"%'";
			            ps = con.prepareStatement(query);
			            rs = ps.executeQuery();
			            while(rs.next())
			            {
			            	String f = rs.getString(2);
			            	listModel.addElement(f);
			            }
			            con.close();
			        } catch (SQLException ex) {
			        	System.out.println(ex);
			        }
				if(txtSearch.getText().equals("")) {
					listModel.clear();
					try{
			        	Connection con = dbconnection.conn();
			        	PreparedStatement ps;
				        ResultSet rs;
				        String query = "SELECT * FROM `FoodItem`";
				            ps = con.prepareStatement(query);
				            rs = ps.executeQuery();
				            while(rs.next())
				            {
				            	String f = rs.getString(2);
				            	listModel.addElement(f);
				            }
				            con.close();
				        } catch (SQLException ex) {
				        	System.out.println(ex);
				        }
				}
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        txtQuantity = new JTextField(8);
        txtQuantity.setBackground(new Color(237, 199, 183));
        txtQuantity.setForeground(new Color(18, 60, 105));
        txtQuantity.setFont(f3);
        txtQuantity.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
				int q = Integer.parseInt(txtQuantity.getText());
				double p = Double.parseDouble(txtPrice.getText());
				double t = q*p;
				txtTotal.setText(String.valueOf(t));
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        txtTotal = new JTextField(8);
        txtTotal.setBackground(new Color(237, 199, 183));
        txtTotal.setForeground(new Color(18, 60, 105));
        txtTotal.setFont(f3);
        txtTotal.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        txtTotal.setEditable(false);
        txtMobile = new JTextField(8);
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
        txtEmail = new JTextField(8);
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
        addToCart = new JButton("Add to Cart");
        addToCart.setBackground(new Color(18, 60, 105));
        addToCart.setForeground(new Color(255, 237, 211));
        addToCart.setEnabled(false);
        addToCart.addActionListener(this);
        generateBill = new JButton("Generate Bill");
        generateBill.setBackground(new Color(18, 60, 105));
        generateBill.setForeground(new Color(255, 237, 211));
        generateBill.setEnabled(false);
        generateBill.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(18, 60, 105));
        btnBack.setForeground(new Color(255, 237, 211));
        btnBack.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets =new Insets(0,20,0,20);
        secondPanel.add(orderID, c);
        c.gridx = 1;
        c.gridy = 0;
        secondPanel.add(orderType, c);
        c.gridx = 2;
        c.gridy = 0;
        secondPanel.add(name, c);
        c.gridx = 3;
        c.gridy = 0;
        secondPanel.add(price, c);
        c.gridx = 0;
        c.gridy = 1;
        secondPanel.add(customerDetails, c);
        c.gridx = 1;
        c.gridy = 1;
        secondPanel.add(type, c);
        c.gridx = 2;
        c.gridy = 1;
        secondPanel.add(txtName, c);
        c.gridx = 3;
        c.gridy = 1;
        secondPanel.add(txtPrice, c);
        c.gridx = 0;
        c.gridy = 2;
        secondPanel.add(cName, c);
        c.gridx = 1;
        c.gridy = 2;
        secondPanel.add(search, c);
        c.gridx = 2;
        c.gridy = 2;
        secondPanel.add(quantity, c);
        c.gridx = 3;
        c.gridy = 2;
        secondPanel.add(total, c);
        c.gridx = 0;
        c.gridy = 3;
        secondPanel.add(txtCName, c);
        c.gridx = 1;
        c.gridy = 3;
        secondPanel.add(txtSearch, c);
        c.gridx = 2;
        c.gridy = 3;
        secondPanel.add(txtQuantity, c);
        c.gridx = 3;
        c.gridy = 3;
        secondPanel.add(txtTotal, c);
        c.gridx = 0;
        c.gridy = 4;
        secondPanel.add(mobile, c);
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 5;
        secondPanel.add(s, c);
        c.gridx = 3;
        c.gridy = 4;
        c.gridheight = 1;
        secondPanel.add(addToCart, c);
        c.gridx = 0;
        c.gridy = 5;
        secondPanel.add(txtMobile, c);
        c.gridx = 2;
        c.gridy = 5;
        c.gridheight = 4;
        c.gridwidth = 2;
        secondPanel.add(scrollPaneTable, c);
        c.gridx = 0;
        c.gridy = 6;
        c.gridheight = 1;
        c.gridwidth = 1;
        secondPanel.add(email, c);
        c.gridx = 0;
        c.gridy = 7;
        secondPanel.add(txtEmail, c);
        c.gridx = 0;
        c.gridy = 8;
        secondPanel.add(btnBack, c);
        c.gridx = 2;
        c.gridy = 9;
        secondPanel.add(totalText, c);
        c.gridx = 3;
        c.gridy = 9;
        secondPanel.add(generateBill, c);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.setBounds(0,750,1366,50);
        bottomPanel.setBackground(new Color(255, 237, 211));
        
        add(topPanel);
        add(firstPanel);
        add(secondPanel);
        add(bottomPanel);

        setSize(1366,800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == addToCart) {
    		String n = txtName.getText();
    		int quan = Integer.parseInt(txtQuantity.getText());
    		try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        ResultSet rs;
		        String query = "SELECT * FROM `FoodItem` WHERE `itemName`=?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, n);
		            rs = ps.executeQuery();
		            if(rs.next())
		            {
		            	int id = Integer.parseInt(rs.getString(1));
		            	PreparedStatement ps1;
		            	String q = "SELECT * FROM `ItemList` WHERE `orderID`=? AND `itemID`=?";
		            	ps1 = con.prepareStatement(q);
			            ps1.setInt(1, orderId);
			            ps1.setInt(2, id);
			            ResultSet rs1;
			            rs1 = ps1.executeQuery();
			            if(rs1.next())
			            {
			            	quan += Integer.parseInt(rs1.getString(3));
			            	q = "UPDATE `ItemList` SET `quantity`=? WHERE `orderID`=? AND `itemID`=?";
			            	PreparedStatement ps2;
				            ps2 = con.prepareStatement(q);
				            ps2.setInt(1, quan);
				            ps2.setInt(2, orderId);
				            ps2.setInt(3, id);
				            if(ps2.executeUpdate()>0)
				            {
				            	txtName.setText("");
				            	Integer x = Integer.parseInt(txtQuantity.getText());
				            	txtQuantity.setText("");
				            	txtPrice.setText("");
				            	double d = Double.parseDouble(txtTotal.getText());
				            	txtTotal.setText("");
				            	x=quan - x;
				            	String elem = String.valueOf(quan)+"x    "+n;
				            	cartModel.addElement(elem);
				            	elem = String.valueOf(x)+"x    "+n;
				            	cartModel.removeElement(elem);
				            	totBill+=d;				            	
				            }
			            }
			            else {
				        q = "INSERT INTO `ItemList` (`orderID`, `itemID`, `quantity`) VALUES (?,?,?)";
				            ps1 = con.prepareStatement(q);
				            ps1.setInt(1, orderId);
				            ps1.setInt(2, id);
				            ps1.setInt(3, quan);
				            if(ps1.executeUpdate()>0)
				            {
				            	txtName.setText("");
				            	txtQuantity.setText("");
				            	txtPrice.setText("");
				            	double d = Double.parseDouble(txtTotal.getText());
				            	txtTotal.setText("");
				            	String elem = String.valueOf(quan)+"x    "+n;
				            	cartModel.addElement(elem);
				            	totBill+=d;
				            }
			            }
		            }
		            con.close();
		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }
    		totalText.setText("Total "+String.valueOf(totBill));
    		validateFields();
    	}
		if(e.getSource() == btnBack) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this order?");
            switch(result) {
            case JOptionPane.YES_OPTION:
            	try{
    				Connection con = dbconnection.conn();
    	        	PreparedStatement ps;
    		        String query = "DELETE FROM `ItemList` WHERE `orderID`=?";
    		            ps = con.prepareStatement(query);
    		            ps.setInt(1, orderId);
    		            ps.executeUpdate();
    		            con.close();
    		        } catch (SQLException ex) {
    		        	System.out.println(ex);
    		        }
    			try{
    				Connection con = dbconnection.conn();
    	        	PreparedStatement ps;
    		        String query = "DELETE * FROM `Order` WHERE `orderID`=?";
    		            ps = con.prepareStatement(query);
    		            ps.setInt(1, orderId);
    		            ps.executeUpdate();
    		            con.close();
    		        } catch (SQLException ex) {
    		        	System.out.println(ex);
    		        }
    			dispose();
        		new guest();
                break;
                case JOptionPane.NO_OPTION:
                break;
                case JOptionPane.CANCEL_OPTION:
                break;
                case JOptionPane.CLOSED_OPTION:
                break;
            }
		}
		if(e.getSource() == generateBill) {
			String nam = txtCName.getText();
			String em = txtEmail.getText();
			String mm = txtMobile.getText();
			SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String todayDate = dFormat.format(date);
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        ResultSet rs;
		        String query = "UPDATE `Order` SET `name`=?,`email`=?,mobile=?,`date`=? WHERE `orderID`=?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, nam);
		            ps.setString(2, em);
		            ps.setString(3, mm);
		            ps.setString(4, todayDate);
		            ps.setInt(5, orderId);
		            ps.executeUpdate();
		            con.close();
			}catch (SQLException ex) {
	        	System.out.println(ex);
	        }
			String path = "D:\\Cafeteria Management System\\";
			String oType = String.valueOf(type.getSelectedItem());
			com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
			try {
				PdfWriter.getInstance(doc, new FileOutputStream(path+""+orderId+".pdf"));
				doc.open();
				Paragraph nm = new Paragraph("                                                       Cafeteria Management System\n");
				doc.add(nm);
				Paragraph starLine = new Paragraph("****************************************************************************************************************");
				doc.add(starLine);
				Paragraph line3 = new Paragraph("\tOrder ID: "+orderId+"\nCustomer Name: "+txtCName.getText()+"        Order Type: "+oType+"        Total Bill: "+totBill+"\n ");
				doc.add(line3);
				PdfPTable tbl = new PdfPTable(4);
				tbl.addCell("Name");
				tbl.addCell("Price");
				tbl.addCell("Quantity");
				tbl.addCell("Total");
				try{
		        	Connection con = dbconnection.conn();
		            	String q = "SELECT * FROM `ItemList` WHERE `orderID` =?";
		            	PreparedStatement ps1;
		    	        ResultSet rs1;
		    	        ps1 = con.prepareStatement(q);
		    	        ps1.setInt(1, orderId);
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
				            	String ne = rs2.getString(2);
				            	double price = Double.parseDouble(rs2.getString(3));
				            	double tot = price*quantity;
				            	tbl.addCell(ne);
								tbl.addCell(String.valueOf(price));
								tbl.addCell(String.valueOf(quantity));
								tbl.addCell(String.valueOf(tot));
				            }
			            }
			        con.close();
			        } catch (SQLException ex) {
			        	System.out.println(ex);
			        }
				doc.add(tbl);
				doc.add(starLine);
				Paragraph thanks = new Paragraph("                                                       Thank you, Please visit again!");
				doc.add(thanks);
				doc.close();
				try {
					if((new File("D://Cafeteria Management System//"+orderId+".pdf")).exists()) {
						Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D://Cafeteria Management System//"+orderId+".pdf");
					}
				}catch(Exception ee) {
					System.out.println(ee);
				}
			}
			catch(Exception e1) {
				System.out.println(e1);
			}
			dispose();
			new guest();
		}
	}
    public void validateFields() {
    	String quantityPattern = "^([1-9])([0-9])*$";
    	String emailPattern = "^[^@\\s]+@([^\\.@\\s]+)(\\.[^\\.@\\s]+)+$";
    	String namePattern = "^([a-zA-Z\\s]){2,30}$";
    	String mobilePattern = "^(\\+923|00923|03)[0-4][0-9]\\-?\\d{7}$";
    	if(txtCName.getText().matches(namePattern)&&txtEmail.getText().matches(emailPattern)&&txtMobile.getText().matches(mobilePattern)&&totBill>0) {
    		generateBill.setEnabled(true);
    	}
    	else {
    		generateBill.setEnabled(false);
    	}
    	if(txtName.getText().matches(namePattern)&&txtQuantity.getText().matches(quantityPattern)&&!txtQuantity.getText().equals("")&&!txtPrice.getText().equals("")&&!txtTotal.getText().equals("")) {
    		addToCart.setEnabled(true);
    	}
    	else {
    		addToCart.setEnabled(false);
    	}
    }
//    public static void main(String [] args)
//    {
//        placeOrder obj = new placeOrder();
//    }
}
