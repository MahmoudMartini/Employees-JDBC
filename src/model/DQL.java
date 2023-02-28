package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DQL {

    public DQL getFromId(String id) throws ClassNotFoundException, SQLException;

    public ArrayList getResultSet() throws ClassNotFoundException, SQLException;
}
