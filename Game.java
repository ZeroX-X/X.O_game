package TicTacToe;
// GAME STRUCTURE
public abstract class Game {
    abstract void startGame();
    abstract void playTurn(Player player);
    abstract void endGame();
    public boolean isGameOver() {
        return isGameOver();
    }
    
}
/*
Without the Game class, it might end up spreading the game logic across different parts of code, and it could become less organized 
and harder to maintain as project grows
*/
