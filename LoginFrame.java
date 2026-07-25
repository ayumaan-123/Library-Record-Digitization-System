package gui;

import dao.AdminDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel title;
    JLabel userLabel;
    JLabel passLabel;

    JTextField txtUser;
    JPasswordField txtPass;

    JButton btnLogin;

    public LoginFrame() {

        setTitle("Library Management System");

        setSize(500,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        title = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(45,30,420,40);
        add(title);

        userLabel = new JLabel("Username");
        userLabel.setBounds(70,110,100,30);
        add(userLabel);

        txtUser = new JTextField();
        txtUser.setBounds(170,110,180,30);
        add(txtUser);

        passLabel = new JLabel("Password");
        passLabel.setBounds(70,170,100,30);
        add(passLabel);

        txtPass = new JPasswordField();
        txtPass.setBounds(170,170,180,30);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(170,240,120,35);

        btnLogin.addActionListener(this);

        add(btnLogin);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = txtUser.getText();

        String password = String.valueOf(txtPass.getPassword());

        AdminDAO dao = new AdminDAO();

        boolean status = dao.login(username, password);

        if(status){

            JOptionPane.showMessageDialog(this,"Login Successful");

            new DashboardFrame();

            dispose();

        }

        else{

            JOptionPane.showMessageDialog(this,"Invalid Username or Password");

        }

    }

}