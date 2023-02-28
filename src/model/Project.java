package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Project extends DBModel implements DML, DQL {

    private String projId;
    private String projStartDate;

    public Project() {
    }

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

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public void setProjStartDate(String projStartDate) {
        this.projStartDate = projStartDate;
    }

    @Override
    public String toString() {
        return "Project{" + "projId=" + projId + ", projStartDate=" + projStartDate + '}';
    }

    @Override
    public Project getFromId(String projId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Project WHERE proj_id = ?;";
        prepareStatement(sql);
        statement.setString(1, projId);

        ResultSet res = statement.executeQuery();
        res.next();
        Project project = new Project(res);

        disconnect();
        return project;
    }

    @Override
    public ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Project;";
        prepareStatement(sql);

        ResultSet res = statement.executeQuery();
        ArrayList<Project> arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new Project(res));
        }

        disconnect();
        return arr;
    }

    @Override
    public int dbInsert() throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Project VALUES (?, ?);";
        prepareStatement(sql);
        statement.setString(1, projId);
        statement.setString(2, projStartDate);

        int rows = statement.executeUpdate();
        disconnect();
        return rows;
    }

    @Override
    public int dbUpdate() throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Project SET proj_start_date = ? WHERE proj_id = ?;";
        prepareStatement(sql);
        statement.setString(1, projStartDate);
        statement.setString(2, projId);

        int rows = statement.executeUpdate();
        disconnect();
        return rows;
    }

    @Override
    public int dbDelete() throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Project WHERE proj_id = ?;";
        prepareStatement(sql);
        statement.setString(1, projId);

        int rows = statement.executeUpdate();
        disconnect();
        return rows;
    }

    @Override
    public boolean isValid() {
        return (projId != null) && (!"".equals(projId)) && (!"".equals(projStartDate));
    }
}
