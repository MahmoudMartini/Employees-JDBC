package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employee extends DBModel implements DML {

    private int empNo;
    private String empName = "";
    private String dept = "";

    public Employee() {
    }

    Employee(ResultSet row) throws SQLException {
        this.empNo = row.getInt("emp_no");
        this.empName = row.getString("emp_name");
        this.dept = row.getString("dept");
    }

    public Employee(int emp_no, String emp_name, String dept) {
        this.empNo = emp_no;
        this.empName = emp_name;
        this.dept = dept;
    }

    public int getEmpNo() {
        return empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee{" + "emp_no=" + empNo + ", emp_name=" + empName + ", dept=" + dept + '}';
    }

    public static Employee getFromNo(int empNO) throws ClassNotFoundException, SQLException {
        connect();
        String sql = "SELECT * FROM Employee WHERE emp_no = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, empNO);
        ResultSet res = statement.executeQuery();
        res.next();
        return new Employee(res);
    }

    public static ArrayList<Employee> getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<Employee> arr = null;
        connect();
        String sql = "SELECT * FROM Employee;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new Employee(res));
        }
        connection.close();
        return arr;
    }

    public static ArrayList<Employee> getResultSet(Department department) throws ClassNotFoundException, SQLException {
        ArrayList<Employee> arr = null;
        connect();
        String sql = "SELECT * FROM Employee e, department d WHERE e.dept = d.dept AND d.dept = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, department.getDept());
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new Employee(res));
        }
        connection.close();
        return arr;
    }

    @Override
    public int dbInsert() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "INSERT INTO Employee VALUES (?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, empNo);
        statement.setString(2, empName);
        statement.setString(3, dept);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbUpdate() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "UPDATE Employee SET emp_name = ?, dept = ? WHERE emp_no = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, empName);
        statement.setString(2, dept);
        statement.setInt(3, empNo);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbDelete() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "DELETE FROM Employee WHERE emp_no = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, empNo);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public boolean isValid() {
        return (empNo > 0) && (!"".equals(empName)) && (!"".equals(dept));
    }
}
