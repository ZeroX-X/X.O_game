package TicTacToe;

public class TicTacToeGameManager {

    // Method to start the Tic Tac Toe game with two players
    public static void startTicTacToeGame(VIPPlayer player1, VIPPlayer player2) {
        // Create an instance of TicTacToeGame using the Game interface
        Game game = new TicTacToeGame();

        // Start the Tic Tac Toe game
        game.startGame();

        // Game loop with alternating turns until the game is over
        while (!game.isGameOver()) {
            // Determine the current player for the turn
            Player currentPlayer = getCurrentPlayer(player1, player2);

            // Display a message indicating the current player's turn
            System.out.println(((Player) currentPlayer).getName() + ", it's your turn!");


            // Allow the current player to take their turn in the game
            game.playTurn(currentPlayer);
        }

        // End the Tic Tac Toe game and display the result
        game.endGame();
    }

    // Helper method to alternate between players for their turns
    private static Player getCurrentPlayer(Player player1, Player player2) {
        return (Math.random() < 0.5) ? player1 : player2;
    }
}
