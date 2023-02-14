package model.another;

import java.sql.SQLException;

// Another possible name: SQLStatement
public interface DML {
    int dbInsert() throws ClassNotFoundException, SQLException;
    int dbUpdate() throws ClassNotFoundException, SQLException;
    int dbDelete() throws ClassNotFoundException, SQLException;
}
