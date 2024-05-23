package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class TicTacToeController {

    public static final String SCORE_LOCATION_DEFAULT = "src/main/resources/ihm_project/tictactoe/score.xml";

    // use of a logger instead of a system.err.println() suggested by Sonar Lint for better maintainability
    Logger loggerTicTactToeController = Logger.getLogger(getClass().getName());

    protected Properties getScore() {
        Properties score = new Properties();
        try (InputStream scoreInputStream = new FileInputStream(SCORE_LOCATION_DEFAULT)) {
            score.loadFromXML(scoreInputStream);
        } catch (IOException e) {
            loggerTicTactToeController.info("Impossible to open score.xml, creating a new one");
            saveScore(score);
        }
        return score;
    }

    protected void saveScore(Properties score) {
        try (OutputStream scoreOutputStream = new FileOutputStream(SCORE_LOCATION_DEFAULT)) {
            score.storeToXML(scoreOutputStream, "");
        } catch (Exception e) {
            loggerTicTactToeController.info("Impossible to save score.xml");
        }
    }

    protected static void setRulesStage(Stage rulesStage) {
        TicTacToeController.rulesStage = rulesStage;
    }

    protected static void setNewGameStage(Stage newGameStage) {
        TicTacToeController.newGameStage = newGameStage;
    }

    protected static void setGameStage(Stage gameStage) {
        TicTacToeController.gameStage = gameStage;
    }

    protected static void setGameController(GameController gameController) {
        TicTacToeController.gameController = gameController;
    }

    protected static void setAboutStage(Stage aboutStage) {
        TicTacToeController.aboutStage = aboutStage;
    }

    protected static GameController getGameController() {
        return gameController;
    }

    @FXML
    private static Stage rulesStage;

    @FXML
    private static Stage aboutStage;

    @FXML
    private static Stage newGameStage;

    @FXML
    private static Stage gameStage;

    @FXML
    private static GameController gameController;

    @FXML
    protected void onRulesButtonClicked() {
        rulesStage.show();
    }

    @FXML
    protected void onAboutButtonClicked() {
        aboutStage.show();
    }

    @FXML
    protected void onRetryButtonClicked() {
        gameController.restartGame();
    }

    @FXML
    protected void onNewGameButtonClicked() {
        newGameStage.show();
        gameStage.hide();
        gameController.resetGame();
    }
}
