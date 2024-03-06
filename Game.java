import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game implements Endable {

    private Board gameBoard;
    private final List<Player> players = new ArrayList<>();

    private Tile chosenTile = null;

    public Game() {}

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Tile getChosenTile() {
        return chosenTile;
    }

    public void setChosenTile(Tile chosenTile) {
        this.chosenTile = chosenTile;
    }

    public void end() {
        // do we need this? might change to just isGameOver??
    }

    public String getMove(Scanner scanner) {
        return "";
    }

    public abstract void gameLoop();

    public abstract boolean isGameOver();

    public abstract void takeTurn();

    public abstract void chooseTile();

    public abstract void checkMatch();

    public abstract boolean isMatch(List<Tile> tiles);

    public abstract void chooseTile(List<Tile> tiles);

}
