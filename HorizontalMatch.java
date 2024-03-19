import java.util.ArrayList;
import java.util.List;

public class HorizontalMatch implements Matchable {
    public HorizontalMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
        int rows = gameBoard.size();
        int columns = gameBoard.get(0).size();
        List<Tile> matched = new ArrayList<>();

        // for each row
        for (int row = 0; row < rows; row++) {
            // check matched from the above row
            if (row > 0) {
                if (matched.size() >= 4) {
                    // there was a match (of 4+) in the above row
                    return matched; // SUCCESS
                }
                // clear all non-4+ matches
                matched.clear();
            }

            // for each Tile in the row
            for (int col = 0; col < columns; col++) {

                // get display of the Tile
                Tile currentTile = gameBoard.get(row).get(col);
                String currentDisplay = currentTile.getDisplay();

                // if not null, check for matches
                if (currentDisplay != null) {
                    if (matched.isEmpty()) {
                        // is the first non-empty Tile found in row
                        // just add to matched to find possible matches
                        matched.add(currentTile);

                    } else if (currentDisplay.equals(matched.get(0).getDisplay())) {
                        // matched has at least one Tile in it
                        // add if the displays are the same
                        matched.add(currentTile);

                    } else if (matched.size() <= 3) {
                        // currentDisplay does not have the same displays in matched
                        // AND there is less than 4 Tiles in the matched
                        // THEREFORE reset matched to find another match
                        matched.clear();
                        matched.add(currentTile);
                    }
                }
            }
        }

        if (matched.size() >= 4) {
            // there was a match (of 4+) in the bottom row
            return matched; // SUCCESS
        }
        return null;
    }
}
