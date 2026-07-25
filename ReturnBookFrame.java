package gui;

import dao.ReturnBookDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBookFrame extends JFrame implements ActionListener {

    JLabel lblIssueId, lblBookId;
    JTextField txtIssueId, txtBookId;
    JButton btnReturn, btnClear;

    ReturnBookDAO dao = new ReturnBookDAO();

    public ReturnBookFrame() {

        setTitle("Return Book");
        setSize(450,300);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblIssueId = new JLabel("Issue ID");
        lblIssueId.setBounds(40,40,100,30);
        add(lblIssueId);

        txtIssueId = new JTextField();
        txtIssueId.setBounds(150,40,200,30);
        add(txtIssueId);

        lblBookId = new JLabel("Book ID");
        lblBookId.setBounds(40,90,100,30);
        add(lblBookId);

        txtBookId = new JTextField();
        txtBookId.setBounds(150,90,200,30);
        add(txtBookId);

        btnReturn = new JButton("Return");
        btnReturn.setBounds(70,170,120,35);
        btnReturn.addActionListener(this);
        add(btnReturn);

        btnClear = new JButton("Clear");
        btnClear.setBounds(220,170,120,35);
        btnClear.addActionListener(this);
        add(btnClear);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnReturn){

            int issueId = Integer.parseInt(txtIssueId.getText());
            int bookId = Integer.parseInt(txtBookId.getText());

            if(dao.returnBook(issueId,bookId)){

                JOptionPane.showMessageDialog(this,"Book Returned Successfully");

            }else{

                JOptionPane.showMessageDialog(this,"Return Failed");

            }

        }

        if(e.getSource()==btnClear){

            txtIssueId.setText("");
            txtBookId.setText("");

        }

    }

}