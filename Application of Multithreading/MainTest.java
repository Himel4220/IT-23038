import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        OrderQueue queue = new OrderQueue();

        // Create 3 delivery agents
        new DeliveryAgent("Agent-1", queue).start();
        new DeliveryAgent("Agent-2", queue).start();
        new DeliveryAgent("Agent-3", queue).start();

        Scanner scanner = new Scanner(System.in);
        int orderCount = 0;

        // Simulate order creation
        while (orderCount < 10) {
            System.out.println("Press Enter to place a new order... or type -1 to exit");

            String input = scanner.nextLine();
            if (input.equals("-1")) {
                break;
            }

            Order order = new Order();
            queue.addOrder(order);
            orderCount++;
        }

        scanner.close();
    }
}
