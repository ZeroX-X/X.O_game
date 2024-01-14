# Tic Tac Toe Game

## Introduction

This is a simple console-based Tic Tac Toe game implemented in Java. The game supports two players who can either register or log in to play. Player information is stored in a text file, and the game history is recorded after each match.

## 1. Overview
This project is a implementation of the Tic Tac Toe game. It demonstrates various object-oriented programming concepts and features, including classes, inheritance, polymorphism, encapsulation, abstraction, exception handling, file I/O, anonymous inner classes, functional interfaces, lambda expressions, and static methods.
## 2. Classes & Objects
There are 7 classes in this project:

- **AuthManager**:  It's responsible for managing user authentication for my user or we called VIPPlayer in my Tic Tac Toe project.
- **Game**: I create this class for structuring my project like startgame(), playerTurn(), endGame().
- **TicTacToeGame**: It's an important class where the core logic of the Tic Tac Toe game, including managing the game board, player moves, and determining the game outcome.
- **TicTacToeGameManager**:  This one is the coordinator for the Tic Tac Toe game, arrange the game's start, turns, and end.
- **Player**: This is our superclass which is a blueprint for creating specialized VIPPlayer.
- **VIPPlayer**: It's a subclass extend from Player. This class represent a player with enhanced features, particularly an integrated authentication system.
- **start_the_game**: It's our main class where we can start the game and play.

## 3. Inheritance
In our project we have **2 superclasses** and **2 subclasses**:
- Game: It's a structure of this game which we should 3 abstract or we can call it as template. And in our specialized **TicTacToeGame**, The important of this class is the game play from the startgame to endGame. In Summarize, we use inheritance for the better structure of the code.
- Player: As Player class is a really general player. So we create a specialized class **VIPPlayer** and this class can have the feature like register and login with username and password. But it need to follow the rule or the  blueprint from Player superclass.
### 3.1 Override:
- We override this method because it's allows us to handle the VIP player's turn in a way that is specific to VIP players, which involve additional logic and behavior compared to the generic player. 
```java
 @Override
    public void playerTurn() {
        // Specific implementation for VIPPlayer's turn
        System.out.println(name + "'s Turn please ");
        int move = getPlayerInput();
        game.makeMove(move);
    }
```
### 3.2 Overloading:
- In my project i create two board one is a normal board and one is invisible board. We use overloading to create the same method name printBoard() with different parameter and different result. Here is the code below:
```java
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
      // Overloaded method to print a invisible version of the board
      private void printBoard(boolean invisible) {
        if (invisible) {
            System.out.println("invisble Board:");
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
```


## 4. Polymorphism
 - We do use **toString, equal method** in our project now take a look at our code below and we will explain why we use it:
 ```java
@Override
public String toString() {
    return name + " (Symbol: " + getSymbol() + ")";
}


@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    VIPPlayer otherPlayer = (VIPPlayer) obj;
    return Objects.equals(name, otherPlayer.name) &&
           Objects.equals(username, otherPlayer.username);
}

 ```
 The purpose:
 - __toString__: Provides a string representation of a VIPPlayer instance. When i print a VIPPlayer object, this method is called to create the string that represents the player's name and symbol.

- __equals__: Determines if two VIPPlayer instances are considered equal. In this case, equality is based on both the name and username being the same. This method is used to compare VIPPlayer objects for equality.

## 5. Encapsulation

- **public:** we use this public because it can be access in all classes in myproject 

```java
    public String getName() {
        return name;
    }
```
- **private:** Private Fields: name, username, password, wins, and losses in the VIPPlayer class are private. I want to restricted the access to these fields, and they can only be accessed or modified through the methods provided by the class for the better manipulation of my code.

```java
public class VIPPlayer extends Player {
    private String name;
    private String username;
    private String password;
    private int wins;
    private int losses;
    private TicTacToeGame game;
```

- **protected:** it can access only in subclass and in the same package. In my case, its allows subclasses (like VIPPlayer, which extends Player) to access and override this method.

```java
//Player.java
protected String getName() {
    return "Player"; 
}

```
## 6. Abstraction

- **Abstract Class (Game):** We use Game class as an **Abstract class** because we want this class to focus on essential features like {startgame(), endGame()} the overall structure and behavior of the GamePlay.
```java
public abstract class Game
```

- **Abstract Method:** Abstract methods are used to define a common interface without providing an implementation. In my case, startGame, playTurn, and endGame represent essential steps or actions in a game, but the exact implementation details are left to concrete subclass **(TicTacToeGame.java)**.
```java
    abstract void startGame();
    abstract void playTurn(Player player);
    abstract void endGame();
```
## 7. Exception Handling

```java
private void getPlayerInput() {
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

```
- **Exception handling** is a fundamental practice that contributes to the stability, reliability, and maintainability of software. It helps deal with unexpected situations in a controlled and organized manner.
- As Try and catch is a keyword in **Exception handling**. We are using **try** to attempt to parse the user's input as an integer. If the input cannot be parsed (Example: if the user enters something that is not a number), a NumberFormatException is thrown. The **catch** block then handles this exception by printing a message and allowing the user to try again.

## 8. File I/O

- We use file I/O to process the input and produce the output of our **player_info.txt**.

```java
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

```
## 9. Functional Interface and Lambda Expression

-  Functional interface is an interface that contains only one abstract method.
- So we use **MoveValidator** is designed to be a functional interface with a single method isValidMove that takes an int move as a parameter and returns a boolean.

```java
// MoveValidator interface
interface MoveValidator {
    boolean isValidMove(int move);
}

```
- A **lambda expression** is a concise way to represent an instance of a functional interface.
```java
// Lambda expression to implement the isValidMove method of MoveValidator
MoveValidator moveValidator = (move) -> move >= 0 && move < 9 && board[move / 3][move % 3] == ' ';

```
- (move) is the parameter list, and the expression after -> is the body of the isValidMove method.
It checks if the move is within valid bounds and corresponds to an empty cell on the Tic Tac Toe board.

## 10. Static Method

- In my case, we're using static method because it provides a centralized and organized way to start a game with my two players.
```java
public static void startTicTacToeGame(VIPPlayer player1, VIPPlayer player2) {
```







