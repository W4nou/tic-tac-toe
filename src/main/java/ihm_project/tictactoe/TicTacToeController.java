package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.* ;

public class TicTacToeController {
    @FXML
    public static Stage reglesScene;

    @FXML
    public static Stage aProposScene;

    @FXML
    public static Stage nouvellePartie;

    @FXML
    public static Stage game;

    @FXML
    public static GameController gameController;

    @FXML
    void onReglesMenuButtonClicked() {
        reglesScene.show();
    }

    @FXML
    void onAProposMenuButtonClicked() {
        aProposScene.show();
    }

    @FXML
    void onNewGameButtonClicked() {
        nouvellePartie.show();
        game.hide();
        gameController.reset();
    }
}
