import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.*;
import java.util.ArrayList;

public class QuizGame {

    private Stage stage;
    private ArrayList<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;
    private String playerName;

    private Label questionLabel = new Label();
    private RadioButton optionA = new RadioButton();
    private RadioButton optionB = new RadioButton();
    private RadioButton optionC = new RadioButton();
    private RadioButton optionD = new RadioButton();
    private ToggleGroup group = new ToggleGroup();

    private Label timerLabel = new Label();
    private Timeline timeline;
    private IntegerProperty timeSeconds;

    public QuizGame(Stage stage) {
        this.stage = stage;
    }

    // ---------------- START SCREEN ----------------
    public Pane getStartPane() {

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("Welcome to Quiz Game");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Button startBtn = new Button("Start");
        Button exitBtn = new Button("Exit");

        startBtn.setOnAction(e -> {
            playerName = nameField.getText();
            loadQuestions();
            showGameScene();
        });

        exitBtn.setOnAction(e -> Platform.exit());

        layout.getChildren().addAll(title, nameField, startBtn, exitBtn);
        return layout;
    }

    // ---------------- LOAD RANDOM QUESTIONS ----------------
    private void loadQuestions() {
        questions.clear();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM questions ORDER BY RAND() LIMIT 5";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                questions.add(new Question(
                        rs.getString("question"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getString("correct_answer")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- GAME SCREEN ----------------
    private void showGameScene() {

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        optionA.setToggleGroup(group);
        optionB.setToggleGroup(group);
        optionC.setToggleGroup(group);
        optionD.setToggleGroup(group);

        Button nextBtn = new Button("Next");
        Button restartBtn = new Button("Restart");
        Button exitBtn = new Button("Exit");

        nextBtn.setOnAction(e -> nextQuestion());
        restartBtn.setOnAction(e -> restartGame());
        exitBtn.setOnAction(e -> Platform.exit());

        layout.getChildren().addAll(
                questionLabel,
                optionA, optionB, optionC, optionD,
                timerLabel,
                nextBtn, restartBtn, exitBtn
        );

        stage.setScene(new Scene(layout, 600, 400));

        currentIndex = 0;
        score = 0;
        showQuestion();
    }

    // ---------------- DISPLAY QUESTION ----------------
    private void showQuestion() {

        if (currentIndex >= questions.size()) {
            saveScore();
            showResult();
            return;
        }

        Question q = questions.get(currentIndex);

        questionLabel.setText(q.getQuestion());
        optionA.setText("A. " + q.getOptionA());
        optionB.setText("B. " + q.getOptionB());
        optionC.setText("C. " + q.getOptionC());
        optionD.setText("D. " + q.getOptionD());

        group.selectToggle(null);

        startTimer();
    }

    // ---------------- NEXT QUESTION ----------------
    private void nextQuestion() {

        if (group.getSelectedToggle() != null) {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            String answer = selected.getText().substring(0,1);

            if (answer.equals(questions.get(currentIndex).getCorrectAnswer())) {
                score++;
            }
        }

        timeline.stop();
        currentIndex++;
        showQuestion();
    }

    // ---------------- TIMER ----------------
    private void startTimer() {
        timeSeconds = new SimpleIntegerProperty(15);
        timerLabel.textProperty().bind(timeSeconds.asString("Time left: %d"));

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    timeSeconds.set(timeSeconds.get() - 1);
                    if (timeSeconds.get() <= 0) {
                        timeline.stop();
                        currentIndex++;
                        showQuestion();
                    }
                })
        );
        timeline.setCycleCount(15);
        timeline.play();
    }

    // ---------------- SAVE SCORE ----------------
    private void saveScore() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, playerName);
            ps.setInt(2, score);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- RESULT SCREEN ----------------
    private void showResult() {

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Label result = new Label("Final Score: " + score + "/5");

        Button restart = new Button("Play Again");
        Button exit = new Button("Exit");

        restart.setOnAction(e -> {
            loadQuestions();
            showGameScene();
        });

        exit.setOnAction(e -> Platform.exit());

        layout.getChildren().addAll(result, restart, exit);

        stage.setScene(new Scene(layout, 600, 400));
    }

    // ---------------- RESTART ----------------
    private void restartGame() {
        loadQuestions();
        showGameScene();
    }
}
