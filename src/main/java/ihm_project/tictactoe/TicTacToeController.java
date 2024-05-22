package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class TicTacToeController {
    @FXML
    public static Stage rulesScene;

    @FXML
    public static Stage aboutScene;

    @FXML
    public static Stage newGame;

    @FXML
    public static Stage game;

    @FXML
    public static GameController gameController;

    @FXML
    public void onRulesButtonClicked() {
        rulesScene.show();
    }

    @FXML
    public void onAboutButtonClicked() {
        aboutScene.show();
    }

    @FXML
    public void onRetryButtonClicked() {
        gameController.restartGame();
    }

    @FXML
    public void onNewGameButtonClicked() {
        newGame.show();
        game.hide();
        gameController.resetNewGame();
    }

    public Properties getScore() {
        Properties score = new Properties();
        try (InputStream scoreInputStream = new FileInputStream("score.xml")) {
            score.loadFromXML(scoreInputStream);
        } catch (IOException e) {
            System.err.println("Impossible to open score.xml, creating a new one");
            saveScore(score);
        }
        return score;
    }

    public void saveScore(Properties score) {
        try (OutputStream scoreOutputStream = new FileOutputStream("score.xml")) {
            score.storeToXML(scoreOutputStream,"");
        } catch (Exception e) {
            System.err.println("Impossible to save score.xml");
        }
    }
}
