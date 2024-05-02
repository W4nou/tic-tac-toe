package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class TicTacToeController {
    @FXML
    public static Stage reglesScene;

    @FXML
    public static Stage aProposScene;

    @FXML
    void onReglesMenuButtonClicked() {
        reglesScene.show();
    }

    @FXML
    void onAProposMenuButtonClicked() {
        aProposScene.show();
    }
}
