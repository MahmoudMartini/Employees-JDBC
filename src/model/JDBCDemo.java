package model;
import java.sql.*;


public class JDBCDemo 
{
    
    public static void main(String[] args) 
    {
        Employee employee;
        try 
        {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM Employee;");

            while (res.next())
            {
                employee = new Employee(res);
                System.out.println(employee.toString());
            }
        } 
          catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
