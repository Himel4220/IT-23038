import java.lang.reflect.Field;
import java.util.ArrayList;

public class Array_List {
    public static void main(String[] args) throws Exception {

        ArrayList<String> list = new ArrayList<>(10);

        // Adding 10 elements (fills initial capacity)
        for(int i = 1; i <= 10; i++) {
            list.add("IT2300" + i);
        }

        System.out.println("Initial Size: " + list.size());
        System.out.println("Initial Capacity: " + getCapacity(list));

        // Adding one more element (exceeds capacity)
        list.add("IT23011");

        System.out.println("Size After Adding One More Element: " + list.size());
        System.out.println("Capacity After Exceeding: " + getCapacity(list));
    }

    // Method to get capacity using reflection
    private static int getCapacity(ArrayList<?> list) throws Exception {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] data = (Object[]) field.get(list);
        return data.length;
    }
}
