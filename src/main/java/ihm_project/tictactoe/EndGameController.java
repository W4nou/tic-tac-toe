package ihm_project.tictactoe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGameController extends TicTacToeController{

    @FXML
    private Stage endGameStage;

    @FXML
    private Label gameResultLabel;

    public void setEndGameStage(Stage endGameStage){
        this.endGameStage = endGameStage;
    }

    public void displayWinner(String name) {
        if (name == null) {
            gameResultLabel.setText("Il n'y a aucun gagnant \u00E0 cette partie");
            return;
        }
        gameResultLabel.setText(name+" \u00E0 gagn\u00E9 la partie");
        endGameStage.show();
    }

    @FXML
    public void onExitButtonClicked(){
        Platform.exit();
    }

    @Override
    public void onRetryButtonClicked() {
        endGameStage.hide();
        super.onRetryButtonClicked();
    }

    @Override
    public void onNewGameButtonClicked(){
        endGameStage.hide();
        super.onNewGameButtonClicked();
    }
}
