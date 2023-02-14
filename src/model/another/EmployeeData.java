package model.another;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

public class EmployeeData extends SqlRecord {

    private int emp_no;
    private String emp_name;
    private String dept;

    public EmployeeData(ResultSet row) {
        try {
            this.emp_no = row.getInt("emp_no");
            this.emp_name = row.getString("emp_name");
            this.dept = row.getString("dept");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }

    public EmployeeData(int emp_no, String emp_name, String dept) {
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
        return "EmployeeData{" + "emp_no=" + emp_no + ", emp_name=" + emp_name + ", dept=" + dept + '}';
    }

    public int dbInsert() throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Employee VALUES (?, ?, ?);";
        connect();
        statement = con.prepareStatement(sql);
        
        int n = statement.getMetaData().getColumnCount();
        statement.setString(1, Integer.toString(emp_no));
        statement.setString(2, emp_name);
        statement.setString(3, dept);
        
        int r = statement.executeUpdate();
        return r;
    }
    
    public static void foo() {
        System.out.println("In child class");
    }
}
