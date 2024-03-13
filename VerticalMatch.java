import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.get;

public class VerticalMatch implements Matchable {
    public VerticalMatch() {}

    public List<Tile> match(List<List<Tile>> gameBoard) {
        String toBeMatched = null;
        List<Tile> matched = new ArrayList<>();

        int rows = gameBoard.size();
<<<<<<< Updated upstream
        int columns = gameBoard.getFirst().size();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
//        int columns = gameBoard.getFirst().size();
        int columns = gameBoard.get(0).size();
>>>>>>> Stashed changes
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

//                    } else if (currentDisplay.equals(matched.getFirst().getDisplay())) {
                    } else if (currentDisplay.equals(matched.get(0).getDisplay())) {
                        // matched has at least one Tile in it
                        // add if the displays are the same
                        matched.add(currentTile);

                    } else if (matched.size() <= 3){
                        // currentDisplay does not have the same displays in matched
                        // AND there is less than 4 Tiles in the matched
                        // THEREFORE reset matched to find another match
                        matched.clear();
                        matched.add(currentTile);
=======
=======
>>>>>>> Stashed changes

        // for each tile

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row <= rows - 4; row++) {
                if (gameBoard.get(row).get(col) == playerToken &&
                        gameBoard.get(row + 1).get(col) == playerToken &&
                        gameBoard.get(row + 2).get(col) == playerToken &&
                        gameBoard.get(row + 3).get(col) == playerToken) {
                    return true; // Found a vertical match
                }
            }
        }

        for (List<Tile> innerList : gameBoard) {
            for (Tile tile : innerList) {
                // get display of tile
                String display = tile.getDisplay();

                // check for non-null Tile display and non-"removed" Memory Tile
                if (!(display == null) && !(display.equals("X"))) {
                    // initialize toBeMatched
                    if (toBeMatched == null) {
                        toBeMatched = display;
                    }
                    // find tile with a display that matches toBeMatched
                    if (tile.getDisplay().equals(toBeMatched)) {
                        matched.add(tile);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                    }
                }
            }
        }
<<<<<<< Updated upstream
<<<<<<< Updated upstream

        if (matched.size() >= 4) {
            // there was a match (of 4+) in the rightmost column
            return matched; // SUCCESS
        }

        // gone through every column and no matches were found
=======
=======
>>>>>>> Stashed changes
        // in the Memory game, matched should always be a length of 2
        if (matched.size() > 1) {
            return matched;
        }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        return null;
    }
}
