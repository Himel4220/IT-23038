import java.util.*;

// Store student ID -> student details using TreeMap
public class StudentTreeMap {

    public static void main(String[] args) {

        TreeMap<Integer, String> students = new TreeMap<>();

        students.put(101, "Alice - CSE");
        students.put(103, "Bob - IT");
        students.put(102, "Charlie - ECE");

        System.out.println("Students (Sorted by ID):");
        students.forEach((id, details) ->
                System.out.println(id + " -> " + details));
    }
}
