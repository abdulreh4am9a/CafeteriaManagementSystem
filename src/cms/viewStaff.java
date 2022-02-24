package cms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class viewStaff extends JFrame implements ActionListener{
    //private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, bottomPanel;
    private JLabel projectName, pageName, id, name, gender, desig;
    private JTextField txtid, txtName, txtDesig;
    private JButton btnUpdate, btnDelete, btnBack;
    private JComboBox txtGender;
    private JTable staff;
    private Font f1, f2, f3;
    
    public viewStaff(){
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
        pageName = new JLabel("View, Edit & Delete Staff", SwingConstants.CENTER);
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
        txtDesig = new JTextField(10);
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
        secondPanel.add(txtid, c);
        c.gridx = 2;
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
        secondPanel.add(txtName,c);
        c.gridx=0;
        c.gridy=2;
        c.weightx=0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets =new Insets(0,30,0,10);
        secondPanel.add(gender,c);
        c.gridx=1;
        c.gridy=2;
        secondPanel.add(txtGender,c);
        c.gridx=0;
        c.gridy=3;
        secondPanel.add(desig,c);
        c.gridx=1;
        c.gridy=3;
        secondPanel.add(txtDesig,c);
        c.gridx=0;
        c.gridy=4;
        c.fill = GridBagConstraints.NONE;
        secondPanel.add(btnUpdate,c);
        c.gridx=1;
        c.gridy=4;
        secondPanel.add(btnDelete,c);
        c.gridx=0;
        c.gridy=5;
        c.gridwidth=2;
        secondPanel.add(btnBack,c);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.setBounds(0,730,1366,150);
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
			String gender = String.valueOf(txtGender.getSelectedItem());
			String designation = txtDesig.getText();
			try{
				Connection con = dbconnection.conn();
	        	PreparedStatement ps;
		        String query = "UPDATE `Staff` SET `staffName`=?, `gender`=?, `designation`=? WHERE `staffID`=?";
		            ps = con.prepareStatement(query);
		            ps.setString(1, name);
		            ps.setString(2, gender);
		            ps.setString(3, designation);
		            ps.setInt(4, id);
		            if(ps.executeUpdate()>0)
		            {
		            	JOptionPane.showMessageDialog( null, "Staff Member Updated Successfully!" );
		            	dispose();
						new viewStaff();
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
		        String query = "DELETE FROM `Staff` WHERE `staffID`=?";
		            ps = con.prepareStatement(query);
		            ps.setInt(1, id);
		            int result = JOptionPane.showConfirmDialog(null, "Do you want to delete this staff member?");
		            switch(result) {
		            case JOptionPane.YES_OPTION:
		            	if(ps.executeUpdate()>0)
			            {
		            		JOptionPane.showMessageDialog( null, "Staff Member Deleted Successfully!" );
			            	dispose();
							new viewStaff();
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
    	if(txtid.getText().matches(idPattern) && txtName.getText().matches(namePattern) && txtDesig.getText().matches(namePattern)) {
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
//        viewStaff obj = new viewStaff();
//    }
}
