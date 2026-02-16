import java.util.*;

// Implement Stack & Queue using PriorityQueue
public class PQStackQueue {

    public static void main(String[] args) {

        // Queue (FIFO) using increasing priority
        PriorityQueue<Integer> queue =
                new PriorityQueue<>(Comparator.naturalOrder());

        queue.add(3);
        queue.add(1);
        queue.add(2);

        System.out.println("Queue (FIFO):");
        while(!queue.isEmpty())
            System.out.print(queue.poll() + " ");

        System.out.println();

        // Stack (LIFO) using reverse order
        PriorityQueue<Integer> stack =
                new PriorityQueue<>(Comparator.reverseOrder());

        stack.add(3);
        stack.add(1);
        stack.add(2);

        System.out.println("Stack (LIFO):");
        while(!stack.isEmpty())
            System.out.print(stack.poll() + " ");
    }
}
