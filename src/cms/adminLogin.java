package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class adminLogin extends JFrame {
	//private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, lastPanel;
    private JLabel projectName;
    private Font f1, f2, f3;
    private JButton addFood, viewFood, addStaff, viewStaff, viewSales, assStaff, logout, changePass;
    
    public adminLogin(){
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
    	firstPanel.setBounds(0,200,1366,100);
    	firstPanel.setBackground(new Color(255, 237, 211));
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
    	
        secondPanel = new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	secondPanel.setBounds(0,300,1366,300);
    	secondPanel.setBackground(new Color(255, 237, 211));
    	f3 = new Font("Bookman Old Style", Font.PLAIN, 18);
    	addFood = new JButton("Add Food Item");
        addFood.setBackground(new Color(18, 60, 105));
        addFood.setForeground(new Color(255, 237, 211));
        addFood.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		addFoodItem obj = new addFoodItem();
        	}
        });
        viewFood = new JButton("View, Edit or Delete Food Item");
        viewFood.setBackground(new Color(18, 60, 105));
        viewFood.setForeground(new Color(255, 237, 211));
        viewFood.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		viewFood obj = new viewFood();
        	}
        });
        addStaff = new JButton("Add Staff Member");
        addStaff.setBackground(new Color(18, 60, 105));
        addStaff.setForeground(new Color(255, 237, 211));
        addStaff.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		addStaff obj = new addStaff();
        	}
        });
        viewStaff = new JButton("View, Edit or Delete Staff Members");
        viewStaff.setBackground(new Color(18, 60, 105));
        viewStaff.setForeground(new Color(255, 237, 211));
        viewStaff.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		viewStaff obj = new viewStaff();
        	}
        });
        viewSales = new JButton("View Sales");
        viewSales.setBackground(new Color(18, 60, 105));
        viewSales.setForeground(new Color(255, 237, 211));
        viewSales.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		viewSales obj = new viewSales();
        	}
        });
        assStaff = new JButton("Assign Staff Shifts");
        assStaff.setBackground(new Color(18, 60, 105));
        assStaff.setForeground(new Color(255, 237, 211));
        assStaff.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		assignShift obj = new assignShift();
        	}
        });
        logout = new JButton("Logout");
        logout.setBackground(new Color(18, 60, 105));
        logout.setForeground(new Color(255, 237, 211));
        logout.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new guest();
        	}
        });
        changePass = new JButton("Change Password");
        changePass.setBackground(new Color(18, 60, 105));
        changePass.setForeground(new Color(255, 237, 211));
        changePass.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		changePassword obj = new changePassword();
        	}
        });
    	GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.weightx = 0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,10);
        secondPanel.add(addFood, c);
        c.gridx=1;
        c.gridy=0;
        c.insets = new Insets(0,0,0,0);
        secondPanel.add(viewFood, c);
        c.gridx=0;
        c.gridy=1;
        c.insets = new Insets(0,0,0,10);
        secondPanel.add(addStaff, c);
        c.gridx=1;
        c.gridy=1;
        c.insets = new Insets(0,0,0,0);
        secondPanel.add(viewStaff, c);
        c.gridx=0;
        c.gridy=2;
        c.insets = new Insets(0,0,0,10);
        secondPanel.add(viewSales, c);
        c.gridx=1;
        c.gridy=2;
        c.insets = new Insets(0,0,0,0);
        secondPanel.add(assStaff, c);
        c.gridx=0;
        c.gridy=3;
        c.insets = new Insets(0,0,0,10);
        secondPanel.add(logout, c);
        c.gridx=1;
        c.gridy=3;
        c.insets = new Insets(0,0,0,0);
        secondPanel.add(changePass, c);
        
        lastPanel = new JPanel();
        lastPanel.setLayout(new GridLayout(1,1));
        lastPanel.setBounds(0,600,1366,200);
        lastPanel.setBackground(new Color(255, 237, 211));
        
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
//        adminLogin obj = new adminLogin();
//    }
}
