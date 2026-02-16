public // Represents a single parking request (like Order)
public class RegistrarParking {

    private static int counter = 1;   // auto-increment ID
    private int parkingId;

    public RegistrarParking() {
        this.parkingId = counter++;
    }

    public int getParkingId() {
        return parkingId;
    }
}
 {
    
}
