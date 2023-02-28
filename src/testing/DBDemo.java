package testing;

import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

public abstract class DBDemo {

    static Project project;
    static Employee employee;
    static Department department;
    static EmpProj empProj;
    static ExtendedProject exProject;
    
    public static void main(String[] args) {
        DBDemo.queryFromId();
//        DBDemo.query(new ExtendedProject());
//        DBDemo.query(new ExtendedEmployee());
        DBDemo.query(new ExtendedEmpProj());

//        System.out.println(new EmpProj(1, null, null, 1).isValid());
//        System.out.println(new EmpProj(1, "A", null, 1).isValid());
//        System.out.println(new EmpProj(1, null, "City", 1).isValid());
//        System.out.println(new EmpProj(1, "A", "City", 1).isValid());
//        System.out.println(new EmpProj(1, "A", "", 1).isValid());
//        System.out.println(new EmpProj(1, "", "City", 1).isValid());
//        System.out.println(new EmpProj(1, "", "", 1).isValid());
//        System.out.println(new EmpProj(0, "A", "City", 1).isValid());
//        System.out.println(new EmpProj(1, "A", "City", 0).isValid());
//        System.out.println(new EmpProj(0, "", "", 0).isValid());
    }

    public static void update(DML dml) {
        project = new Project("Z", "25-OCT-22");
        project = new Project("D", null);
        employee = new Employee(53, "Borriss", "Accounts");
        employee = new Employee(53, null, null);
        employee = new Employee(5, null, null);
        empProj = new EmpProj(7, "A", "Aleppo", 22);
        empProj = new EmpProj(7, "A", null, 0);
        department = new Department("HR", "Fadi");
        department = new Department("Accounts", null);
        
        int r = 0;
        try {
            r = dml.dbInsert();
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (r > 0) {
            System.out.println("Updated successfully");
        }
    }

    public static void query(DQL dql) {
        ArrayList<DQL> arr = null;
        try {
            arr = dql.getResultSet();
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        for (DQL e : arr) {
            System.out.println(e.toString());
        }
    }

    public static void queryFromId() {
        DQL dql = null;
        try {
            project = Project.getFromId("D");
            employee = Employee.getFromNo(5);
            empProj = EmpProj.getFromId(5, "A");
            department = Department.getFromDept("Accounts");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(project.toString());
    }

    abstract void withForeign();

    abstract void isValid();
}
