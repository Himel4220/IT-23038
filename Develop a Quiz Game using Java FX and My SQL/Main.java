import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        QuizGame quizGame = new QuizGame(stage);
        stage.setTitle("Quiz Game");
        stage.setScene(new Scene(quizGame.getStartPane(), 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
