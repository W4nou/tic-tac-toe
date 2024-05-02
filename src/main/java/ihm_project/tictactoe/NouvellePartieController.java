package ihm_project.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NouvellePartieController extends TicTacToeController {

    private ConfigurationController configurationController;
    private GameController gameController;

    @FXML
    private MenuItem aProposMenuButton;

    @FXML
    private Button configPartieButton;

    @FXML
    private TextField p1TextField;

    @FXML
    private TextField p2TextField;

    @FXML
    private Button nouvellePartieButton;

    @FXML
    private MenuItem nouvellePartieMenuButton;

    @FXML
    private CheckBox ordinateurCheckBox;

    @FXML
    private MenuItem recommencerMenuButton;

    @FXML
    private MenuItem reglesMenuButton;

    @FXML
    public Stage configurationPartieScene;

    @FXML
    public Stage gameScene;

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
        configurationController.reloadPseudo(p1TextField.getText(),p2TextField.getText());
        configurationPartieScene.showAndWait();

        p1 = configurationController.getP1();
        p2 = configurationController.getP2();
        boardSize = configurationController.getBoardSize();
    }

    @FXML
    void onNouvellePartieButtonClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.hide();

        gameController.setGame(new Game(p1,p2,boardSize));

        gameScene.show();

    }

    public void setConfigurationController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
