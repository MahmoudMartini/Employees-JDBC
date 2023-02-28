package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExtendedEmployee extends DBModel implements DQL {

    private Employee employee;
    private EmpProj empProj;

    public ExtendedEmployee() {
    }

    ExtendedEmployee(ResultSet row) throws SQLException {
        this.employee = new Employee(row);
        this.empProj = new EmpProj(row);
    }

    public ExtendedEmployee(Employee employee, EmpProj empProj) {
        this.employee = employee;
        this.empProj = empProj;
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

    public String getProjId() {
        return empProj.getProjId();
    }

    public String getLocation() {
        return empProj.getLocation();
    }

    public int getWeeksProj() {
        return empProj.getWeeksProj();
    }

    @Override
    public String toString() {
        return "ExtendedEmployee{" + employee.toString() + ", " + empProj.toString() + '}';
    }

    @Override
    public ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<DBModel> arr;
        String sql = "SELECT * FROM Employee e, EmpProj ep WHERE e.emp_no = ep.emp_no;";
        prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        
        arr = new ArrayList<>();
        while (res.next()) {
            arr.add(new ExtendedEmployee(res));
        }
        disconnect();
        return arr;
    }

    @Override
    public boolean isValid() {
        return employee.isValid() && empProj.isValid();
    }
}
