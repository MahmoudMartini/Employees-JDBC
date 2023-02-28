package model;

import java.sql.SQLException;

// Another possible name: SQLStatement
public interface DML {
    abstract public int dbInsert() throws ClassNotFoundException, SQLException;

    abstract public int dbUpdate() throws ClassNotFoundException, SQLException;

    abstract public int dbDelete() throws ClassNotFoundException, SQLException;

}
