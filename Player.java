package TicTacToe;

public abstract class Player {
    private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    // Abstract method to be implemented by concrete Player classes
    public abstract void playerTurn();

    // Concrete method providing a default implementation for getName
    protected String getName() {
        return "Player"; 
    }

    public char getSymbol() {
        return symbol;
    }
}
