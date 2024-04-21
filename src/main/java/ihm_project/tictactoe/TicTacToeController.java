package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class TicTacToeController {
    @FXML
    public Stage reglesScene;

    @FXML
    public Stage aProposScene;

    @FXML
    void onReglesMenuButtonClicked() {
        reglesScene.show();
    }

    @FXML
    void onAProposMenuButtonClicked() {
        aProposScene.show();
    }
}
