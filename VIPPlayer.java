package TicTacToe;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class VIPPlayer extends Player {
    private String name;
    private String username;
    private String password;
    private int wins;
    private int losses;
    private TicTacToeGame game;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor to initialize VIPPlayer with a name, symbol, and reference to the game
    public VIPPlayer(String name, char symbol, TicTacToeGame game) {
        super(symbol);
        this.name = name;
        this.game = game;
        this.username = "";
        this.password = "";
        this.wins = 0;
        this.losses = 0;
    }

    // Override method to handle the player's turn
    @Override
    public void playerTurn() {
        System.out.println(name + "'s Turn please ");
        int move = getPlayerInput();
        game.makeMove(move);
    }

    // Get the player's input for the move
    private int getPlayerInput() {
        do {
            try {
                System.out.print("Enter your move (1-9): ");
                int move = Integer.parseInt(scanner.nextLine()) - 1;

                if (game.isValidMove(move)) {
                    return move;
                } else {
                    System.out.println("Invalid move. Please choose an empty cell.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        } while (true);
    }

    // Override method to provide a string representation of the VIPPlayer
    @Override
    public String toString() {
        return name + " (Symbol: " + getSymbol() + ")";
    }

    // Getter for the player's name
    @Override
    public String getName() {
        return name;
    }

    // Authentication System

    // Method for user registration
    public void register(String username, String password) {
        this.username = username;
        this.password = password;
        saveUserInfoToFile();
    }

    // Method for user login
    public void login(String inputUsername, String inputPassword) {
        if (checkCredentials(inputUsername, inputPassword)) {
            System.out.println("Login successful!");
            loadUserInfoFromFile();
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }

    // Method to check credentials during login
    private boolean checkCredentials(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    // Method to save user information to a file
    private void saveUserInfoToFile() {
        try {
            Path path = Path.of("player_info.txt");
            List<String> userInfo = List.of(
                    "username=" + username,
                    "password=" + password,
                    "wins=" + wins,
                    "losses=" + losses
            );

            Files.write(path, userInfo);
        } catch (IOException e) {
            System.out.println("Error saving user information to file.");
        }
    }

    // Method to load user information from a file
    private void loadUserInfoFromFile() {
        try {
            List<String> lines = Files.readAllLines(Path.of("player_info.txt"));

            for (String line : lines) {
                String[] keyValue = line.split("=");

                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    // Update player information based on the key-value pairs
                    switch (key) {
                        case "username":
                            username = value;
                            break;
                        case "password":
                            password = value;
                            break;
                        case "wins":
                            wins = Integer.parseInt(value);
                            break;
                        case "losses":
                            losses = Integer.parseInt(value);
                            break;
                        // Add more cases for additional information if needed
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user information from file.");
        }
    }
}
