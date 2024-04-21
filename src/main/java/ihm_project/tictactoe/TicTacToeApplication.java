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
//        Création de la fenêtre nouvelle partie

        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeApplication.class.getResource("nouvellePartie.fxml"));
        Scene nouvellePartie = new Scene(fxmlLoader.load());
        stage.setTitle("TicTacToe : Nouvelle Partie");
        stage.setScene(nouvellePartie);
        NouvellePartieController nouvellePartieController = fxmlLoader.getController();


//        Création de la fenêtre de configuration de la partie

        Stage configurationPartie = new Stage();
        configurationPartie.initOwner(stage);
        configurationPartie.setTitle("TicTacToe : Configuration Partie");
        FXMLLoader fxmlConfigurationLoader = new FXMLLoader(TicTacToeApplication.class.getResource("configurationPartie.fxml"));
        Scene configurationPartieScene = new Scene(fxmlConfigurationLoader.load());
        configurationPartie.setScene(configurationPartieScene);

        ConfigurationController configurationController = fxmlConfigurationLoader.getController();
        nouvellePartieController.setConfigurationController(configurationController);

        nouvellePartieController.configurationPartieScene = configurationPartie;


//        Création de la fenêtre à Règles

        Stage regles = new Stage(StageStyle.UTILITY);
        regles.initOwner(stage);
        regles.setTitle("TicTacToe : règles");
        FXMLLoader fxmlRegleLoader = new FXMLLoader(TicTacToeApplication.class.getResource("regles.fxml"));
        Scene reglesScene = new Scene(fxmlRegleLoader.load());
        regles.setScene(reglesScene);

        nouvellePartieController.reglesScene = regles;


//        Création de la fenêtre à Propos

        Stage aPropos = new Stage();
        aPropos.initOwner(stage);
        aPropos.setTitle("TicTacToe : À propos");
        FXMLLoader fxmlAProposLoader = new FXMLLoader(TicTacToeApplication.class.getResource("aPropos.fxml"));
        Scene aProposScene = new Scene(fxmlAProposLoader.load());
        aPropos.setScene(aProposScene);

        nouvellePartieController.aProposScene = aPropos;



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}