import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.get;

public class VerticalMatch implements Matchable {
    public VerticalMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
        String toBeMatched = null;
        List<Tile> matched = new ArrayList<>();

        int rows = gameBoard.size();
        int columns = gameBoard.getFirst().size();

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
                    }
                }
            }
        }
        // in the Memory game, matched should always be a length of 2
        if (matched.size() > 1) {
            return matched;
        }
        return null;
    }
}
