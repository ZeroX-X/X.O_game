package TicTacToe;

import java.util.Scanner;

// MoveValidator interface
interface MoveValidator {
    boolean isValidMove(int move);
}

public class TicTacToeGame extends Game {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;
    private final Scanner scanner;
    private MoveValidator moveValidator = (move) -> move >= 0 && move < 9 && board[move / 3][move % 3] == ' ';

    // Constructor to initialize the TicTacToeGame
    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        scanner = new Scanner(System.in);
    }

    // Override method to start the Tic Tac Toe game
    @Override
    public void startGame() {
        clearBoard();
        printBoard();
        System.out.println("Game starts! " + currentPlayer + "'s turn.");
    }

    // Override method to handle a player's turn
    @Override
    public void playTurn(Player player) {
        // Display a welcome message for VIP players
        if (player instanceof VIPPlayer) {
            System.out.println("Welcome, " + player + ", to the World of X and O!");
        }
        int move = getPlayerInput(player); // Get the player's move
        makeMove(move); // Make the move on the board
        // Check for a win or a full board to end the game
        if (checkWin(currentPlayer) || isBoardFull()) {
            gameOver = true;
        }
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch to the next player
    }

    // Override method to end the Tic Tac Toe game
    @Override
    public void endGame() {
        printBoard();
        // Display the result of the game (win, win, or tie)
        if (checkWin('X')) {
            System.out.println("X wins!");
        } else if (checkWin('O')) {
            System.out.println("O wins!");
        } else {
            System.out.println("It's a tie!");
        }
        scanner.close(); // Close the scanner to prevent resource leak
    }

    // Method to get a valid player input for the move
    private int getPlayerInput(Player player) {
        if (player instanceof VIPPlayer) {
            System.out.print(player.toString() + ", your move please (1-9): ");
        } else {
            System.out.print("Your move please (1-9): ");
        }
        int move;
        do {
            move = getValidInput();
        } while (!isValidMove(move));
        return move;
    }

    // Method to get a valid integer input from the player
    private int getValidInput() {
        int move;
        try {
            move = Integer.parseInt(scanner.nextLine()) - 1;
            // Check if the move is within the valid range
            if (move < 0 || move >= 9) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 9.");
            return getValidInput();
        }
        return move;
    }

    // Method to make a move on the board
    void makeMove(int move) {
        if (moveValidator.isValidMove(move)) {
            board[move / 3][move % 3] = currentPlayer;
            printBoard();
        } else {
            System.out.println("Invalid move. Please choose an empty cell.");
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    // Method to check if a move is valid
    boolean isValidMove(int move) {
        return move >= 0 && move < 9 && board[move / 3][move % 3] == ' ';
    }

    // Method to check if the current player has won
    private boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Method to check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to clear the Tic Tac Toe board
    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Method to print the current state of the Tic Tac Toe board
    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Overloaded method to print a simpler version of the board
    private void printBoard(boolean invisible) {
        if (invisible) {
            System.out.println("Invisible Board:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            // Call the original printBoard method
            printBoard();
        }
    }
}
