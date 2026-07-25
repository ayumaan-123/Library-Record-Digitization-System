package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReturnBookDAO {

    public boolean returnBook(int issueId, int bookId) {

        Connection con = null;

        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // Update Issue Status
            String sql1 = "UPDATE issued_books SET status='Returned' WHERE issue_id=?";

            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, issueId);

            int row1 = ps1.executeUpdate();

            if (row1 == 0) {
                con.rollback();
                return false;
            }

            // Increase Available Books
            String sql2 = "UPDATE books SET available = available + 1 WHERE book_id=?";

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, bookId);

            ps2.executeUpdate();

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

}