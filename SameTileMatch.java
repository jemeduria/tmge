import java.util.ArrayList;
import java.util.List;

public class SameTileMatch implements Matchable {

    public SameTileMatch() {}

    public List<Tile> match(List<List<Tile>> gameBoard) {
        String toBeMatched = null;
        List<Tile> matched = new ArrayList<>();

        for (List<Tile> innerList : gameBoard) {
            for (Tile tile : innerList) {
                // testing display only
                String display = tile.getDisplay();
                
                // check non-null (via Tile display) and "removed" Memory Tile
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

        if (matched.size() > 1) {
            return matched;
        }
        return null;
    }

}
