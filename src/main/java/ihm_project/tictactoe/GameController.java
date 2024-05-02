package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController extends TicTacToeController{
    @FXML
    private GridPane boardGridPane;

    @FXML
    private Label gameStatusLabel;

    private Game game;

    @FXML
    void initialize() {
        double boardSize = boardGridPane.getScaleX();
        double cellSize = boardSize/3;

        for(int row = 0; row<boardGridPane.getRowCount();row++){
            for(int column = 0; column<boardGridPane.getColumnCount();column++) {
                Button cell = new Button();
                final int finalRow = row;
                final int finalColumn = column;
                cell.setOnMouseClicked(onAction -> game.played(finalRow, finalColumn));
                boardGridPane.add(cell, row, column);
                cell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                cell.setOpacity(0.0);
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
