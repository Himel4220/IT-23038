import java.lang.reflect.Field;
import java.util.Vector;

public class Vectorfile {
    public static void main(String[] args) throws Exception {

        Vector<String> vector = new Vector<>(10);

        // Adding 10 elements (fills initial capacity)
        for(int i = 1; i <= 10; i++) {
            vector.add("IT2300" + i);
        }

        System.out.println("Initial Size: " + vector.size());
        System.out.println("Initial Capacity: " + getCapacity(vector));

        // Adding one more element (exceeds capacity)
        vector.add("IT23011");

        System.out.println("Size After Adding One More Element: " + vector.size());
        System.out.println("Capacity After Exceeding: " + getCapacity(vector));
    }

    // Method to get capacity using reflection
    private static int getCapacity(Vector<?> vector) throws Exception {
        Field field = Vector.class.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] data = (Object[]) field.get(vector);
        return data.length;
    }
}
