package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * ExtendedEmpProj: Employee v EmpProj v Project
 */
public class ExtendedEmpProj extends DBModel implements DQL {

    private Employee employee;
    private EmpProj empProj;
    private Project project;

    public ExtendedEmpProj() {
        this.employee = new Employee();
        this.empProj = new EmpProj();
        this.project = new Project();
    }

    ExtendedEmpProj(ResultSet row) throws SQLException {
        this.employee = new Employee(row);
        this.empProj = new EmpProj(row);
        this.project = new Project(row);
    }

    private ExtendedEmpProj(Employee employee, EmpProj empProj, Project project) {
        this.employee = employee;
        this.empProj = empProj;
        this.project = project;
    }
    
    public ExtendedEmpProj(int empNo, String empName, String dept, String location, int weeksProj, String projId, String projStartDate) {
        this.employee = new Employee(empNo, empName, dept);
        this.empProj = new EmpProj(empNo, projId, location, weeksProj);
        this.project = new Project(projId, projStartDate);
    }

    public Employee getEmployee() {
        return employee;
    }

    public EmpProj getEmpProj() {
        return empProj;
    }

    public Project getProject() {
        return project;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEmpProj(EmpProj empProj) {
        this.empProj = empProj;
    }

    public void setProject(Project project) {
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

    public void setEmpNo(int empNo) {
        this.employee.setEmpNo(empNo);
        this.empProj.setEmpNo(empNo);
    }

    public void setEmpName(String empName) {
        this.employee.setEmpName(empName);
    }

    public void setDept(String dept) {
        this.employee.setDept(dept);
    }

    public void setLocation(String location) {
        this.empProj.setLocation(location);
    }

    public void setWeeksProj(int weeksProj) {
        this.empProj.setWeeksProj(weeksProj);
    }

    public void setProjId(String projId) {
        this.empProj.setProjId(projId);
        this.project.setProjId(projId);
    }

    public void setProjStartDate(String projStartDate) {
        this.project.setProjStartDate(projStartDate);
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
//        return employee.isValid() && empProj.isValid() && project.isValid();
        return empProj.isValid();
    }
}
