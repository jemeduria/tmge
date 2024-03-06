import java.util.ArrayList;
import java.util.List;

public class MemoryBoard extends Board {

    private final int rows = 5;
    private final int columns = 4;

    private final List<Integer> tileOptions;

    public MemoryBoard(List<Integer> tileOptions) {
        super();
        this.addMatches();
        this.tileOptions = tileOptions;
    }

    public void addMatches() {
        super.getMatches().add(new SameTileMatch());
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public boolean isValidMove(String move) {
        return true;
    }

    public void executeMove(List<Tile> tiles) {
        ;
    }

    public List<Tile> createBoardTiles(List<Tile> tiles) {
        return new ArrayList<>();
    }

    public void createBoardGame(List<Tile> tiles) {
        // create list of tiles per row
        List<Tile> rowTiles = new ArrayList<>();

        for (Tile tile: tiles) {
            // add a tile
            rowTiles.add(tile);

            // if the row is maxed out to column size
            if (rowTiles.size() == this.getColumns()) {
                // add row of tiles to gameBoard
                super.getGameBoard().add(rowTiles);
                // empty list for new row
                rowTiles = new ArrayList<>();
            }
        }
    }

}
