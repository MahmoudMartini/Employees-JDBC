package jdbc.demo.dbmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Department {
    private String dept;
    private String manager;

    public Department(ResultSet row) {
        try {
            this.dept = row.getString("dept");
            this.manager = row.getString("manager");
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }

    
    public Department(String dept, String manager) {
        this.dept = dept;
        this.manager = manager;
    }

    public String getDept() {
        return dept;
    }

    public String getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "Department{" + "dept=" + dept + ", manager=" + manager + '}';
    }
    
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM Department;");
            
            res.next();
            Department e1 = new Department(res);
            System.out.println(e1.toString());
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
