import java.util.List;

public class VerticalMatch implements Matchable {
    public VerticalMatch() {
        ;
    }

    public boolean match(List<Tile> tiles) {
        // make sure all tiles are in the same column
        // make sure that within the tiles given, all are exactly 1 row apart
        //     - take into account that tiles may be passed out of order
        return true;
    }
}
