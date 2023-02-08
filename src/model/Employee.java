package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee {

    private int emp_no;
    private String emp_name;
    private String dept;

    public Employee(ResultSet row) {
        try {
            this.emp_no = row.getInt("emp_no");
            this.emp_name = row.getString("emp_name");
            this.dept = row.getString("dept");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }

    public Employee(int emp_no, String emp_name, String dept) {
        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.dept = dept;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee{" + "emp_no=" + emp_no + ", emp_name=" + emp_name + ", dept=" + dept + '}';
    }

    public static ResultSet getResultSet() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM Employee;");
        return res;
    }

    public int insertRecord() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");

        String sql = "INSERT INTO Employee VALUES (?, ?, ?);";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, Integer.toString(emp_no));
        statement.setString(2, emp_name);
        statement.setString(3, dept);

        int r = statement.executeUpdate();
        return r;
    }

    public int updateRecord() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");

        String sql = "UPDATE Employee SET emp_name = ?, dept = ? WHERE emp_no = ?;";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, emp_name);
        statement.setString(2, dept);
        statement.setString(3, Integer.toString(emp_no));

        int r = statement.executeUpdate();
        return r;
    }

    public int deleteRecord() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");

        String sql = "DELETE FROM Employee WHERE emp_no = ?;";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, Integer.toString(emp_no));

        int r = statement.executeUpdate();
        return r;
    }
}
