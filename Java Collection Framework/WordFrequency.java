import java.util.*;

// Count word frequency using TreeMap (sorted by key)
public class WordFrequency {

    public static void main(String[] args) {

        String text = "java is easy and java is powerful";
        String[] words = text.split(" ");

        TreeMap<String, Integer> map = new TreeMap<>();

        for(String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        System.out.println("Word Frequencies:");
        System.out.println(map);
    }
}
