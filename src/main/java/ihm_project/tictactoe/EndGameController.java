package ihm_project.tictactoe;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.Map;
import java.util.Properties;

public class EndGameController extends TicTacToeController {

    private Properties score;
    private Stage endGameStage;

    protected void setEndGameStage(Stage endGameStage) {
        this.endGameStage = endGameStage;
    }

    protected void refreshLeaderBoard() {
        ObservableList<Map.Entry<String, String>> data = FXCollections.observableArrayList();
        for (String pseudo : score.stringPropertyNames()) {
            data.add(Map.entry(pseudo, score.getProperty(pseudo)));
        }
        leaderBoardTable.setItems(data);
        leaderBoardTable.getSortOrder().add(scoreTableColumn);

        scoreTableColumn.setSortable(true);
        leaderBoardTable.sort();
        scoreTableColumn.setSortable(false);
    }

    protected void displayWinner(String name) {
        if (name == null) {
            gameResultLabel.setText("Il n'y a aucun gagnant \u00E0 cette partie");
        } else {
            incrementScore(name);
            gameResultLabel.setText(name + " \u00E0 gagn\u00E9 la partie");
        }
        endGameStage.show();
    }

    private void incrementScore(String name) {
        if (name.equals(Player.P1_NAME_DEFAULT) || name.equals(Player.P2_NAME_DEFAULT) || name.equals(Player.BOT_NAME_DEFAULT)) {
            // exclude the default names from the leaderBoard
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
        refreshLeaderBoard();

        super.saveScore(score);
    }


    @FXML
    private Label gameResultLabel;

    @FXML
    private TableView<Map.Entry<String, String>> leaderBoardTable;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> pseudoTableColumn;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> scoreTableColumn;

    @FXML
    public void initialize() {
        score = super.getScore();
        pseudoTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        scoreTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
        scoreTableColumn.setComparator(Comparator.comparingInt(Integer::parseInt));
        pseudoTableColumn.setReorderable(false);
        scoreTableColumn.setReorderable(false);

        refreshLeaderBoard();
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
