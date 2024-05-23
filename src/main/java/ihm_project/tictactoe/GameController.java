package ihm_project.tictactoe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;
import java.util.Random;

public class GameController extends TicTacToeController {

    private Game game;
    private final Random random = new Random();
    private Button[][] correspondingButtons;

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

        correspondingButtons = new Button[game.getSize()][game.getSize()];
        for (int row = 0; row < boardGridPane.getRowCount(); row++) {
            for (int column = 0; column < boardGridPane.getColumnCount(); column++) {
                Button buttonCell = new Button();
                final int finalRow = row;
                final int finalColumn = column;
                buttonCell.setOnMouseClicked(event -> played(finalRow, finalColumn));

                Pane paneCell = new Pane();
                paneCell.getChildren().add(buttonCell);

                GridPane.setHgrow(paneCell, Priority.ALWAYS);
                GridPane.setVgrow(paneCell, Priority.ALWAYS);

                paneCell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


                // necessary in order to use the current layout to calculate the size of the needed button
                Platform.runLater(() -> {
                    buttonCell.setPrefWidth(paneCell.getWidth());
                    buttonCell.setPrefHeight(paneCell.getHeight());
                });
                correspondingButtons[column][row] = buttonCell;
                boardGridPane.add(paneCell, row, column);
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

    private void played(int row, int column) {
        Button clickedButton = getCorrespondingButton(row, column);

        Player resultPlayer = game.playedCell(row, column);
        updateButtonGraphic(resultPlayer, clickedButton);
        gameStatusLabel.setText("A " + game.whoseTurn().getName() + " de jouer\n");

        Player winner = game.winner();
        boolean boardIsFull = game.isBoardFull();
        String winnerName = (winner == null) ? null : winner.getName();

        if (winner != null || boardIsFull) {
            gameStatusLabel.setText("La partie est termin\u00E9\n");

            GridPane boardGridPane = (GridPane) boardAnchorPane.getChildren().getFirst();
            changeAllButtons(false, boardGridPane);

            TicTacToeController.getEndGameController().displayWinner(winnerName);
        }

        if (game.whoseTurn().isBot()) {
            int[] botMove = botPlay();
            played(botMove[0], botMove[1]);
        }
    }

    private Button getCorrespondingButton(int row, int column) {
        return correspondingButtons[column][row];
    }

    public int[] botPlay() {
        while (true) {
            int randomRow = random.nextInt(game.getSize());
            int randomColumn = random.nextInt(game.getSize());
            if (game.isValidMove(randomRow, randomColumn)) {
                return new int[]{randomRow, randomColumn};
            }
        }
    }

    private void changeAllButtons(boolean activation, Pane root) {
        for (Node node : root.getChildrenUnmodifiable()) {
            if (node instanceof Button button) {
                button.setDisable(!activation);
                if (button.getGraphic() instanceof ImageView && activation) {
                    button.setGraphic(null);
                }
            } else if (node instanceof Pane pane) {
                changeAllButtons(activation, pane);
            }
        }
    }

    private void updateButtonGraphic(Player player, Button clickedButton) {
        ImageView imageView = new ImageView();
        imageView.setImage(player.getShape());
        imageView.setEffect(player.getColor());

        // -6 is an arbitrary value inferior to two times the padding,
        // it seems to fix an issue with the columns and rows adjusting when icons are added
        imageView.setFitHeight(clickedButton.getPrefHeight() - 6);
        imageView.setFitWidth(clickedButton.getPrefWidth() - 6);

        clickedButton.setGraphic(imageView);
        clickedButton.setDisable(true);
    }

    protected void resetGame() {
        GridPane boardGridPane = (GridPane) boardAnchorPane.getChildren().getFirst();
        boardGridPane.getChildren().clear();
        boardAnchorPane.getChildren().remove(boardGridPane);
        TicTacToeController.getNewGameController().resetNewGame();
    }

    public void restartGame() {
        gameStatusLabel.setText("A " + game.whoseTurn().getName() + " de jouer\n");
        GridPane boardGridPane = (GridPane) boardAnchorPane.getChildren().getFirst();
        changeAllButtons(true, boardGridPane);
        game.restart();
    }

    @FXML
    private AnchorPane boardAnchorPane;

    @FXML
    private Label gameStatusLabel;
}
