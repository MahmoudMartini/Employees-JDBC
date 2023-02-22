package model;

// another possible name: SQLModel
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

abstract class DBModel {

    protected static Connection connection;
//    protected final int CONNECTION_ERROR_CODE = -1;

    protected static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:Iyad.db";
        connection = DriverManager.getConnection(url);
    }

    protected static ArrayList getResultSet() throws ClassNotFoundException, SQLException {
        return null;
    }

    abstract public int dbInsert() throws ClassNotFoundException, SQLException;

    abstract public int dbUpdate() throws ClassNotFoundException, SQLException;

    abstract public int dbDelete() throws ClassNotFoundException, SQLException;

    abstract public boolean isValid();
}
