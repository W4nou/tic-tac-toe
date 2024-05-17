package ihm_project.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NouvellePartieController extends TicTacToeController {

    @FXML
    private TextField p1TextField;

    @FXML
    private TextField p2TextField;

    @FXML
    private CheckBox ordinateurCheckBox;

    @FXML
    public Stage configurationPartieScene;

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
                p2TextField.setDisable(ordinateurCheckBox.isSelected()); // activation ou désactivation du TextField
                if (p2TextField.isDisable()) {
                    p2TextField.setText("Ordinateur");
                } else {
                    p2TextField.setText("");
                }
            }
        });
    }

    @FXML
    void onConfigurationPartieButtonClicked() {
        configurationController.reloadPseudo(p1TextField.getText(), p2TextField.getText());
        configurationPartieScene.show();
    }

    @FXML
    void onNouvellePartieButtonClicked(ActionEvent event) throws Exception {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.hide();

        configurationController.reloadPseudo(p1TextField.getText(), p2TextField.getText());

        p1 = configurationController.getP1();
        p2 = configurationController.getP2();

        boardSize = configurationController.getBoardSize();

        gameController.setGame(new Game(p1, p2, boardSize));

        gameScene.show();

    }

    public void setConfigurationController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
