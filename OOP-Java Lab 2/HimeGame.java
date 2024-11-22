import java.io.BufferedReader;     // For reading text from an input stream (like files)
import java.io.BufferedWriter;               // To represent files or directories
import java.io.FileReader;         // For reading from files using character streams
import java.io.FileWriter;         // For writing characters to a file
import java.io.IOException;        // For handling I/O exceptions
import java.util.ArrayList;     // For writing text to an output stream efficiently
import java.util.List;             // For using the List interface, which is part of the Collection framework
import java.util.Scanner;        // For using the ArrayList class, which is a resizable array implementation of the List interface

public class HimeGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the player's name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        // Get the difficulty level (1 - Easy, 2 - Medium, 3 - Hard)
        System.out.print("Enter difficulty level (1: Easy, 2: Medium, 3: Hard): ");
        int difficultyLevel = scanner.nextInt();

        // Validate the difficulty level
        if (difficultyLevel < 1 || difficultyLevel > 3) {
            System.out.println("Invalid difficulty level. Exiting...");
            return;
        }

        // Map the selected difficulty level to its corresponding string
        String difficultyTag = "";
        switch (difficultyLevel) {
            case 1: difficultyTag = "EASY"; break;
            case 2: difficultyTag = "MEDIUM"; break;
            case 3: difficultyTag = "HARD"; break;
        }

        // Read the questions from the file and filter by difficulty
        List<String[]> questions = loadQuestions(difficultyTag);

        if (questions.isEmpty()) {
            System.out.println("No questions found for the selected difficulty.");
            return;
        }

        // Score initialization
        int score = 0;

        // Ask questions based on the selected difficulty
        for (String[] question : questions) {
            String questionText = question[0];
            int correctAnswer = Integer.parseInt(question[1]);

            // Ask the question
            System.out.print("Question: " + questionText + " ");
            int playerAnswer = scanner.nextInt();

            // Check if the answer is correct
            if (playerAnswer == correctAnswer) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was " + correctAnswer);
            }
        }

        // Display the final score
        System.out.println("\nYour final score is: " + score);

        // Save the player's data into the output file
        savePlayerData(playerName, difficultyLevel, score);

        scanner.close();
    }

    // Load questions from a file and filter by difficulty
    public static List<String[]> loadQuestions(String difficultyTag) {
        List<String[]> questions = new ArrayList<>();
        String fileName = "input.txt";  // File containing questions

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by ';'
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equalsIgnoreCase(difficultyTag)) {
                    // Add the question and the correct answer (as a string array)
                    questions.add(new String[] {parts[1], parts[2]});
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading questions file.");
            e.printStackTrace();
        }

        return questions;
    }

    // Save the player's name, difficulty level, and score to the output file
    public static void savePlayerData(String playerName, int difficultyLevel, int score) {
        String fileName = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Player: " + playerName + ", Difficulty: " + difficultyLevel + ", Score: " + score + " points\n");
            System.out.println("Player data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving player data to file.");
            e.printStackTrace();
        }
    }
} 