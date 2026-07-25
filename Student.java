package model;

public class Student {

    private int studentId;
    private String rollNo;
    private String name;
    private String department;
    private String year;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(int studentId, String rollNo, String name,
                   String department, String year,
                   String phone, String email) {

        this.studentId = studentId;
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.year = year;
        this.phone = phone;
        this.email = email;
    }

    public Student(String rollNo, String name,
                   String department, String year,
                   String phone, String email) {

        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.year = year;
        this.phone = phone;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}