// Parking agent processes car parking requests
public class ParkingAgent extends Thread {

    private ParkingPool pool;

    public ParkingAgent(String name, ParkingPool pool) {
        super(name);
        this.pool = pool;
    }

    @Override
    public void run() {

        while(true) {
            RegistrarParking request = pool.getParking();

            System.out.println(getName() +
                    " is parking Car ID: " + request.getParkingId());

            try {
                Thread.sleep(1000); // simulate parking time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getName() +
                    " completed Parking ID: " + request.getParkingId());
        }
    }
}
