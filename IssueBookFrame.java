
package gui;

import dao.IssueBookDAO;
import model.IssueBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class IssueBookFrame extends JFrame {

    private JTextField txtStudentId, txtBookId, txtIssueDate, txtReturnDate, txtStatus;
    private JTable table;
    private DefaultTableModel model;
    private IssueBookDAO dao;

    public IssueBookFrame() {
        dao = new IssueBookDAO();

        setTitle("Issue Book");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6,2,10,10));

        form.add(new JLabel("Student ID"));
        txtStudentId = new JTextField();
        form.add(txtStudentId);

        form.add(new JLabel("Book ID"));
        txtBookId = new JTextField();
        form.add(txtBookId);

        form.add(new JLabel("Issue Date (YYYY-MM-DD)"));
        txtIssueDate = new JTextField();
        form.add(txtIssueDate);

        form.add(new JLabel("Return Date (YYYY-MM-DD)"));
        txtReturnDate = new JTextField();
        form.add(txtReturnDate);

        form.add(new JLabel("Status"));
        txtStatus = new JTextField();
        form.add(txtStatus);

        JPanel btnPanel = new JPanel();

        JButton btnSave = new JButton("Save");
        JButton btnClear = new JButton("Clear");

        btnPanel.add(btnSave);
        btnPanel.add(btnClear);

        JPanel north = new JPanel(new BorderLayout());
        north.add(form, BorderLayout.CENTER);
        north.add(btnPanel, BorderLayout.SOUTH);

        add(north, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Student ID","Book ID","Issue Date","Return Date","Status"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnSave.addActionListener((ActionEvent e) -> saveIssue());
        btnClear.addActionListener((ActionEvent e) -> clearFields());

        loadTable();

        setVisible(true);
    }

    private void saveIssue() {
        try {
            IssueBook issue = new IssueBook();
            issue.setStudentId(Integer.parseInt(txtStudentId.getText().trim()));
            issue.setBookId(Integer.parseInt(txtBookId.getText().trim()));
            issue.setIssueDate(txtIssueDate.getText().trim());
            issue.setReturnDate(txtReturnDate.getText().trim());
            issue.setStatus(txtStatus.getText().trim());

            dao.issueBook(issue);

            JOptionPane.showMessageDialog(this,"Book Issued Successfully");
            clearFields();
            loadTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void clearFields() {
        txtStudentId.setText("");
        txtBookId.setText("");
        txtIssueDate.setText("");
        txtReturnDate.setText("");
        txtStatus.setText("");
    }

    private void loadTable() {
        model.setRowCount(0);

        List<IssueBook> list = dao.getAllIssuedBooks();

        for (IssueBook b : list) {
            model.addRow(new Object[]{
                    b.getStudentId(),
                    b.getBookId(),
                    b.getIssueDate(),
                    b.getReturnDate(),
                    b.getStatus()
            });
        }
    }
}
