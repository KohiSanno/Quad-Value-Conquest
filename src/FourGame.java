/**
 *
 * @author cocok
 */
public class FourGame {

    private int[][] board;
    private int currentPlayer;
    private int scoreRed;
    private int scoreBlue;
    private int boardSize;


    public FourGame(int size) {
        this.boardSize = size;
        this.board = new int[size][size];
        this.currentPlayer = 1; // 1 for Red, 2 for Blue
        this.scoreRed = 0;
        this.scoreBlue = 0;
        this.owner = new int[size][size];
    }

    public void makeMove(int row, int col) {
        if (row < 0 || col < 0 || row >= boardSize || col >= boardSize) {
            return;
        }


        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < boardSize && j >= 0 && j < boardSize && board[i][j] < 4) {
                    board[i][j]++;
                    if (board[i][j] == 4) {
                        if (currentPlayer == 1) scoreRed++;
                        else scoreBlue++;
                    }
                }
            }
        }


        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }


    public boolean isGameOver() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getFieldValue(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
            return board[row][col];
        } else {
            return -1;
        }
    }


    public void reset() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;

            }
        }
        currentPlayer = 1; // Reset to first player
        scoreRed = 0;
        scoreBlue = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Method to get the winner
    public String getWinner() {
        if (scoreRed > scoreBlue) return "Red Wins!";
        else if (scoreBlue > scoreRed) return "Blue Wins!";
        else return "It's a Draw, No one wins!";
    }

    private int[][] owner;

    private void updateOwner(int row, int col) {
        if (board[row][col] == 3 && owner[row][col] == 0) {
            owner[row][col] = currentPlayer;
        }
    }
    public int getFieldOwner(int row, int col) {
        return owner[row][col]; // This will return 0 if no player owns it yet
    }
    // Main method for testing
    public static void main(String[] args) {
        FourGame game = new FourGame(3); // Example 5x5 board

        // Example moves
        game.makeMove(0, 0);
        game.makeMove(1, 1);

        // Continue game...

        if (game.isGameOver()) {
            System.out.println(game.getWinner());
        }
    }
}
