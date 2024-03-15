import java.util.ArrayList;
import java.util.List;

public class ConnectFourBoard extends Board {

    private final int rows = 6;
    private final int columns = 7;

    public ConnectFourBoard() {
        super();
        this.addMatches();
        this.createBoardGame(new ArrayList<>()); // placeholder input
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    @Override
    public void addMatches() {
        super.getMatches().add(new VerticalMatch());
        super.getMatches().add(new HorizontalMatch());
        super.getMatches().add(new DiagonalMatch());
    }

    @Override
    public boolean isValidMove(String move) {
        // check if column is free (there is at least one available space at the top in that column)
//        if (super.getGameBoard().getFirst().get(Integer.parseInt(move)-1).getDisplay() == null) {
        if (super.getGameBoard().get(0).get(Integer.parseInt(move)-1).getDisplay() == null) {
            return true;
        } else {
            System.out.println("ERROR: Column is full. Choose a different column.");
            return false;
        }
    }

    @Override
    public void execute(List<ConnectFourTile> tiles, Player player) {
        // in connect four, only one Tile can be placed
//        int chosenColumn = tiles.getFirst().getColumn();
        int chosenColumn = tiles.get(0).getColumn();

        // start checking from the lowest Tile in that column
        for (int row = this.getRows()-1; row >= 0; row--) {

            ConnectFourTile currentTile = super.getGameBoard().get(row).get(chosenColumn);
            if (currentTile.getDisplay() == null) {
                // set Tile to Player's display symbol
                currentTile.setDisplay(player.getDisplay());
                return;
            }
        }
    }

    @Override
    public List<ConnectFourTile> createBoardTiles() {
//        return new ArrayList<>();   // // original code

        List<ConnectFourTile> tiles = new ArrayList<>();

        // Create and add tiles to the list based on the number of rows and columns
        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getColumns(); col++) {
                ConnectFourTile tile = new ConnectFourTile(row, col);
                tiles.add(tile);
            }
        }

        return tiles; // return the ArrayList of type Tile
    }

    @Override
    public void createBoardGame(List<ConnectFourTile> tiles) {
//    // ---- original code starts here.
//        // create list of tiles per row
//        List<Tile> rowTiles = new ArrayList<>();
//
//        for (Tile tile: tiles) {
//            // add a tile
//            rowTiles.add(tile);
//
//            // if the row is maxed out to column size
//            if (rowTiles.size() == this.getColumns()) {
//                // add row of tiles to gameBoard
//                super.getGameBoard().add(rowTiles);
//                // empty list for new row
//                rowTiles = new ArrayList<>();
//            }
//        }
//    // --- original code ends here.

        // Clear the existing game board before populating it
        super.getGameBoard().clear();

        // Create list of tiles per row
        List<ConnectFourTile> rowTiles = new ArrayList<>();

        // Populate the game board with the provided tiles
        for (ConnectFourTile t : tiles) {
            // Add a tile
            rowTiles.add(t);

            // If the row is maxed out to column size
            if (rowTiles.size() == this.getColumns()) {
                // Add row of tiles to gameBoard
                super.getGameBoard().add(rowTiles);
                // Empty list for new row
                rowTiles = new ArrayList<>();
            }
        }
    }

    @Override
    public List<ConnectFourTile> checkMatches() {
        // find any and all matches (horizontal, vertical, diagonal, or a mix)
        List<ConnectFourTile> matchedTiles = new ArrayList<>();
        for (Matchable IMatch: super.getMatches()) {
            List<ConnectFourTile> foundMatches = IMatch.match(super.getGameBoard());
            matchedTiles.addAll(foundMatches);
        }

        // return all matchedTiles
        return (!matchedTiles.isEmpty()) ? matchedTiles : null;
    }

    @Override
    public void removeMatchedTiles(List<ConnectFourTile> matchedTiles) {}


}
