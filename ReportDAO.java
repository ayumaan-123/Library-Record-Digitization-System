package dao;

import database.DBConnection;
import model.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportDAO {

    public ArrayList<Report> getAllReports() {

        ArrayList<Report> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT ib.issue_id,s.student_id,s.name,b.book_id,b.title," +
                            "ib.issue_date,ib.return_date,ib.status " +
                            "FROM issued_books ib " +
                            "JOIN students s ON ib.student_id=s.student_id " +
                            "JOIN books b ON ib.book_id=b.book_id";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Report r = new Report();

                r.setIssueId(rs.getInt("issue_id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setStudentName(rs.getString("name"));
                r.setBookId(rs.getInt("book_id"));
                r.setBookTitle(rs.getString("title"));
                r.setIssueDate(rs.getString("issue_date"));
                r.setReturnDate(rs.getString("return_date"));
                r.setStatus(rs.getString("status"));

                list.add(r);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

}