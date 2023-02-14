package control;

import java.util.ArrayList;
import model.Employee;

public class JDBCDemo {

    public static void main(String[] args) {

        Employee employee = new Employee(52, "Morris", "Accounts");
        int r = employee.dbDelete();
        String message = (r > 0)
                ? "Database updated successfully!"
                : "Failed to update database!";
        System.out.println(message);

        ArrayList<Employee> arr = Employee.getResultSet();
        for (Employee e : arr) {
            System.out.println(e.toString());
        }
    }
}
