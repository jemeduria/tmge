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
    public void execute(List<Tile> tiles, Player player) {
        // in connect four, only one Tile can be placed
        int chosenColumn = tiles.get(0).getColumn();

        // start checking from the lowest Tile in that column
        for (int row = this.getRows()-1; row >= 0; row--) {

            Tile currentTile = super.getGameBoard().get(row).get(chosenColumn);
            if (currentTile.getDisplay() == null) {
                // set Tile to Player's display symbol
                currentTile.setDisplay(player.getDisplay());
                return;
            }
        }
    }

    @Override
    public List<Tile> createBoardTiles() {
        List<Tile> tiles = new ArrayList<>();
        // Create and add tiles to the list based on the number of rows and columns
        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getColumns(); col++) {
                Tile tile = new ConnectFourTile(row, col);
                tiles.add(tile);
            }
        }

        return tiles; // return the ArrayList of type Tile
    }

    @Override
    public void createBoardGame(List<Tile> tiles) {
        // Clear the existing game board before populating it
        super.getGameBoard().clear();

        // Create list of tiles per row
        List<Tile> rowTiles = new ArrayList<>();

        // Populate the game board with the provided tiles
        for (Tile tile: tiles) {
            // Add a tile
            rowTiles.add(tile);

            // If the row is maxed out to column size
            if (rowTiles.size() == columns) {
                // Add row of tiles to gameBoard
                super.getGameBoard().add(rowTiles);
                // Empty list for new row
                rowTiles = new ArrayList<>();
            }
        }
    }

    @Override
    public List<Tile> checkMatches() {
        // find any and all matches (horizontal, vertical, diagonal, or a mix)
        List<Tile> matchedTiles = new ArrayList<>();
        for (Matchable IMatch: super.getMatches()) {
            List<Tile> foundMatches = IMatch.match(super.getGameBoard());
            matchedTiles.addAll(foundMatches);
        }

        // return all matchedTiles
        return (!matchedTiles.isEmpty()) ? matchedTiles : null;
    }

    @Override
    public void removeMatchedTiles(List<Tile> matchedTiles) {}

    @Override
    public boolean isFull() {
        return true;
    }


}
