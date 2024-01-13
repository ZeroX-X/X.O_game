package TicTacToe;


public class start_the_game{
    public static void main(String[] args) {
        // Create an instance of TicTacToeGame
        TicTacToeGame ticTacToeGame = new TicTacToeGame();

        // Instantiate two VIPPlayer objects (players 1 and 2) with symbols 'X' and 'O'
        VIPPlayer player1 = new VIPPlayer("ZeroX", 'X', ticTacToeGame);
        VIPPlayer player2 = new VIPPlayer("RasethO", 'O', ticTacToeGame);

        // Call the registration method for player1
        player1.register("player1", "password1");

        // Call the registration method for player2
        player2.register("player2", "password2");

        // Call the login method for player1
        player1.login("player1", "password1");

        // Call the login method for player2
        player2.login("player2", "password2");

        // Start the Tic Tac Toe game with the two players
        TicTacToeGameManager.startTicTacToeGame(player1, player2);
        }
    }