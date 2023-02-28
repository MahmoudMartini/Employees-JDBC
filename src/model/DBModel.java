package model;

// another possible name: SQLModel
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class DBModel {

    protected static Connection connection;
    protected static PreparedStatement statement;
    protected static String sql;

//    DBModel() {
//    }
    
    protected static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:Iyad.db";
        connection = DriverManager.getConnection(url);
    }

    protected static PreparedStatement prepareStatement(String sql) throws ClassNotFoundException, SQLException {
        connect();
        statement = connection.prepareStatement(sql);
        return statement;
    }

    protected static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }

//    protected ArrayList dbQuery() throws SQLException { return null; }
    
    abstract public boolean isValid();
}
