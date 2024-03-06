import java.util.List;

public class DiagonalMatch implements Matchable {

    public DiagonalMatch() {
        ;
    }

    public boolean match(List<Tile> tiles) {
        // make sure all tiles are in different rows/columns
        // make sure that within the tiles given, all are exactly 1 row and 1 column apart??
        //     - take into account that tiles may be passed out of order
        return true;
    }
}
