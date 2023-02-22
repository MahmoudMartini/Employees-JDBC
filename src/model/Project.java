package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Project extends DBModel {

    private String projId;
    private String projStartDate;

    Project(ResultSet row) throws SQLException {
        this.projId = row.getString("proj_id");
        this.projStartDate = row.getString("proj_start_date");
    }

    public Project(String projId, String projStartDate) {
        this.projId = projId;
        this.projStartDate = projStartDate;
    }

    public String getProjId() {
        return projId;
    }

    public String getProjStartDate() {
        return projStartDate;
    }

    @Override
    public String toString() {
        return "Project{" + "projId=" + projId + ", projStartDate=" + projStartDate + '}';
    }

    public static Project getFromId(String projId) throws ClassNotFoundException, SQLException {
        connect();
        String sql = "SELECT * FROM Project WHERE proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, projId);
        ResultSet res = statement.executeQuery();
        res.next();
        return new Project(res);
    }
    
    public static ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<Project> arr;
        connect();
        String sql = "SELECT * FROM Project;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new Project(res));
        }
        connection.close();
        return arr;
    }
    
    @Override
    public int dbInsert() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "INSERT INTO Project VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, projId);
        statement.setString(2, projStartDate);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbUpdate() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "UPDATE Project SET proj_start_date = ? WHERE proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, projStartDate);
        statement.setString(2, projId);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public int dbDelete() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "DELETE FROM Project WHERE proj_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, projId);

        int rows = statement.executeUpdate();
        statement.close();
        connection.close();
        return rows;
    }

    @Override
    public boolean isValid() {
        return (projId != null) && (!"".equals(projId)) && (!"".equals(projStartDate));
    }
}
