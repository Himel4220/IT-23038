import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private final Queue<Order> orders = new LinkedList<>();

    public synchronized void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order #" + order.getOrderId() + " placed.");
        notifyAll(); // Notify waiting delivery agents
    }

    public synchronized Order getOrder() {
        while (orders.isEmpty()) {
            try {
                wait(); // Wait until an order is added
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return orders.poll();
    }
}
