package ihm_project.tictactoe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.Objects;

public class GameController extends TicTacToeController {
    @FXML
    private AnchorPane boardAnchorPane;

    @FXML
    private Label gameStatusLabel;

    @FXML
    private EndGameController endGameController;

    private Game game;

    public void setGame(Game game) {
        this.game = game;
        gameStatusLabel.setText("A " + game.whoseTurn().getName() + " de jouer\n");

        GridPane boardGridPane = new GridPane();

        for (int i = 0; i < game.getSize(); i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            boardGridPane.getColumnConstraints().add(columnConstraints);

            RowConstraints rowConstraints = new RowConstraints();
            boardGridPane.getRowConstraints().add(rowConstraints);
        }

        for (int row = 0; row < boardGridPane.getRowCount(); row++) {
            for (int column = 0; column < boardGridPane.getColumnCount(); column++) {
                Button cell = new Button();
                final int finalRow = row;
                final int finalColumn = column;
                cell.setOnMouseClicked(event -> played(finalRow, finalColumn, event));

                Pane pane = new Pane();
                pane.getChildren().add(cell);

                GridPane.setHgrow(pane, Priority.ALWAYS);
                GridPane.setVgrow(pane, Priority.ALWAYS);

                pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


                // necessary in order to use the current layout to calculate the size of the needed button
                Platform.runLater(() -> {
                    cell.setPrefWidth(pane.getWidth());
                    cell.setPrefHeight(pane.getHeight());
                });

                boardGridPane.add(pane,row,column);
            }
        }

        boardGridPane.setStyle("-fx-grid-lines-visible: true");

        AnchorPane.setTopAnchor(boardGridPane, 0.0);
        AnchorPane.setBottomAnchor(boardGridPane, 0.0);
        AnchorPane.setLeftAnchor(boardGridPane, 0.0);
        AnchorPane.setRightAnchor(boardGridPane, 0.0);

        boardAnchorPane.getChildren().add(boardGridPane);

        // load css file to override the default button styles
        String css = Objects.requireNonNull(this.getClass().getResource("/ihm_project/tictactoe/styles/GameBoardButtonStyles.css")).toExternalForm();
        boardAnchorPane.getScene().getStylesheets().add(css);
    }

    private void played(int row, int column, MouseEvent event) {
        Button clickedButton = (Button) event.getSource();

        Player resultPlayer = game.played(row, column);

        gameStatusLabel.setText("A " + game.whoseTurn().getName() + " de jouer\n");
        updateButtonGraphic(resultPlayer, clickedButton);

        Player winner = game.winner();
        boolean boardFull = game.isBoardFull();
        String winnerName = (winner == null) ? null : winner.getName();

        if (winner != null || boardFull) {
            gameStatusLabel.setText("La partie est termin\u00E9\n");

            GridPane boardGridPane = (GridPane) boardAnchorPane.getChildren().getFirst();
            changeAllButtons(false,boardGridPane);

            endGameController.displayWinner(winnerName);
        }
    }

    private void changeAllButtons(boolean activation,Pane root) {
        for (Node node : root.getChildrenUnmodifiable()) {
            if (node instanceof Button button) {
                button.setDisable(!activation);
                if (button.getGraphic() instanceof ImageView && activation) {
                    button.setGraphic(null);
                }
            } else if (node instanceof Pane pane) {
                changeAllButtons(activation,pane);
            }
        }
    }

    private void updateButtonGraphic(Player player, Button clickedButton) {
        ImageView imageView = new ImageView();
        imageView.setImage(player.getShape());
        imageView.setEffect(player.getColor());

        // -6 is an arbitrary value inferior to two times the padding,
        // it seems to fix an issue with the columns and rows adjusting when icons are added
        imageView.setFitHeight(clickedButton.getPrefHeight()-6);
        imageView.setFitWidth(clickedButton.getPrefWidth()-6);

        clickedButton.setGraphic(imageView);
        clickedButton.setDisable(true);
    }

    public void resetNewGame() {
        GridPane boardGridPane = (GridPane) boardAnchorPane.getChildren().getFirst();
        boardGridPane.getChildren().clear();
        boardAnchorPane.getChildren().remove(boardGridPane);
    }

    public void setEndGameController(EndGameController endGameController){
        this.endGameController = endGameController;
    }

    public void restartGame() {
        gameStatusLabel.setText("A " + game.whoseTurn().getName() + " de jouer\n");
        GridPane boardGridPane = (GridPane) boardAnchorPane.getChildren().getFirst();
        changeAllButtons(true,boardGridPane);
        game.restart();
    }
}
