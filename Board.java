import java.util.ArrayList;
import java.util.List;

public abstract class Board {
	// ATTRIBUTES
	private final List<List<Tile>> gameBoard;
	private final List<Matchable> matchTypes;
	private final List<Disappearable> disappearTypes;

	// CONSTRUCTOR
	public Board() {
		this.gameBoard = new ArrayList<>();
		this.matchTypes = new ArrayList<>();
		this.disappearTypes = new ArrayList<>();
	}

	// GETTERS
	public List<List<Tile>> getGameBoard() { return this.gameBoard; }
	public List<Matchable> getMatchTypes() { return this.matchTypes; }
	public List<Disappearable> getDisappearTypes() { return this.disappearTypes; }

	// ABSTRACT METHODS
	public abstract void displayBoard();
	public abstract void addMatchTypes();
	public abstract void addDisappearTypes();
	public abstract List<Tile> createBoardTiles();
	public abstract void createBoardGame(List<Tile> tiles);
	public abstract boolean isValidMove(String move);
	public abstract void execute(List<Tile> tiles, Player player);
	public abstract List<Tile> checkMatches();
	public abstract void removeMatchedTiles(List<Tile> matchedTiles);
	public abstract boolean isFull();


}
