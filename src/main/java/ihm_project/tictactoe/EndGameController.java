package ihm_project.tictactoe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Properties;

public class EndGameController extends TicTacToeController {

    @FXML
    private Stage endGameStage;

    @FXML
    private Label gameResultLabel;

    private Properties score = super.getScore();

    public void setEndGameStage(Stage endGameStage) {
        this.endGameStage = endGameStage;
    }

    public void displayWinner(String name) {
        if (name == null) {
            gameResultLabel.setText("Il n'y a aucun gagnant \u00E0 cette partie");
        } else {
            updateScore(name);
            gameResultLabel.setText(name + " \u00E0 gagn\u00E9 la partie");
        }
        endGameStage.show();
    }

    private void updateScore(String name) {
        if (name.equals(Player.P1_NAME_DEFAULT) || Objects.equals(name, Player.P2_NAME_DEFAULT)) {
            return;
        }

        String scoreKey = score.getProperty(name);
        int scoreValue;
        if (scoreKey != null) {
            scoreValue = Integer.parseInt(scoreKey);
        } else {
            scoreValue = 0;
        }
        score.setProperty(name, String.valueOf(scoreValue + 1));
        super.saveScore(score);
    }

    @FXML
    public void onExitButtonClicked() {
        Platform.exit();
    }

    @Override
    public void onRetryButtonClicked() {
        endGameStage.hide();
        super.onRetryButtonClicked();
    }

    @Override
    public void onNewGameButtonClicked() {
        endGameStage.hide();
        super.onNewGameButtonClicked();
    }
}
