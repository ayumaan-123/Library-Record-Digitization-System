package dao;

import database.DBConnection;
import model.IssueBook;

import java.sql.*;
import java.util.ArrayList;

public class IssueBookDAO {

    // Issue Book
    public boolean issueBook(IssueBook issue) {

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            con.setAutoCommit(false);

            String sql1 = "INSERT INTO issued_books(student_id,book_id,issue_date,return_date,status) VALUES(?,?,?,?,?)";

            PreparedStatement ps1 = con.prepareStatement(sql1);

            ps1.setInt(1, issue.getStudentId());
            ps1.setInt(2, issue.getBookId());
            ps1.setString(3, issue.getIssueDate());
            ps1.setString(4, issue.getReturnDate());
            ps1.setString(5, issue.getStatus());

            ps1.executeUpdate();

            String sql2 = "UPDATE books SET available = available - 1 WHERE book_id=? AND available>0";

            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps2.setInt(1, issue.getBookId());

            int rows = ps2.executeUpdate();

            if (rows == 0) {

                con.rollback();

                return false;

            }

            con.commit();

            return true;

        } catch (Exception e) {

            try {

                if (con != null)
                    con.rollback();

            } catch (Exception ex) {
            }

            e.printStackTrace();

        }

        return false;

    }


    // Load Issued Books

    public ArrayList<IssueBook> getAllIssuedBooks() {

        ArrayList<IssueBook> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM issued_books");

            while (rs.next()) {

                IssueBook i = new IssueBook();

                i.setIssueId(rs.getInt("issue_id"));
                i.setStudentId(rs.getInt("student_id"));
                i.setBookId(rs.getInt("book_id"));
                i.setIssueDate(rs.getString("issue_date"));
                i.setReturnDate(rs.getString("return_date"));
                i.setStatus(rs.getString("status"));

                list.add(i);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }


    public int getIssuedBookCount() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM issued_books WHERE status='Issued'");

            if (rs.next()) {

                count = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;

    }
}