import java.util.ArrayList;
import java.util.List;

public abstract class Board {
	private final List<List<Tile>> gameBoard;
	private final List<Matchable> matches;

	public Board() {
		this.gameBoard = new ArrayList<>();
		this.matches = new ArrayList<>();
	}

	public abstract void addMatches();

	public abstract boolean isValidMove(String move);

	public abstract void execute(List<Tile> tiles, Player player);

	public abstract List<Tile> createBoardTiles(List<Tile> tiles);

	public abstract void createBoardGame(List<Tile> tiles);

	public abstract void checkMatches();

	public List<List<Tile>> getGameBoard() {
		return this.gameBoard;
	}

	public List<Matchable> getMatches() {
		return this.matches;
	}
}
