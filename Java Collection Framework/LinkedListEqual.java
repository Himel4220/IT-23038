import java.util.*;

// Check equality of two LinkedLists
public class LinkedListEqual {

    public static void main(String[] args) {

        LinkedList<Integer> list1 =
                new LinkedList<>(Arrays.asList(1,2,3));

        LinkedList<Integer> list2 =
                new LinkedList<>(Arrays.asList(1,2,3));

        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println("Are Equal? " + list1.equals(list2));
    }
}

