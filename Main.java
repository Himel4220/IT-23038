//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String user = "root";
        String password = "ictt";

        try {
            // Connect to MySQL server
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    user,
                    password
            );
            Statement stmt = conn.createStatement();

            // Create database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS testdb");

            // Reconnect using the database
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    user,
                    password
            );
            stmt = conn.createStatement();

            // Create table
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS student (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(50)" +
                            ")"
            );

            System.out.println("Database and table created successfully.");
            // INSERT VALUES (this is what you asked for)
            String insertSQL = "INSERT INTO student(name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(insertSQL);

            ps.setString(1, "Arafat");
            ps.executeUpdate();

            ps.setString(1, "Hossain");
            ps.executeUpdate();

            System.out.println("Database, table, and values created successfully.");

            conn.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}