package dao;

import database.DBConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {

    // Add Book
    public boolean addBook(Book book) {

        String sql = "INSERT INTO books(title,author,category,quantity,available) VALUES(?,?,?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getAvailable());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // Get All Books
    public ArrayList<Book> getAllBooks() {

        ArrayList<Book> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM books");

            while (rs.next()) {

                Book b = new Book();

                b.setBookId(rs.getInt("book_id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCategory(rs.getString("category"));
                b.setQuantity(rs.getInt("quantity"));
                b.setAvailable(rs.getInt("available"));

                list.add(b);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    // Update Book
    public boolean updateBook(Book book) {

        String sql = "UPDATE books SET title=?,author=?,category=?,quantity=?,available=? WHERE book_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getAvailable());
            ps.setInt(6, book.getBookId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // Delete Book
    public boolean deleteBook(int bookId) {

        String sql = "DELETE FROM books WHERE book_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }


    public int getBookCount() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM books");

            if (rs.next()) {

                count = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;

    }
}