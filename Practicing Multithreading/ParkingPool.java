import java.util.LinkedList;
import java.util.Queue;

// Shared parking queue (like OrderQueue)
public class ParkingPool {

    private Queue<RegistrarParking> queue = new LinkedList<>();

    // Add parking request
    public synchronized void addParking(RegistrarParking request) {
        queue.add(request);
        System.out.println("New Car Arrived → Parking ID: " + request.getParkingId());
        notify(); // wake up waiting agent
    }

    // Get parking request
    public synchronized RegistrarParking getParking() {

        while(queue.isEmpty()) {
            try {
                wait(); // wait until request available
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.poll();
    }
}
