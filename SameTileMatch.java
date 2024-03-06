import java.util.List;

public class SameTileMatch implements Matchable {

    public SameTileMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
        String toBeMatched = null;
        for (List<Tile> innerList: gameBoard) {
            for (Tile tile: innerList) {
                String display = tile.getDisplay();

                // initialize toBeMatched
                if (toBeMatched == null) {
                    if (!(display == null)) {
                        toBeMatched = tile.getDisplay();
                    }
                }

                // find tile with a display that matches toBeMatched
                if ((!(display == null)) && (!(t))) {

                }

            }
        }
            if (toBeMatched == null) {

//        String toBeMatched = null;
//        for (Tile tile: tiles) {
//            if (toBeMatched == null) {
//                // get something to match
//                 toBeMatched = tile.getDisplay();
//
//                 if (toBeMatched == null) {
//                     return false;
//                 }
//            }
//
//            // check match for all tiles
//            if (!tile.getDisplay().equals(toBeMatched)) {
//                return false;
//            }
//        }
//
//        // all matched!
//        return true;
    }
}
