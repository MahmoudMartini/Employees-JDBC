package jdbc.demo.dbmodel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpProj {

    private int emp_no;
    private String proj_id;
    private String location;
    private int weeks_proj;

    public EmpProj(ResultSet row) {
        try {
            this.emp_no = row.getInt("emp_no");
            this.proj_id = row.getString("proj_id");
            this.location = row.getString("location");
            this.weeks_proj = row.getInt("weeks_proj");
        } catch (SQLException ex) {
            Logger.getLogger(EmpProj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EmpProj(int emp_no, String proj_id, String location, int weeks_proj) {
        this.emp_no = emp_no;
        this.proj_id = proj_id;
        this.location = location;
        this.weeks_proj = weeks_proj;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public String getProj_id() {
        return proj_id;
    }

    public String getLocation() {
        return location;
    }

    public int getWeeks_proj() {
        return weeks_proj;
    }

    @Override
    public String toString() {
        return "EmpProj{" + "emp_no=" + emp_no + ", proj_id=" + proj_id + ", location=" + location + ", weeks_proj=" + weeks_proj + '}';
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:Iyad.db");
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM EmpProj;");

            res.next();
            EmpProj e1 = new EmpProj(res);
            System.out.println(e1.toString());

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
