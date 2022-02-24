package cms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class guest extends JFrame {
	//private JFrame frame;
    private JPanel topPanel, firstPanel, secondPanel, lastPanel;
    private JLabel projectName;
    private Font f1, f2, f3;
    private JButton placeOrder, login;
    
    public guest(){
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
        f2 = new Font("Bookman Old Style", Font.BOLD, 24);
    	
        secondPanel = new JPanel();
    	secondPanel.setLayout(new GridBagLayout());
    	secondPanel.setBounds(0,350,1366,150);
    	secondPanel.setBackground(new Color(255, 237, 211));
    	f3 = new Font("Bookman Old Style", Font.PLAIN, 18);
    	placeOrder = new JButton("Place Order");
        placeOrder.setBackground(new Color(18, 60, 105));
        placeOrder.setForeground(new Color(255, 237, 211));
        placeOrder.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		placeOrder obj = new placeOrder();
        	}
        });
        login = new JButton("Login");
        login.setBackground(new Color(18, 60, 105));
        login.setForeground(new Color(255, 237, 211));
        login.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		login obj = new login();
        	}
        });
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        secondPanel.add(placeOrder, c);
        c.gridx=0;
        c.gridy=1;
        secondPanel.add(login, c);
        
        lastPanel = new JPanel();
        lastPanel.setLayout(new GridLayout(1,1));
        lastPanel.setBounds(0,500,1366,300);
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

    public static void main(String [] args)
    {
        guest obj = new guest();
    }
}
