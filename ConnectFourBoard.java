import java.util.ArrayList;
import java.util.List;

public class ConnectFourBoard extends Board {

    private final int rows = 6;
    private final int columns = 7;

    public ConnectFourBoard() {
        super();
        this.addMatches();
    }

    public void addMatches() {
        super.getMatches().add(new VerticalMatch());
        super.getMatches().add(new HorizontalMatch());
        super.getMatches().add(new DiagonalMatch());
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
