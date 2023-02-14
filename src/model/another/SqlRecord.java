package model.another;

import java.sql.*;

public class SqlRecord {
    Connection con;
    PreparedStatement statement;
    ResultSet res;
    private String className = "org.sqlite.JDBC";
//    Object obj = new org.sqlite.JDBC();
    private String url = "jdbc:sqlite:Iyad.db";
    
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        con = DriverManager.getConnection(url);
    }
    
    public static void foo() {
        System.out.println("In parent class");
    }
    
    
}
