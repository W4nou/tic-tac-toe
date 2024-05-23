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

        FXMLLoader fxmlNewGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("newGame.fxml"));
        Scene newGame = new Scene(fxmlNewGameLoader.load());
        stage.setTitle("TicTacToe : Nouvelle Partie");
        stage.setScene(newGame);
        NewGameController newGameController = fxmlNewGameLoader.getController();

        TicTacToeController.newGame = (Stage) newGame.getWindow();


//        Creation of the 'configurationGame' window

        Stage configurationGame = new Stage();
        configurationGame.initOwner(stage);
        configurationGame.initModality(Modality.APPLICATION_MODAL);
        configurationGame.setTitle("TicTacToe : Configuration Partie");
        FXMLLoader fxmlConfigurationLoader = new FXMLLoader(TicTacToeApplication.class.getResource("configurationGame.fxml"));
        Scene configurationGameScene = new Scene(fxmlConfigurationLoader.load());
        configurationGame.setScene(configurationGameScene);

        ConfigurationController configurationController = fxmlConfigurationLoader.getController();
        newGameController.setConfigurationController(configurationController);

        newGameController.setConfigurationGameStage(configurationGame);


//        Creation of the 'rules' window

        Stage rules = new Stage(StageStyle.UTILITY);
        rules.initOwner(stage);
        rules.setTitle("TicTacToe : R\u00E8gles");
        FXMLLoader fxmlRulesLoader = new FXMLLoader(TicTacToeApplication.class.getResource("rules.fxml"));
        Scene rulesScene = new Scene(fxmlRulesLoader.load());
        rules.setScene(rulesScene);

        TicTacToeController.rulesScene = rules;


//        Creation of the 'about' window

        Stage about = new Stage(StageStyle.UTILITY);
        about.initOwner(stage);
        about.setTitle("TicTacToe : \u00C0 propos");
        FXMLLoader fxmlAboutLoader = new FXMLLoader(TicTacToeApplication.class.getResource("about.fxml"));
        Scene aboutScene = new Scene(fxmlAboutLoader.load());
        about.setScene(aboutScene);

        TicTacToeController.aboutScene = about;


//        Creation of the 'game' window

        Stage game = new Stage();
        game.initOwner(stage);
        game.setTitle("TicTacToe : Partie");
        FXMLLoader fxmlGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("game.fxml"));
        Scene gameScene = new Scene(fxmlGameLoader.load());
        game.setScene(gameScene);

        newGameController.setGameStage(game);

        GameController gameController = fxmlGameLoader.getController();
        newGameController.setGameController(gameController);

        TicTacToeController.gameController = gameController;
        TicTacToeController.game = game;

//        Creation of the 'endGame' window

        Stage endGame = new Stage(StageStyle.UTILITY);
        endGame.initOwner(stage);
        endGame.initModality(Modality.APPLICATION_MODAL);
        endGame.setTitle("TicTacToe : Fin de partie");
        FXMLLoader fxmlEndGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("endGame.fxml"));
        Scene endGameScene = new Scene(fxmlEndGameLoader.load());
        endGame.setScene(endGameScene);

        EndGameController endGameController = fxmlEndGameLoader.getController();
        gameController.setEndGameController(endGameController);
        endGameController.setEndGameStage(endGame);
        endGame.setOnCloseRequest(event -> endGameController.onExitButtonClicked());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}