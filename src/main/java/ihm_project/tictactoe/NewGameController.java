package ihm_project.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NewGameController extends TicTacToeController {

    private void nameTextFieldSetup(TextField textfield, String defaultValue) {
        textfield.textProperty().addListener((observable, oldValue, newValue) -> TicTacToeController.getConfigurationController().reloadPseudo(textfield.getText(), textfield));

        textfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(!newValue) && textfield.getText().isEmpty()) {
                textfield.setText(defaultValue);
            }
        });
    }

    protected void resetNewGame() {
        p1TextField.setText(Player.P1_NAME_DEFAULT);
        p2TextField.setText(Player.P2_NAME_DEFAULT);
        p2TextField.setDisable(false);

        botCheckbox.setSelected(false);
        TicTacToeController.getConfigurationController().resetConfiguration();
    }

    @FXML
    private TextField p1TextField;

    @FXML
    private TextField p2TextField;

    @FXML
    private CheckBox botCheckbox;

    @FXML
    private void initialize() {
        // bot CheckBox setup
        botCheckbox.setOnMouseClicked(selectedItemProperty -> {
            p2TextField.setDisable(botCheckbox.isSelected());
            if (p2TextField.isDisable()) {
                p2TextField.setText(Player.BOT_NAME_DEFAULT);
            } else {
                p2TextField.setText(Player.P2_NAME_DEFAULT);
            }
        });

        p1TextField.setText(Player.P1_NAME_DEFAULT);
        p2TextField.setText(Player.P2_NAME_DEFAULT);

        nameTextFieldSetup(p1TextField, Player.P1_NAME_DEFAULT);
        nameTextFieldSetup(p2TextField, Player.P2_NAME_DEFAULT);
    }

    @FXML
    private void onNewGameButtonClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.hide();

        Player p1 = TicTacToeController.getConfigurationController().getP1();
        Player p2 = TicTacToeController.getConfigurationController().getP2();

        boolean isRandomFirstPlayer = TicTacToeController.getConfigurationController().getIsRandomFirstPlayer();
        int boardSize = TicTacToeController.getConfigurationController().getBoardSize();

        p2.setBot(botCheckbox.isSelected());
        getGameController().setGame(new Game(p1, p2, boardSize, isRandomFirstPlayer));
        TicTacToeController.getGameStage().show();
    }

    @FXML
    private void onConfigurationGameButtonClicked() {
        TicTacToeController.getConfigurationGameStage().show();
    }
}