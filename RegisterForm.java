package com.jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterForm extends JFrame {

    private JTextField fullnameField, usernameField;
    private JPasswordField passwordField;
    private JButton registerButton, backToLoginButton;

    public RegisterForm() {
        setTitle("User Registration");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLabel lblTitle = new JLabel("Register New User", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblFullname = new JLabel("Full Name:");
        fullnameField = new JTextField(20);

        JLabel lblUsername = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel lblPassword = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        registerButton = new JButton("Register");
        backToLoginButton = new JButton("Back to Login");

  
        setLayout(new GridLayout(6, 2, 10, 10));
        add(lblTitle);
        add(new JLabel(""));
        add(lblFullname);
        add(fullnameField);
        add(lblUsername);
        add(usernameField);
        add(lblPassword);
        add(passwordField);
        add(registerButton);
        add(backToLoginButton);

       
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

       
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm().setVisible(true);
            }
        });
    }

   
    private void registerUser() {
        String fullname = fullnameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Please fill all fields");
            return;
        }

        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users(fullname, username, password) VALUES(?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fullname);
            pst.setString(2, username);
            pst.setString(3, password);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "✅ Registration Successful! Now Login");
                dispose();
                new LoginForm().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Registration Failed!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RegisterForm().setVisible(true);
    }
}
