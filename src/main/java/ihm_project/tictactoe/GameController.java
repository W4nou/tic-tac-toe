package ihm_project.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GameController extends TicTacToeController{
    @FXML
    private GridPane boardGridPane;

    @FXML
    private Label gameStatusLabel;

    private Game game;

    @FXML
    void initialize() {
        for(int row = 0; row<boardGridPane.getRowCount();row++){
            for(int column = 0; column<boardGridPane.getColumnCount();column++) {
                Button cell = new Button();
                final int finalRow = row;
                final int finalColumn = column;
                cell.setOnMouseClicked(event -> played(finalRow, finalColumn, event));
                boardGridPane.add(cell, row, column);
                cell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//                cell.setOpacity(0.0);
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
        gameStatusLabel.setText("A "+game.whoseTurn().getName()+" de jouer\n");
    }

    public void played(int row, int column, MouseEvent event){
        Button clickedButton = (Button) event.getSource();

        Player resultPlayer = game.played(row,column);
        if (resultPlayer == null) {
            gameStatusLabel.setText(gameStatusLabel.getText()+"\nImpossible de jouer ici, cette case à déjà été jouée");
            return;
        }

        gameStatusLabel.setText("A "+game.whoseTurn().getName()+" de jouer\n");
        updateButtonGraphic(resultPlayer,clickedButton);

        Player winner = game.winner();
        if (winner != null){
            gameStatusLabel.setText(winner.getName()+" à gagné\n");
            // TODO end the game
        }
    }

    public void updateButtonGraphic(Player player,Button clickedButton) {
        ImageView imageView = new ImageView();
        imageView.setImage(player.getShape());
        imageView.setEffect(player.getColor());

        imageView.setFitHeight(clickedButton.getHeight()*0.8);
        imageView.setFitWidth(clickedButton.getWidth()*0.8);

        clickedButton.setGraphic(imageView);
    }
}
