package com.jdbc;

import javax.swing.*;

import java.awt.*;

public class HomePage extends JFrame {

    private JButton viewProfileButton, settingsButton, logoutButton;

    public HomePage(String fullname) {
    
        setTitle("Homepage");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

      
        JLabel titleLabel = new JLabel("Welcome to the Homepage", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

       
        String firstName = fullname.split(" ")[0];
        JLabel greetingLabel = new JLabel("Hello, " + firstName + "!", SwingConstants.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(greetingLabel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        viewProfileButton = new JButton("View Profile");
        settingsButton = new JButton("Settings");
        logoutButton = new JButton("Logout");

        buttonPanel.add(viewProfileButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);

       
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }
}
