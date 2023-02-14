package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee extends DbModel {

    private int emp_no;
    private String emp_name;
    private String dept;

    Employee(ResultSet row) {
        try {
            this.emp_no = row.getInt("emp_no");
            this.emp_name = row.getString("emp_name");
            this.dept = row.getString("dept");
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<Employee> getResultSet() {
        ArrayList<Employee> arr = null;
        try {
            connect();
            String sql = "SELECT * FROM Employee;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();

            arr = new ArrayList<>();
            while (res.next()) {
                arr.add(new Employee(res));
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    @Override
    public int dbInsert() {
        try {
            connect();
            String sql = "INSERT INTO Employee VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(emp_no));
            statement.setString(2, emp_name);
            statement.setString(3, dept);

            int rows = statement.executeUpdate();
            statement.close();
            connection.close();
            return rows;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return CONNECTION_ERROR_CODE;
        }
    }

    @Override
    public int dbUpdate() {
        try {
            connect();
            String sql = "UPDATE Employee SET emp_name = ?, dept = ? WHERE emp_no = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, emp_name);
            statement.setString(2, dept);
            statement.setString(3, Integer.toString(emp_no));

            int rows = statement.executeUpdate();
            statement.close();
            connection.close();
            return rows;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return CONNECTION_ERROR_CODE;
        }
    }

    @Override
    public int dbDelete() {
        try {
            connect();
            String sql = "DELETE FROM Employee WHERE emp_no = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(emp_no));

            int rows = statement.executeUpdate();
            statement.close();
            connection.close();
            return rows;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return CONNECTION_ERROR_CODE;
        }
    }
}
