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

                if (currentDisplay != null) {
                    // if not null: check for matches

                    if ((matched.isEmpty()) || (currentDisplay.equals(matched.get(0).getDisplay()))) {
                        // is the first non-empty Tile found? add
                        // is a match? add
                        matched.add(currentTile);

                    } else if (matched.size() <= 3) {
                        // not a match AND there are less than 4 Tiles in the matched? reset
                        matched.clear();
                        matched.add(currentTile);
                    }

                } else {
                    // if null: ignore the tile and/or clear the matches

                    if ((!matched.isEmpty()) && (matched.size() <= 3)) {
                        // there is a null tile between non-null tiles? clear
                        matched.clear();
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
