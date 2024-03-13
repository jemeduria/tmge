import java.util.ArrayList;
import java.util.List;

public class VerticalMatch implements Matchable {
    public VerticalMatch() {}

    public List<Tile> match(List<List<Tile>> gameBoard) {

        int rows = gameBoard.size();
        int columns = gameBoard.getFirst().size();
        List<Tile> matched = new ArrayList<>();

        // for each column
        for (int col = 0; col < columns; col++) {

            // check matched from the previous column
            if (col > 0) {
                if (matched.size() >= 4) {
                    // there was a match (of 4+) in the previous column
                    return matched; // SUCCESS
                }
                // clear all non-4+ matches
                matched.clear();
            }

            // for each Tile in the column
            for (int row = 0; row < rows; row++) {

                // get display of the Tile
                Tile currentTile = gameBoard.get(row).get(col);
                String currentDisplay = currentTile.getDisplay();

                // if not null, check for matches
                if (currentDisplay != null) {

                    if (matched.isEmpty()) {
                        // is the first non-empty Tile found in column
                        // just add to matched to find possible matches
                        matched.add(currentTile);

                    } else if (currentDisplay.equals(matched.getFirst().getDisplay())) {
                        // matched has at least one Tile in it
                        // add if the displays are the same
                        matched.add(currentTile);

                    } else if (matched.size() <= 3){
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
            // there was a match (of 4+) in the rightmost column
            return matched; // SUCCESS
        }

        // gone through every column and no matches were found
        return null;
    }
}
