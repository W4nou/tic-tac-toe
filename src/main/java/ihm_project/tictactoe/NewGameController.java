package ihm_project.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NewGameController extends TicTacToeController {

    private ConfigurationController configurationController;
    private GameController gameController;

    protected void setConfigurationController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    protected void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    protected void setGameStage(Stage gameStage) {
        this.gameScene = gameStage;
    }

    protected void setConfigurationGameStage(Stage configurationGameStage) {
        this.configurationGameStage = configurationGameStage;
    }

    private void nameTextFieldSetup(TextField textfield, String defaultValue) {
        textfield.textProperty().addListener((observable, oldValue, newValue) -> configurationController.reloadPseudo(textfield.getText(), textfield));

        textfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(!newValue) && textfield.getText().isEmpty()) {
                textfield.setText(defaultValue);
            }
        });
    }

    @FXML
    private TextField p1TextField;

    @FXML
    private TextField p2TextField;

    @FXML
    private CheckBox botCheckbox;

    @FXML
    private Stage configurationGameStage;

    @FXML
    private Stage gameScene;

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

        Player p1 = configurationController.getP1();
        Player p2 = configurationController.getP2();

        boolean isRandomFirstPlayer = configurationController.getIsRandomFirstPlayer();
        int boardSize = configurationController.getBoardSize();

        p2.setBot(botCheckbox.isSelected());
        gameController.setGame(new Game(p1, p2, boardSize, isRandomFirstPlayer));
        gameScene.show();
    }

    @FXML
    private void onConfigurationGameButtonClicked() {
        configurationGameStage.show();
    }
}