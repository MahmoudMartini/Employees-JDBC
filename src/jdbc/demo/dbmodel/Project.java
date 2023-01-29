package jdbc.demo.dbmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project {

    private String projId;
    private String projStartDate;

    public Project(ResultSet row) {
        try {
            this.projId = row.getString("proj_id");
            this.projStartDate = row.getString("proj_start_date");
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Project(String projId, String projStartDate) {
        this.projId = projId;
        this.projStartDate = projStartDate;
    }

    @Override
    public String toString() {
        return "Project{" + "projId=" + projId + ", projStartDate=" + projStartDate + '}';
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM Project;");

            res.next();
            Project e1 = new Project(res);
            System.out.println(e1.toString());

            String sqlInsert = "INSERT INTO Project Values (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInsert);
            stmt.setString(1, "F");
            stmt.setString(2, "10-11-99");
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getProjId() {
        return projId;
    }

    public String getProjStartDate() {
        return projStartDate;
    }

    public boolean valid() {
        return !"".equals(projId) && !"".equals(projStartDate);
    }
}
