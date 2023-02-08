package control;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

public class JDBCDemo {

    public static void main(String[] args) {
        
        try {
            Employee employee = new Employee(52, "Adam", "Accounts");
            
//            int r = employee.insertRecord();
            int r = employee.updateRecord();
//            int r = employee.deleteRecord();
            
            if (r > 0) {
                System.out.println("Database updated successfully!");    
            } else {
                System.out.println("Failed to update database!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ResultSet res = Employee.getResultSet();

            while (res.next()) {
                Employee employee = new Employee(res);
                System.out.println(employee.toString());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
