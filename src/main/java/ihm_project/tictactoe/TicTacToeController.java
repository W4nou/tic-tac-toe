package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class TicTacToeController {

    public static final String SCORE_LOCATION_DEFAULT = "src/main/resources/ihm_project/tictactoe/score.xml";

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
        gameController.resetGame();
    }

    public Properties getScore() {
        Properties score = new Properties();
        try (InputStream scoreInputStream = new FileInputStream(SCORE_LOCATION_DEFAULT)) {
            score.loadFromXML(scoreInputStream);
        } catch (IOException e) {
            System.err.println("Impossible to open score.xml, creating a new one");
            saveScore(score);
        }
        return score;
    }

    public void saveScore(Properties score) {
        try (OutputStream scoreOutputStream = new FileOutputStream(SCORE_LOCATION_DEFAULT)) {
            score.storeToXML(scoreOutputStream, "");
        } catch (Exception e) {
            System.err.println("Impossible to save score.xml");
        }
    }
}
