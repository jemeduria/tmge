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
        // check if column is free (there is at least one available space at the top in that column)
        if (super.getGameBoard().getFirst().get(Integer.parseInt(move)-1).getDisplay() == null) {
            return true;
        } else {
            System.out.println("ERROR: Column is full. Choose a different column.");
            return false;
        }
    }


    public void execute(List<Tile> tiles, Player player) {
        // in connect four, only one Tile can be placed
        int chosenColumn = tiles.getFirst().getColumns();

        for (int row = this.getRows()-1; row >= 0; row--) {
            Tile currentTile = super.getGameBoard().get(row).get(chosenColumn);
            if (currentTile.getDisplay() == null) {
                // set Tile to Player's display symbol
                currentTile.setDisplay(player.getDisplay());
                return;
            }
        }
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

    public void checkMatches() {
        List<Tile> matchedTiles = new ArrayList<>();
        for (Matchable IMatch: super.getMatches()) {
            List<Tile> foundMatches = IMatch.match(super.getGameBoard());
            matchedTiles.addAll(foundMatches);
        }

        this.removeMatchedTiles(matchedTiles);
    }

    public void removeMatchedTiles(List<Tile> matchedTiles) {
        // remove all matched tiles from the highest positioning tile first
        ;
    }


}
