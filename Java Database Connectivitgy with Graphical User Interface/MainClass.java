import java.util.Scanner;

// Main class to simulate N number of car parking
public class MainClass {

    public static void main(String[] args) {

        ParkingPool pool = new ParkingPool();

        // Create 3 parking agents
        new ParkingAgent("Agent-1", pool).start();
        new ParkingAgent("Agent-2", pool).start();
        new ParkingAgent("Agent-3", pool).start();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of cars to park: ");
        int n = sc.nextInt();

        // Generate N parking requests
        for(int i = 0; i < n; i++) {
            RegistrarParking request = new RegistrarParking();
            pool.addParking(request);

            try {
                Thread.sleep(500); // simulate arrival delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sc.close();
    }
}
