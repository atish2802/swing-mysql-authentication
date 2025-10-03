package com.jdbc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LoginForm extends JFrame {

	    private JTextField usernameField;
	    private JPasswordField passwordField;
	    private JButton loginButton;

	    public LoginForm() {
	      
	        setTitle("Login Page");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(null);

	      
	        JLabel titleLabel = new JLabel("Login Page", SwingConstants.CENTER);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        titleLabel.setBounds(100, 20, 200, 40);
	        add(titleLabel);

	    
	        JLabel userLabel = new JLabel("Username:");
	        userLabel.setBounds(50, 80, 100, 25);
	        add(userLabel);

	        usernameField = new JTextField();
	        usernameField.setBounds(150, 80, 200, 25);
	        add(usernameField);

	      
	        JLabel passLabel = new JLabel("Password:");
	        passLabel.setBounds(50, 120, 100, 25);
	        add(passLabel);

	        passwordField = new JPasswordField();
	        passwordField.setBounds(150, 120, 200, 25);
	        add(passwordField);

	       
	        loginButton = new JButton("Login");
	        loginButton.setBounds(150, 180, 100, 30);
	        add(loginButton);

	   
	        loginButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                loginUser();
	            }
	        });
	    }

	    private void loginUser() {
	        String username = usernameField.getText();
	        String password = new String(passwordField.getPassword());

	        try (Connection conn = DatabaseConnection.getConnection()) {
	            String sql = "SELECT fullname FROM users WHERE username=? AND password=?";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            pst.setString(1, username);
	            pst.setString(2, password);

	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                String fullname = rs.getString("fullname");
	                JOptionPane.showMessageDialog(this, "Login Successful!");
	                dispose(); 
	                new HomePage(fullname).setVisible(true);
	            } else {
	                JOptionPane.showMessageDialog(this, "❌ No user found / Wrong Username or Password");
	            }

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
	    }
	
	    public void dispose() {
	    	 new RegisterForm().setVisible(true);
		}
	   

}
