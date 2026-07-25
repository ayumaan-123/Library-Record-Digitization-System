package gui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentFrame extends JFrame implements ActionListener {

    JLabel lblTitle;
    JLabel lblRoll;
    JLabel lblName;
    JLabel lblDepartment;
    JLabel lblYear;
    JLabel lblPhone;
    JLabel lblEmail;

    JTextField txtRoll;
    JTextField txtName;
    JTextField txtDepartment;
    JTextField txtYear;
    JTextField txtPhone;
    JTextField txtEmail;

    JButton btnSave;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnClear;

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;

    StudentDAO dao = new StudentDAO();

    int studentId = -1;

    public StudentFrame(){

        setTitle("Student Management");

        setSize(1000,650);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel("STUDENT MANAGEMENT", SwingConstants.CENTER);

        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));

        lblTitle.setBounds(0,20,1000,40);

        add(lblTitle);

        // Name
        lblName = new JLabel("Name");

        lblName.setBounds(40,90,120,30);

        add(lblName);

        txtName = new JTextField();

        txtName.setBounds(160,90,220,30);

        add(txtName);

// Roll No
        lblRoll = new JLabel("Roll No");

        lblRoll.setBounds(40,140,120,30);

        add(lblRoll);

        txtRoll = new JTextField();

        txtRoll.setBounds(160,140,220,30);

        add(txtRoll);

        lblDepartment = new JLabel("Department");

        lblDepartment.setBounds(40,190,120,30);

        add(lblDepartment);

        txtDepartment = new JTextField();

        txtDepartment.setBounds(160,190,220,30);

        add(txtDepartment);

        lblYear = new JLabel("Academic Year");

        lblYear.setBounds(40,240,120,30);

        add(lblYear);

        txtYear = new JTextField();

        txtYear.setBounds(160,240,220,30);

        add(txtYear);

        lblPhone = new JLabel("Phone No");

        lblPhone.setBounds(40,290,120,30);

        add(lblPhone);

        txtPhone = new JTextField();

        txtPhone.setBounds(160,290,220,30);

        add(txtPhone);

        lblEmail = new JLabel("Email ID");

        lblEmail.setBounds(40,340,120,30);

        add(lblEmail);

        txtEmail = new JTextField();

        txtEmail.setBounds(160,340,220,30);

        add(txtEmail);

        btnSave = new JButton("SAVE");

        btnSave.setBounds(20,430,85,35);

        btnSave.addActionListener(this);

        add(btnSave);


        btnUpdate = new JButton("UPDATE");

        btnUpdate.setBounds(115,430,85,35);

        btnUpdate.addActionListener(this);

        add(btnUpdate);


        btnDelete = new JButton("DELETE");

        btnDelete.setBounds(210,430,85,35);

        btnDelete.addActionListener(this);

        add(btnDelete);


        btnClear = new JButton("CLEAR");

        btnClear.setBounds(305,430,85,35);

        btnClear.addActionListener(this);

        add(btnClear);
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Roll No");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Year");
        model.addColumn("Phone");
        model.addColumn("Email");

        table = new JTable(model);

        table.setRowHeight(25);

        scrollPane = new JScrollPane(table);

        scrollPane.setBounds(430,90,530,470);

        add(scrollPane);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                studentId = Integer.parseInt(model.getValueAt(row,0).toString());

                txtRoll.setText(model.getValueAt(row,1).toString());
                txtName.setText(model.getValueAt(row,2).toString());
                txtDepartment.setText(model.getValueAt(row,3).toString());
                txtYear.setText(model.getValueAt(row,4).toString());
                txtPhone.setText(model.getValueAt(row,5).toString());
                txtEmail.setText(model.getValueAt(row,6).toString());

            }

        });

        loadStudents();

        setVisible(true);

    }

    public void loadStudents(){

        model.setRowCount(0);

        ArrayList<Student> list = dao.getAllStudents();

        for(Student s : list){

            Object row[] = {

                    s.getStudentId(),
                    s.getRollNo(),
                    s.getName(),
                    s.getDepartment(),
                    s.getYear(),
                    s.getPhone(),
                    s.getEmail()

            };

            model.addRow(row);

        }

    }

    public void clearFields(){

        studentId = -1;

        txtRoll.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtYear.setText("");
        txtPhone.setText("");
        txtEmail.setText("");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnSave){

            Student s = new Student();

            s.setRollNo(txtRoll.getText());
            s.setName(txtName.getText());
            s.setDepartment(txtDepartment.getText());
            s.setYear(txtYear.getText());
            s.setPhone(txtPhone.getText());
            s.setEmail(txtEmail.getText());

            if(dao.addStudent(s)){

                JOptionPane.showMessageDialog(this,"Student Saved Successfully");

                loadStudents();

                clearFields();

            }else{

                JOptionPane.showMessageDialog(this,"Failed to Save");

            }

        }

        else if(e.getSource()==btnUpdate){

            if(studentId==-1){

                JOptionPane.showMessageDialog(this,"Please Select Student");

                return;

            }

            Student s = new Student();

            s.setStudentId(studentId);
            s.setRollNo(txtRoll.getText());
            s.setName(txtName.getText());
            s.setDepartment(txtDepartment.getText());
            s.setYear(txtYear.getText());
            s.setPhone(txtPhone.getText());
            s.setEmail(txtEmail.getText());

            if(dao.updateStudent(s)){

                JOptionPane.showMessageDialog(this,"Student Updated Successfully");

                loadStudents();

                clearFields();

            }else{

                JOptionPane.showMessageDialog(this,"Update Failed");

            }

        }

        else if(e.getSource()==btnDelete){

            if(studentId==-1){

                JOptionPane.showMessageDialog(this,"Please Select Student");

                return;

            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if(option==JOptionPane.YES_OPTION){

                if(dao.deleteStudent(studentId)){

                    JOptionPane.showMessageDialog(this,"Student Deleted Successfully");

                    loadStudents();

                    clearFields();

                }else{

                    JOptionPane.showMessageDialog(this,"Delete Failed");

                }

            }

        }

        else if(e.getSource()==btnClear){

            clearFields();

        }

    }

}