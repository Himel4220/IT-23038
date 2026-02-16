public import java.util.*;

// Store employee ID -> department using HashMap
public class EmployeeHashMap {

    public static void main(String[] args) {

        HashMap<Integer, String> employees = new HashMap<>();

        employees.put(1, "HR");
        employees.put(2, "Finance");
        employees.put(3, "IT");

        System.out.println("Employee Details:");
        employees.forEach((id, dept) ->
                System.out.println(id + " -> " + dept));
    }
}
 {
    
}
