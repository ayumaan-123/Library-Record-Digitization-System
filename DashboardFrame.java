package gui;

import dao.BookDAO;
import dao.IssueBookDAO;
import dao.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame implements ActionListener {

    JPanel headerPanel;
    JPanel sidePanel;
    JPanel centerPanel;

    JButton btnDashboard;
    JButton btnStudents;
    JButton btnBooks;
    JButton btnIssue;
    JButton btnReturn;
    JButton btnReports;
    JButton btnLogout;

    JLabel title;

    JLabel booksCard;
    JLabel studentsCard;
    JLabel issuedCard;
    JLabel dueCard;

    BookDAO bookDAO = new BookDAO();
    StudentDAO studentDAO = new StudentDAO();
    IssueBookDAO issueBookDAO = new IssueBookDAO();

    public DashboardFrame() {

        setTitle("Library Dashboard");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // HEADER

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25,118,210));
        headerPanel.setPreferredSize(new Dimension(1000,70));

        title = new JLabel("LIBRARY RECORD DIGITIZATION SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial",Font.BOLD,24));

        headerPanel.add(title);

        // SIDEBAR

        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(180,500));
        sidePanel.setLayout(new GridLayout(7,1,10,10));

        btnDashboard = new JButton("Dashboard");
        btnStudents = new JButton("Students");
        btnBooks = new JButton("Books");
        btnIssue = new JButton("Issue Book");
        btnReturn = new JButton("Return Book");
        btnReports = new JButton("Reports");
        btnLogout = new JButton("Logout");

        btnDashboard.addActionListener(this);
        btnStudents.addActionListener(this);
        btnBooks.addActionListener(this);
        btnIssue.addActionListener(this);
        btnReturn.addActionListener(this);
        btnReports.addActionListener(this);
        btnLogout.addActionListener(this);

        sidePanel.add(btnDashboard);
        sidePanel.add(btnStudents);
        sidePanel.add(btnBooks);
        sidePanel.add(btnIssue);
        sidePanel.add(btnReturn);
        sidePanel.add(btnReports);
        sidePanel.add(btnLogout);

        // CENTER

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,2,20,20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        booksCard = createCard("📚 Total Books : " + bookDAO.getBookCount());

        studentsCard = createCard("👨‍🎓 Total Students : " + studentDAO.getStudentCount());

        issuedCard = createCard("📖 Issued Books : " + issueBookDAO.getIssuedBookCount());

        dueCard = createCard("⏳ Due Books : " + issueBookDAO.getIssuedBookCount());

        centerPanel.add(booksCard);
        centerPanel.add(studentsCard);
        centerPanel.add(issuedCard);
        centerPanel.add(dueCard);

        add(headerPanel,BorderLayout.NORTH);
        add(sidePanel,BorderLayout.WEST);
        add(centerPanel,BorderLayout.CENTER);

        setVisible(true);
    }

    private JLabel createCard(String text){

        JLabel lbl = new JLabel(text,SwingConstants.CENTER);

        lbl.setOpaque(true);
        lbl.setBackground(new Color(230,240,255));
        lbl.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lbl.setFont(new Font("Arial",Font.BOLD,20));

        return lbl;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDashboard) {

            booksCard.setText("📚 Total Books : " + bookDAO.getBookCount());
            studentsCard.setText("👨‍🎓 Total Students : " + studentDAO.getStudentCount());
            issuedCard.setText("📖 Issued Books : " + issueBookDAO.getIssuedBookCount());
            dueCard.setText("⏳ Due Books : " + issueBookDAO.getIssuedBookCount());

        }
        else if (e.getSource() == btnStudents) {

            new StudentFrame();

        }
        else if (e.getSource() == btnBooks) {

            new BookFrame();

        }
        else if (e.getSource() == btnIssue) {

            new IssueBookFrame();

        }
        else if (e.getSource() == btnReturn) {

            new ReturnBookFrame();

        }
        else if (e.getSource() == btnReports) {

            new ReportFrame();

        }
        else if (e.getSource() == btnLogout) {

            dispose();
            new LoginFrame();

        }

    }

}