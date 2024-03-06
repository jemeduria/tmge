import java.util.List;

public class HorizontalMatch implements Matchable {
    public HorizontalMatch() {
        ;
    }

    public boolean match(List<Tile> tiles) {
        // make sure all tiles are in the same row
        // make sure that within the tiles given, all are exactly 1 column apart
        //     - take into account that tiles may be passed out of order
        return true;
    }
}
