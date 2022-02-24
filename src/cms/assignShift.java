package cms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class assignShift extends JFrame implements ActionListener{
    //private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel;
    private JLabel projectName, pageName, id, startShift, endShift;
    private JTextField txtid, txtStart, txtEnd;
    private JButton btnAssign, btnBack;
    private JTable staff;
    private Font f1, f2, f3;
    
    public assignShift(){
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
    	firstPanel.setBounds(0,150,1366,100);
    	firstPanel.setBackground(new Color(255, 237, 211));
        pageName = new JLabel("Assign Shift", SwingConstants.CENTER);
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
        startShift = new JLabel("Start:   ");
    	startShift.setBackground(new Color(255, 237, 211));
    	startShift.setForeground(new Color(18, 60, 105));
    	startShift.setFont(f3);
        txtStart = new JTextField(10);
        txtStart.setBackground(new Color(237, 199, 183));
        txtStart.setForeground(new Color(18, 60, 105));
        txtStart.setFont(f3);
        txtStart.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        endShift = new JLabel("End:   ");
        endShift.setBackground(new Color(255, 237, 211));
        endShift.setForeground(new Color(18, 60, 105));
        endShift.setFont(f3);
        txtEnd = new JTextField(10);
        txtEnd.setBackground(new Color(237, 199, 183));
        txtEnd.setForeground(new Color(18, 60, 105));
        txtEnd.setFont(f3);
        txtEnd.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
        });
        
        DefaultTableModel daDefaultTableModel = new DefaultTableModel(0, 0);
        String cols[] = {"ID","Name","Gender","Designation", "Shift Start", "Shift End"};
        daDefaultTableModel.setColumnIdentifiers(cols);
        try{
        	Connection con = dbconnection.conn();
        	PreparedStatement ps;
	        ResultSet rs;
	        String query = "SELECT * FROM `Staff`";
            ps = con.prepareStatement(query); 
            rs = ps.executeQuery();
            while(rs.next())
	        {
	        	Object obj[] = new Object[] {
	        		rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)	
	        	};
	        	daDefaultTableModel.addRow(obj);
	        }
	        con.close();
	        } catch (SQLException ex) {
	        	System.out.println(ex);
	        }
        staff = new JTable(daDefaultTableModel) {
        	public boolean isCellEditable(int rows, int cols) {
        		return false;
        	}
        };
        staff.setBackground(new Color(237, 199, 183));
		staff.setForeground(new Color(18, 60, 105));
		staff.setFont(f3);
		staff.getColumnModel().getColumn(0).setPreferredWidth(100);
		staff.getColumnModel().getColumn(1).setPreferredWidth(300);
		staff.getColumnModel().getColumn(2).setPreferredWidth(250);
		staff.getColumnModel().getColumn(3).setPreferredWidth(250);
		staff.getColumnModel().getColumn(4).setPreferredWidth(250);
		staff.getColumnModel().getColumn(5).setPreferredWidth(250);
		JScrollPane scrollPane = new JScrollPane(staff);
		scrollPane.setVisible(true);
        btnAssign = new JButton("Assign");
        btnAssign.setBackground(new Color(18, 60, 105));
        btnAssign.setForeground(new Color(255, 237, 211));
        btnAssign.setEnabled(false);
        btnAssign.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(18, 60, 105));
        btnBack.setForeground(new Color(255, 237, 211));
        btnBack.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets =new Insets(0,30,0,10);
        secondPanel.add(id, c);
        c.gridx = 1;
        c.gridy = 0;
        secondPanel.add(txtid, c);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 20;
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
        secondPanel.add(startShift,c);
        c.gridx=1;
        c.gridy=1;
        secondPanel.add(txtStart,c);
        c.gridx=0;
        c.gridy=2;
        secondPanel.add(endShift,c);
        c.gridx=1;
        c.gridy=2;
        secondPanel.add(txtEnd,c);
        c.gridx=0;
        c.gridy=3;
        c.fill = GridBagConstraints.NONE;
        secondPanel.add(btnAssign,c);
        c.gridx=1;
        c.gridy=3;
        secondPanel.add(btnBack,c);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.setBounds(0, 750,1366,50);
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
		if (e.getSource() == btnAssign){
			int id = Integer.parseInt(txtid.getText());
			String start = txtStart.getText();
			String end = txtEnd.getText();
			try{
	        	Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        String query = "UPDATE `Staff` SET `shiftStart`=?, `shiftEnd`=? WHERE `staffID`=?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, start);
		            ps.setString(2, end);
		            ps.setInt(3, id);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Shift Assigned Successfully!" );
		            	dispose();
						new assignShift();
		            }
		            else {
		            	JOptionPane.showMessageDialog(secondPanel,"Invalid ID!","Warning", JOptionPane.WARNING_MESSAGE);
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
    	String timePattern = "^[0-1][0-9]:?[0-5][0-9]$";
    	if(txtid.getText().matches(idPattern) && txtStart.getText().matches(timePattern) && txtEnd.getText().matches(timePattern)) {
    		btnAssign.setEnabled(true);
    	}
    	else {
    		btnAssign.setEnabled(false);
    	}
    }
//    public static void main(String [] args)
//    {
//        assignShift obj = new assignShift();
//    }
}
