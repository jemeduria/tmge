import java.util.ArrayList;
import java.util.List;

public class HorizontalMatch implements Matchable {
    public HorizontalMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
        List<Tile> matches = new ArrayList<>();
        int rows = gameBoard.size();
        if (rows == 0) return null;
        int columns = gameBoard.get(0).size();

        // Traverse each row
        for (List<Tile> row : gameBoard) {
            matches = new ArrayList<>(); // Reset matches for each row
            for (int col = 0; col < columns - 1; col++) {
                Tile currentTile = row.get(col);
                if (currentTile.getDisplay() == null) continue; // Skip null Tiles

                // Check if the current tile matches the next tile, and add it to matches
                if (col < columns - 1 && currentTile.getDisplay().equals(row.get(col + 1).getDisplay())) {
                    matches.add(currentTile);
                    // case for the last matching tile in a row
                    if (col + 1 == columns - 1) {
                        matches.add(row.get(col + 1));
                    }
                } else {
                    // If current tile does not match the next, check if we already found a matching sequence
                    if (matches.size() >= 3) { // Include the last tile of the sequence before breaking
                        matches.add(currentTile);
                        if (matches.size() >= 4) {
                            return matches; // Return the first found sequence of 4 or more
                        }
                    }
                    matches.clear(); // Reset matches if less than 4 in a sequence
                }
            }
            // Check at the end of each row if a match was found in the last sequence
            if (matches.size() >= 4) {
                return matches;
            }
        }
        return null;
    }
}
