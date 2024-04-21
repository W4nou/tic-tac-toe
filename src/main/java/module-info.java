module ihm_projet.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens ihm_project.tictactoe to javafx.fxml;
    exports ihm_project.tictactoe;
}