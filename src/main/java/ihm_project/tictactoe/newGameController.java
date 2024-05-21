package ihm_project.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class newGameController extends TicTacToeController {

    @FXML
    private TextField p1TextField;

    @FXML
    private TextField p2TextField;

    @FXML
    private CheckBox ordinateurCheckBox;

    @FXML
    public Stage configurationGameScene;

    @FXML
    public Stage gameScene;

    private ConfigurationController configurationController;
    private GameController gameController;

    private Player p1;
    private Player p2;

    private int boardSize;

    @FXML
    void initialize() {
        ordinateurCheckBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent selectedItemProperty) {
                p2TextField.setDisable(ordinateurCheckBox.isSelected());
                if (p2TextField.isDisable()) {
                    p2TextField.setText("Ordinateur");
                } else {
                    p2TextField.setText("");
                }
            }
        });

        p1TextField.setText(Player.P1_NAME_DEFAULT);
        p2TextField.setText(Player.P2_NAME_DEFAULT);

        // handlers to synchronize TextField across both windows
        p1TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            configurationController.reloadPseudo(p1TextField.getText(), p2TextField.getText());
        });

        p2TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            configurationController.reloadPseudo(p1TextField.getText(), p2TextField.getText());
        });

        // handlers to use default value if nothing is in the textField and the textField is not selected
        p1TextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && p1TextField.getText().isEmpty()) {
                p1TextField.setText(Player.P1_NAME_DEFAULT);
            }
        });

        p2TextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && p2TextField.getText().isEmpty()) {
                p2TextField.setText(Player.P2_NAME_DEFAULT);
            }
        });
    }

    @FXML
    void onConfigurationGameButtonClicked() {
        configurationGameScene.show();
    }

    @FXML
    void onNewGameButtonClicked(ActionEvent event) throws Exception {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.hide();

        p1 = configurationController.getP1();
        p2 = configurationController.getP2();

        boolean isRandomFirstPlayer = configurationController.getIsRandomFirstPlayer();

        boardSize = configurationController.getBoardSize();

        gameController.setGame(new Game(p1, p2, boardSize, isRandomFirstPlayer));

        gameScene.show();
    }

    public void setConfigurationController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
