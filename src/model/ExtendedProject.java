package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * Project v EmpProj
 */
public class ExtendedProject extends DBModel implements DQL {

    private Project project;
    private EmpProj empProj;

    public ExtendedProject() {
        this.project = new Project();
        this.empProj = new EmpProj();
    }

    ExtendedProject(ResultSet row) throws SQLException {
        this.project = new Project(row);
        this.empProj = new EmpProj(row);
    }

    private ExtendedProject(Project project, EmpProj empProj) {
        this.project = project;
        this.empProj = empProj;
    }
    public ExtendedProject(String projId, String projStartDate, int empNo, String location, int weeksProj) {
        this.project = new Project(projId, projStartDate);
        this.empProj = new EmpProj(empNo, projId, location, weeksProj);
    }

    public Project getProject() {
        return project;
    }

    public EmpProj getEmpProj() {
        return empProj;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEmpProj(EmpProj empProj) {
        this.empProj = empProj;
    }

    public int getEmpNo() {
        return empProj.getEmpNo();
    }

    public String getLocation() {
        return empProj.getLocation();
    }

    public int getWeeksProj() {
        return empProj.getWeeksProj();
    }

    public String getProjId() {
        return project.getProjId();
    }

    public String getProjStartDate() {
        return project.getProjStartDate();
    }

    @Override
    public String toString() {
        return "ExtendedProject{" + project.toString() + ", " + empProj.toString() + '}';
    }
    
    public ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<DBModel> arr;
        String sql = "SELECT * FROM Project p, EmpProj ep WHERE p.proj_id = ep.proj_id;";
        prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new ExtendedProject(res));
        }
        disconnect();
        return arr;
    }

    @Override
    public boolean isValid() {
        return project.isValid() && empProj.isValid();
    }

}
