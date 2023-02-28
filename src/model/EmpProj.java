package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpProj extends DBModel implements DML {

    private int empNo;
    private String projId;
    private String location;
    private int weeksProj;

    EmpProj(ResultSet row) throws SQLException {
        this.empNo = row.getInt("emp_no");
        this.projId = row.getString("proj_id");
        this.location = row.getString("location");
        this.weeksProj = row.getInt("weeks_proj");
    }

    public EmpProj(int emp_no, String proj_id, String location, int weeks_proj) {
        this.empNo = emp_no;
        this.projId = proj_id;
        this.location = location;
        this.weeksProj = weeks_proj;
    }

    public int getEmpNo() {
        return empNo;
    }

    public String getProjId() {
        return projId;
    }

    public String getLocation() {
        return location;
    }

    public int getWeeksProj() {
        return weeksProj;
    }

    @Override
    public String toString() {
        return "EmpProj{" + "emp_no=" + empNo + ", proj_id=" + projId + ", location=" + location + ", weeks_proj=" + weeksProj + '}';
    }

    public static EmpProj getFromId(int empNo, String projId) throws ClassNotFoundException, SQLException {
        connect();
        String sql = "SELECT * FROM EmpProj WHERE emp_no = ? AND proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, empNo);
        statement.setString(2, projId);
        ResultSet res = statement.executeQuery();
        res.next();
        return new EmpProj(res);
    }

    public static ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<EmpProj> arr;
        connect();
        String sql = "SELECT * FROM EmpProj;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new EmpProj(res));
        }
        connection.close();
        return arr;
    }

    public static ArrayList getResultSet(Employee employee) throws ClassNotFoundException, SQLException {
        ArrayList<EmpProj> arr;
        connect();
        String sql = "SELECT * FROM EmpProj ep, Employee e WHERE ep.emp_no = e.emp_no AND e.emp_no = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employee.getEmpNo());
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new EmpProj(res));
        }
        connection.close();
        return arr;
    }

    public static ArrayList getResultSet(Project project) throws ClassNotFoundException, SQLException {
        ArrayList<EmpProj> arr;
        connect();
        String sql = "SELECT * FROM EmpProj ep, Project p WHERE ep.proj_id = p.proj_id AND p.proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, project.getProjId());
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new EmpProj(res));
        }
        connection.close();
        return arr;
    }

    @Override
    public int dbInsert() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "INSERT INTO EmpProj VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, empNo);
        statement.setString(2, projId);
        statement.setString(3, location);
        statement.setInt(4, weeksProj);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbUpdate() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "UPDATE EmpProj SET location = ?, weeks_proj = ? WHERE emp_no = ? AND proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, location);
        statement.setInt(2, weeksProj);
        statement.setInt(3, empNo);
        statement.setString(4, projId);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbDelete() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "DELETE FROM EmpProj WHERE emp_no = ? AND proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, empNo);
        statement.setString(2, projId);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public boolean isValid() {
        return (empNo > 0) && (projId != null) && (!"".equals(projId)) && (!"".equals(location)) && (weeksProj > 0);
    }
}
