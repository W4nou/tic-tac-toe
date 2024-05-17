package ihm_project.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Creation of the nouvellePartie window

        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApplication.class.getResource("nouvellePartie.fxml"));
        Scene nouvellePartie = new Scene(fxmlLoader.load());
        stage.setTitle("TicTacToe : Nouvelle Partie");
        stage.setScene(nouvellePartie);
        NouvellePartieController nouvellePartieController = fxmlLoader.getController();

        TicTacToeController.nouvellePartie = (Stage) nouvellePartie.getWindow();


//        Creation of the configurationPartie window

        Stage configurationPartie = new Stage();
        configurationPartie.initOwner(stage);
        configurationPartie.setTitle("TicTacToe : Configuration Partie");
        FXMLLoader fxmlConfigurationLoader = new FXMLLoader(TicTacToeApplication.class.getResource("configurationPartie.fxml"));
        Scene configurationPartieScene = new Scene(fxmlConfigurationLoader.load());
        configurationPartie.setScene(configurationPartieScene);

        ConfigurationController configurationController = fxmlConfigurationLoader.getController();
        nouvellePartieController.setConfigurationController(configurationController);

        nouvellePartieController.configurationPartieScene = configurationPartie;


//        Creation of the règles window

        Stage regles = new Stage(StageStyle.UTILITY);
        regles.initOwner(stage);
        regles.setTitle("TicTacToe : règles");
        FXMLLoader fxmlRegleLoader = new FXMLLoader(TicTacToeApplication.class.getResource("regles.fxml"));
        Scene reglesScene = new Scene(fxmlRegleLoader.load());
        regles.setScene(reglesScene);

        TicTacToeController.reglesScene = regles;


//        Creation of the à Propos window

        Stage aPropos = new Stage();
        aPropos.initOwner(stage);
        aPropos.setTitle("TicTacToe : À propos");
        FXMLLoader fxmlAProposLoader = new FXMLLoader(TicTacToeApplication.class.getResource("aPropos.fxml"));
        Scene aProposScene = new Scene(fxmlAProposLoader.load());
        aPropos.setScene(aProposScene);

        TicTacToeController.aProposScene = aPropos;


//        Creation of the game window

        Stage game = new Stage();
        game.initOwner(stage);
        game.setTitle("TicTacToe : Partie");
        FXMLLoader fxmlGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("game.fxml"));
        Scene gameScene = new Scene(fxmlGameLoader.load());
        game.setScene(gameScene);

        nouvellePartieController.gameScene = game;

        GameController gameController = fxmlGameLoader.getController();
        nouvellePartieController.setGameController(gameController);

        TicTacToeController.gameController = gameController;
        TicTacToeController.game = game;

//        Creation of the endGame window

        Stage endGame = new Stage();
        endGame.initOwner(stage);
        endGame.setTitle("TicTacToe : Fin de partie");
        FXMLLoader fxmlEndGameLoader = new FXMLLoader(TicTacToeApplication.class.getResource("endGame.fxml"));
        Scene endGameScene = new Scene(fxmlEndGameLoader.load());
        endGame.setScene(endGameScene);

        EndGameController endGameController = fxmlEndGameLoader.getController();
        gameController.setEndGameController(endGameController);
        endGameController.setEndGameStage(endGame);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}