package gui;

import dao.ReportDAO;
import model.Report;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ReportFrame extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton btnRefresh;

    ReportDAO dao = new ReportDAO();

    public ReportFrame() {

        setTitle("Library Reports");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("Issue ID");
        model.addColumn("Student ID");
        model.addColumn("Student Name");
        model.addColumn("Book ID");
        model.addColumn("Book Title");
        model.addColumn("Issue Date");
        model.addColumn("Return Date");
        model.addColumn("Status");

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        btnRefresh = new JButton("Refresh");

        btnRefresh.addActionListener(e -> loadReport());

        add(sp, BorderLayout.CENTER);
        add(btnRefresh, BorderLayout.SOUTH);

        loadReport();

        setVisible(true);

    }

    private void loadReport() {

        model.setRowCount(0);

        ArrayList<Report> list = dao.getAllReports();

        for (Report r : list) {

            model.addRow(new Object[]{

                    r.getIssueId(),
                    r.getStudentId(),
                    r.getStudentName(),
                    r.getBookId(),
                    r.getBookTitle(),
                    r.getIssueDate(),
                    r.getReturnDate(),
                    r.getStatus()

            });

        }

    }

}