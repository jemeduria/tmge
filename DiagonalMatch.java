import java.util.ArrayList;
import java.util.List;

public class DiagonalMatch implements Matchable {
	// CONSTRUCTOR
    public DiagonalMatch() {}

	// INTERFACE IMPLEMENTATION
	public List<Tile> match(List<List<Tile>> gameBoard) {
		int rows = gameBoard.size();
		int columns = gameBoard.get(0).size();
		List<Tile> matched = new ArrayList<>();

		// Check matches along diagonals (top-left to bottom-right)
		for (int i = 0; i < rows + columns - 1; i++) {
			// new matched list for each diagonal
			matched.clear();

			int startRow = Math.max(0, i - columns + 1);
			int startCol = Math.min(columns - 1, i);

			for (int row = startRow, col = startCol; row < rows && col >= 0; row++, col--) {
				Tile currentTile = gameBoard.get(row).get(col);
				String currentDisplay = currentTile.getDisplay();

				if (currentDisplay != null) {
					if (matched.isEmpty() || currentDisplay.equals(matched.get(0).getDisplay())) {
						// is the first non-empty Tile found OR matches tiles
						matched.add(currentTile);
					} else {
						// reset matched
						matched.clear();
						matched.add(currentTile);
					}
				} else {
					// display null? reset matched
					matched.clear();
				}

				if (matched.size() >= 4) {
					// found a match of 4 or more tiles
					return matched;
				}
			}
		}

		// Check matches along diagonals (top-right to bottom-left)
		for (int i = 0; i < rows + columns - 1; i++) {
			// new matched list for each diagonal
			matched.clear();

			int startRow = Math.max(0, i - columns + 1);
			int startCol = Math.min(columns - 1, i);

			for (int row = startRow, col = columns - 1 - startCol; row < rows && col < columns; row++, col++) {
				Tile currentTile = gameBoard.get(row).get(col);
				String currentDisplay = currentTile.getDisplay();

				if (currentDisplay != null) {
					if (matched.isEmpty() || currentDisplay.equals(matched.get(0).getDisplay())) {
						// is the first non-empty Tile found OR matches tiles
						matched.add(currentTile);
					} else {
						// reset matched
						matched.clear();
						matched.add(currentTile);
					}
				} else {
					// display null? reset matched
					matched.clear();
				}

				if (matched.size() >= 4) {
					// found a match of 4 or more tiles
					return matched;
				}
			}
		}

		// no matches at all
		return null;
	}

}
