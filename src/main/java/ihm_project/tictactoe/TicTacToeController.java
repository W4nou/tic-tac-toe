package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;

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
        gameController.resetBoard();
    }

    @FXML
    public void onNewGameButtonClicked() {
        newGame.show();
        game.hide();
        gameController.reset();
    }
}
