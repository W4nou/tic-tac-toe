package ihm_project.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TicTacToeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        Creation of the 'newGame' window

        stage.setTitle("TicTacToe : Nouvelle Partie");
        FXMLLoader fxmlNewGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("newGame.fxml"));

        Scene newGame = new Scene(fxmlNewGameLoader.load());
        stage.setScene(newGame);


//        Creation of the 'configurationGame' window

        Stage configurationGameStage = new Stage();
        configurationGameStage.initOwner(stage);
        configurationGameStage.initModality(Modality.APPLICATION_MODAL);

        configurationGameStage.setTitle("TicTacToe : Configuration Partie");
        FXMLLoader fxmlConfigurationLoader = new FXMLLoader(TicTacToeApplication.class.getResource("configurationGame.fxml"));

        Scene configurationGameScene = new Scene(fxmlConfigurationLoader.load());
        configurationGameStage.setScene(configurationGameScene);


//        Creation of the 'rules' window

        Stage rulesStage = new Stage(StageStyle.UTILITY);
        rulesStage.initOwner(stage);

        rulesStage.setTitle("TicTacToe : R\u00E8gles");
        FXMLLoader fxmlRulesLoader = new FXMLLoader(TicTacToeApplication.class.getResource("rules.fxml"));

        Scene rulesScene = new Scene(fxmlRulesLoader.load());
        rulesStage.setScene(rulesScene);


//        Creation of the 'aboutStage' window

        Stage aboutStage = new Stage(StageStyle.UTILITY);
        aboutStage.initOwner(stage);

        aboutStage.setTitle("TicTacToe : \u00C0 propos");
        FXMLLoader fxmlAboutLoader = new FXMLLoader(TicTacToeApplication.class.getResource("about.fxml"));

        Scene aboutScene = new Scene(fxmlAboutLoader.load());
        aboutStage.setScene(aboutScene);


//        Creation of the 'game' window

        Stage gameStage = new Stage();
        gameStage.initOwner(stage);

        gameStage.setTitle("TicTacToe : Partie");
        FXMLLoader fxmlGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("game.fxml"));

        Scene gameScene = new Scene(fxmlGameLoader.load());
        gameStage.setScene(gameScene);


//        Creation of the 'endGame' window

        Stage endGameStage = new Stage(StageStyle.UTILITY);
        endGameStage.initOwner(stage);
        endGameStage.initModality(Modality.APPLICATION_MODAL);

        endGameStage.setTitle("TicTacToe : Fin de partie");
        FXMLLoader fxmlEndGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("endGame.fxml"));

        Scene endGameScene = new Scene(fxmlEndGameLoader.load());
        endGameStage.setScene(endGameScene);

//          Passing every needed controller and Stage needed through the TicTacToeController

        EndGameController endGameController = fxmlEndGameLoader.getController();

        TicTacToeController.setConfigurationController(fxmlConfigurationLoader.getController());
        TicTacToeController.setConfigurationGameStage(configurationGameStage);

        TicTacToeController.setGameController(fxmlGameLoader.getController());
        TicTacToeController.setGameStage(gameStage);

        TicTacToeController.setAboutStage(aboutStage);

        TicTacToeController.setRulesStage(rulesStage);

        TicTacToeController.setNewGameStage((Stage) newGame.getWindow());
        TicTacToeController.setNewGameController(fxmlNewGameLoader.getController());

        TicTacToeController.setEndGameController(endGameController);
        TicTacToeController.setEndGameStage(endGameStage);


//          Close the app when the cross is clicked in the end game menu

        endGameStage.setOnCloseRequest(event -> endGameController.onExitButtonClicked());


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}