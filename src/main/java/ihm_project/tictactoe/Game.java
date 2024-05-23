package ihm_project.tictactoe;

import java.util.Random;
import java.util.function.IntFunction;

public class Game {
    public static final int BOARD_SIZE_DEFAULT = 3;
    private final Player p1;
    private final Player p2;
    private Player[][] board;
    private final int size;
    private boolean isP1Turn;

    public Game(Player p1, Player p2, int size, boolean isFirstPlayerRandom) {
        this.p1 = p1;
        this.p2 = p2;
        this.size = size;
        board = new Player[this.size][this.size];

        if (isFirstPlayerRandom) {
            isP1Turn = new Random().nextBoolean();
        } else {
            isP1Turn = true;
        }
    }

    public Player winner() {
        for (int column = 0; column < size; column++) {
            Player winner = checkColumnLine(column);
            if (winner != null) {
                return winner;
            }
        }

        for (int row = 0; row < size; row++) {
            Player winner = checkRowLine(row);
            if (winner != null) {
                return winner;
            }
        }

        return checkDiagonalLine();
    }

    private Player checkLine(int end, IntFunction<Player> step) {
        Player previousCell = step.apply(0);
        Player actualCell;

        for (int i = 1; i <= end; i++) {
            actualCell = step.apply(i);

            if (actualCell == null || actualCell != previousCell) {
                return null;
            }
        }
        return previousCell;
    }

    private Player checkRowLine(int row) {
        return checkLine(size - 1, column -> board[column][row]);
    }

    private Player checkColumnLine(int column) {
        return checkLine(size - 1, row -> board[column][row]);
    }

    private Player checkDiagonalLine() {
        Player winner = checkLine(size - 1, diag -> board[diag][diag]);
        if (winner != null) {
            return winner;
        }

        return checkLine(size - 1, diag -> board[diag][size - 1 - diag]);
    }

    public boolean canPlay(int row, int column) {
        return board[column][row] == null;
    }

    public Player played(int row, int column) {
        if (!canPlay(row, column)) {
            return null;
        }
        Player player = whoseTurn();
        board[column][row] = player;
        isP1Turn = !isP1Turn;
        return player;
    }

    public Player whoseTurn() {
        return isP1Turn ? p1 : p2;
    }

    public int getSize() {
        return size;
    }

    public boolean isBoardFull() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (board[row][column] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void restart() {
        board = new Player[this.size][this.size];
    }
}
