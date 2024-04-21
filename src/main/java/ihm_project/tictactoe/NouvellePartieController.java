package ihm_project.tictactoe;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NouvellePartieController extends TicTacToeController {

    private ConfigurationController configurationController;

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

    public void setConfigurationController(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    @FXML
    void onNouvellePartieClicked() {
        //TODO
    }
}
