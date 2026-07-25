package dao;

import database.DBConnection;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    // Save Student
    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students(roll_no,name,department,year,phone,email) VALUES(?,?,?,?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getYear());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getEmail());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    // Get All Students
    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {

                Student s = new Student();

                s.setStudentId(rs.getInt("student_id"));
                s.setRollNo(rs.getString("roll_no"));
                s.setName(rs.getString("name"));
                s.setDepartment(rs.getString("department"));
                s.setYear(rs.getString("year"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));

                list.add(s);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }


    public boolean updateStudent(Student student) {

        String sql = "UPDATE students SET roll_no=?,name=?,department=?,year=?,phone=?,email=? WHERE student_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getYear());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getEmail());
            ps.setInt(7, student.getStudentId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }
    public boolean deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }


    public int getStudentCount() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM students");

            if (rs.next()) {

                count = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;

    }
}