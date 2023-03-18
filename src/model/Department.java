package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Department extends DBModel implements DML {

    private String dept;
    private String manager;

    public Department() {
    }

    Department(ResultSet row) throws SQLException {
        this.dept = row.getString("dept");
        this.manager = row.getString("manager");
    }

    public Department(String dept, String manager) {
        this.dept = dept;
        this.manager = manager;
    }

    public String getDept() {
        return dept;
    }

    public String getManager() {
        return manager;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" + "dept=" + dept + ", manager=" + manager + '}';
    }

    public static Department getFromDept(String dept) throws ClassNotFoundException, SQLException {
        connect();
        String sql = "SELECT * FROM Department WHERE dept = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dept);
        ResultSet res = statement.executeQuery();
        res.next();
        Department department = new Department(res);
        connection.close();
        return department;
    }

    public static ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<Department> arr;
        connect();
        String sql = "SELECT * FROM Department;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new Department(res));
        }
        connection.close();
        return arr;
    }

    @Override
    public int dbInsert() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "INSERT INTO Department VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dept);
        statement.setString(2, manager);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbUpdate() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "UPDATE Department SET manager = ? WHERE dept = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, manager);
        statement.setString(2, dept);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbDelete() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "DELETE FROM Department WHERE dept = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dept);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public boolean isValid() {
        return (dept != null) && (!"".equals(dept)) && (!"".equals(manager));
    }

}
