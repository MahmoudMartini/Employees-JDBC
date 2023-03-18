package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Employee v Department
 */
public class ExtendedEmployee extends DBModel implements DQL {

    private Employee employee;
    private Department department;

    public ExtendedEmployee() {
        this.employee = new Employee();
        this.department = new Department();
    }

    ExtendedEmployee(ResultSet row) throws SQLException {
        this.employee = new Employee(row);
        this.department = new Department(row);
    }

    private ExtendedEmployee(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    public ExtendedEmployee(int empNo, String empName, String dept, String manager) {
        this.employee = new Employee(empNo, empName, dept);
        this.department = new Department(dept, manager);
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

    public String getManager() {
        return department.getManager();
    }

    @Override
    public String toString() {
        return "ExtendedEmployee{" + employee.toString() + ", " + department.toString() + '}';
    }

    @Override
    public ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        ArrayList<DBModel> arr;
        String sql = "SELECT * FROM Employee e, department d WHERE e.dept = d.dept;";
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
        return employee.isValid() && department.isValid();
    }
}
