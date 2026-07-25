package gui;

import dao.BookDAO;
import model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BookFrame extends JFrame implements ActionListener {

    JLabel lblTitle,lblBook,lblAuthor,lblCategory,lblQuantity,lblAvailable;

    JTextField txtBook,txtAuthor,txtCategory,txtQuantity,txtAvailable;

    JButton btnSave,btnUpdate,btnDelete,btnClear;

    JTable table;
    DefaultTableModel model;
    JScrollPane sp;

    BookDAO dao=new BookDAO();

    int bookId=-1;

    public BookFrame(){

        setTitle("Book Management");
        setSize(1000,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblTitle=new JLabel("BOOK MANAGEMENT",SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial",Font.BOLD,30));
        lblTitle.setBounds(0,20,1000,40);
        add(lblTitle);

        lblBook=new JLabel("Book Title");
        lblBook.setBounds(40,90,120,30);
        add(lblBook);

        txtBook=new JTextField();
        txtBook.setBounds(160,90,220,30);
        add(txtBook);

        lblAuthor=new JLabel("Author");
        lblAuthor.setBounds(40,140,120,30);
        add(lblAuthor);

        txtAuthor=new JTextField();
        txtAuthor.setBounds(160,140,220,30);
        add(txtAuthor);

        lblCategory=new JLabel("Category");
        lblCategory.setBounds(40,190,120,30);
        add(lblCategory);

        txtCategory=new JTextField();
        txtCategory.setBounds(160,190,220,30);
        add(txtCategory);

        lblQuantity=new JLabel("Quantity");
        lblQuantity.setBounds(40,240,120,30);
        add(lblQuantity);

        txtQuantity=new JTextField();
        txtQuantity.setBounds(160,240,220,30);
        add(txtQuantity);

        lblAvailable=new JLabel("Available");
        lblAvailable.setBounds(40,290,120,30);
        add(lblAvailable);

        txtAvailable=new JTextField();
        txtAvailable.setBounds(160,290,220,30);
        add(txtAvailable);

        btnSave=new JButton("SAVE");
        btnSave.setBounds(20,430,85,35);
        btnSave.addActionListener(this);
        add(btnSave);

        btnUpdate=new JButton("UPDATE");
        btnUpdate.setBounds(115,430,85,35);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnDelete=new JButton("DELETE");
        btnDelete.setBounds(210,430,85,35);
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnClear=new JButton("CLEAR");
        btnClear.setBounds(305,430,85,35);
        btnClear.addActionListener(this);
        add(btnClear);

        model=new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Category");
        model.addColumn("Quantity");
        model.addColumn("Available");

        table=new JTable(model);
        table.setRowHeight(25);

        sp=new JScrollPane(table);
        sp.setBounds(450,90,510,500);
        add(sp);

        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                int row=table.getSelectedRow();

                bookId=Integer.parseInt(model.getValueAt(row,0).toString());

                txtBook.setText(model.getValueAt(row,1).toString());
                txtAuthor.setText(model.getValueAt(row,2).toString());
                txtCategory.setText(model.getValueAt(row,3).toString());
                txtQuantity.setText(model.getValueAt(row,4).toString());
                txtAvailable.setText(model.getValueAt(row,5).toString());

            }

        });

        loadBooks();

        setVisible(true);

    }

    public void loadBooks(){

        model.setRowCount(0);

        ArrayList<Book> list=dao.getAllBooks();

        for(Book b:list){

            Object row[]={
                    b.getBookId(),
                    b.getTitle(),
                    b.getAuthor(),
                    b.getCategory(),
                    b.getQuantity(),
                    b.getAvailable()
            };

            model.addRow(row);

        }

    }

    public void clearFields(){

        bookId=-1;

        txtBook.setText("");
        txtAuthor.setText("");
        txtCategory.setText("");
        txtQuantity.setText("");
        txtAvailable.setText("");

        txtBook.requestFocus();

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnSave){

            Book b = new Book();

            b.setTitle(txtBook.getText());
            b.setAuthor(txtAuthor.getText());
            b.setCategory(txtCategory.getText());
            b.setQuantity(Integer.parseInt(txtQuantity.getText()));
            b.setAvailable(Integer.parseInt(txtAvailable.getText()));

            if(dao.addBook(b)){

                JOptionPane.showMessageDialog(this,"Book Added Successfully");

                loadBooks();

                clearFields();

            }else{

                JOptionPane.showMessageDialog(this,"Failed");

            }

        }

        else if(e.getSource()==btnUpdate){

            if(bookId==-1){

                JOptionPane.showMessageDialog(this,"Select Book");

                return;

            }

            Book b = new Book();

            b.setBookId(bookId);
            b.setTitle(txtBook.getText());
            b.setAuthor(txtAuthor.getText());
            b.setCategory(txtCategory.getText());
            b.setQuantity(Integer.parseInt(txtQuantity.getText()));
            b.setAvailable(Integer.parseInt(txtAvailable.getText()));

            if(dao.updateBook(b)){

                JOptionPane.showMessageDialog(this,"Book Updated Successfully");

                loadBooks();

                clearFields();

            }else{

                JOptionPane.showMessageDialog(this,"Update Failed");

            }

        }

        else if(e.getSource()==btnDelete){

            if(bookId==-1){

                JOptionPane.showMessageDialog(this,"Select Book");

                return;

            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete this Book?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if(option==JOptionPane.YES_OPTION){

                if(dao.deleteBook(bookId)){

                    JOptionPane.showMessageDialog(this,"Book Deleted Successfully");

                    loadBooks();

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