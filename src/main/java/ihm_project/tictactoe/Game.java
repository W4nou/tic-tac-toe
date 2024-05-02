package ihm_project.tictactoe;

public class Game {
    private static final int SIZE_DEFAULT = 3;

    private final Player p1;
    private final Player p2;
    private Player[][] board;
    private final int size;
    private boolean isP1Turn;

    public Game(Player p1, Player p2, int size) {
        this.p1 = p1;
        this.p2 = p2;
        this.size = size;
        board = new Player[this.size][this.size];
        isP1Turn = true;
    }

    public Game(Player p1, Player p2) {
        this(p1, p2, SIZE_DEFAULT);
    }

    public Player winner() {
        for (int column = 0; column < size; column++) {
            Player winner = columnLine(column);
            if (winner != null) {
                return winner;
            }
        }

        for (int row = 0; row < size; row++) {
            Player winner = rowLine(row);
            if (winner != null) {
                return winner;
            }
        }

        return diagonalLine();
    }

    private Player rowLine(int row) {
        Player previousCell = board[0][row];
        Player actualCell;

        for (int column = 1; column < size; column++) {
            actualCell = board[column][row];

            if (actualCell == null || actualCell != previousCell) {
                return null;
            }
        }
        return previousCell;
    }

    private Player columnLine(int column) {
        Player previousCell = board[column][0];
        Player actualCell;

        for (int row = 1; row < size; row++) {
            actualCell = board[column][row];

            if (actualCell == null || actualCell != previousCell) {
                return null;
            }
        }
        return previousCell;
    }

    private Player diagonalLine() {
        Player previousCell = board[0][0];
        Player actualCell = null;

        for (int diag = 1; diag < size; diag++) {
            actualCell = board[diag][diag];

            if (actualCell == null || actualCell != previousCell) {
                break;
            }
        }
        if (actualCell == previousCell && actualCell != null) {
            return actualCell;
        }

        previousCell = board[size - 1][size - 1];
        for (int diag = size - 1; diag >= 0; diag--) {
            actualCell = board[diag][diag];

            if (actualCell == null || actualCell != previousCell) {
                return null;
            }
        }
        return previousCell;
    }

    public boolean canPlay(int row, int column) {
        return board[column][row] == null;
    }

    public boolean played(int row, int column) {
        Player p = isP1Turn ? p1 : p2;
        if (canPlay(row, column)) {
            board[column][row] = p;
            return true;
        }
        return false;
    }
}
