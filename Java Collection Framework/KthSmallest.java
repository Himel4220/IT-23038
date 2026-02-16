import java.util.*;

// Program to find kth smallest element in ArrayList
public class KthSmallest {

    public static int kthSmallest(ArrayList<Integer> list, int k) {
        Collections.sort(list); // sort list
        return list.get(k - 1); // kth smallest (1-based index)
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 7));

        int k = 3;
        System.out.println("List: " + list);
        System.out.println(k + "rd Smallest Element: " + kthSmallest(list, k));
    }
}
