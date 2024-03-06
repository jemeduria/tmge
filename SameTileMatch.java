import java.util.List;

public class SameTileMatch implements Matchable {

    public SameTileMatch() {
        ;
    }

    public boolean match(List<Tile> tiles) {
        // ISSUE: tile has no display only the Tile's subclasses do
        // SOLUTION: give Tile a display that is type String...
        //           - NumberTile would have to convert their int to String
        //           - ConnectFourTile is good
        // QUESTION: is this a smart way to implement this? (in terms of having a good TMGE)

//        String toBeMatched = null
//        for (Tile tile: tiles) {
//            if (toBeMatched == null) {
//                 toBeMatched = tile.display();
//            }
//            if (!tile.display().equals(toBeMatched)) {
//                return false;
//            }
//        }

        return true;
    }
}
