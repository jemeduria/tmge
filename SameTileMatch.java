import java.util.List;

public class SameTileMatch implements Matchable {

    public SameTileMatch() {
        ;
    }

    public boolean match(List<DisappearingTile> tiles) {
        String toBeMatched = null;
        for (DisappearingTile tile: tiles) {
            if (toBeMatched == null) {
                // get something to match
                 toBeMatched = tile.getDisplay();
                 if (toBeMatched == null) {
                     return false;
                 }
            }

            // check match for all tiles
            if (!tile.getDisplay().equals(toBeMatched)) {
                return false;
            }
        }

        // all matched!
        return true;
    }
}
