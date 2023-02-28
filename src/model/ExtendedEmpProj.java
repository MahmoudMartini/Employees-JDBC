package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExtendedEmpProj extends DBModel implements DQL {

    private Employee employee;
    private EmpProj empProj;
    private Project project;

    public ExtendedEmpProj() {
    }

    ExtendedEmpProj(ResultSet row) throws SQLException {
        this.employee = new Employee(row);
        this.empProj = new EmpProj(row);
        this.project = new Project(row);
    }

    public ExtendedEmpProj(Employee employee, EmpProj empProj, Project project) {
        this.employee = employee;
        this.empProj = empProj;
        this.project = project;
    }

    public int getEmpNo() {
        return employee.getEmpNo();
    }

    public String getEmpName() {
        return employee.getEmpName();
    }

    public String getDept() {
        return employee.getDept();
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
        return "ExtendedEmpProj{" + employee.toString() + ", " + empProj.toString() + ", " + project.toString() + '}';
    }

    @Override
    public ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<DBModel> arr;
        String sql = "SELECT * FROM Employee e, EmpProj ep, Project p WHERE e.emp_no = ep.emp_no AND ep.proj_id = p.proj_id;";
        prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        
        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new ExtendedEmpProj(res));
        }
        disconnect();
        return arr;
    }

    @Override
    public boolean isValid() {
        return employee.isValid() && empProj.isValid() && project.isValid();
    }
}
