import java.util.ArrayList;
import java.util.List;

public class ConnectFourBoard extends Board {

    private final int rows = 6;
    private final int columns = 7;
    private final List<String> tileOptions;

    public ConnectFourBoard(List<String> tileOptions) {
        super();
        this.tileOptions = tileOptions;
        this.addMatches();
        this.addDisappearTypes();
        this.createBoardGame(this.createBoardTiles());
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
    public void addDisappearTypes() {
        super.getDisappearTypes().add(new SimpleDisappear());
    }

    @Override
    public boolean isValidMove(String move) {
        // check if column is free (there is at least one available space at the top in that column)
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
                tiles.add(new ConnectFourTile(tileOptions, row, col));
            }
        }

        return tiles; // return the ArrayList of type Tile
    }

    @Override
    public void createBoardGame(List<Tile> tiles) {
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
            if (foundMatches != null) {
                matchedTiles.addAll(foundMatches);
            }
        }

        return (!matchedTiles.isEmpty()) ? matchedTiles : null;
    }

    @Override
    public void removeMatchedTiles(List<Tile> matchedTiles) {
        List<DisappearingTile> disappearingTiles = new ArrayList<>();
        for (Tile tile : matchedTiles) {
            if (tile instanceof DisappearingTile dTile) {
                disappearingTiles.add(dTile);
            }
        }

        // only one type of disappearing
        for (Disappearable IDisappear : super.getDisappearTypes()) {
            IDisappear.disappear(disappearingTiles, super.getGameBoard());
        }

        // drop the tiles
        this.dropTiles(disappearingTiles);

    }

    private void dropTiles(List<DisappearingTile> disappearingTiles) {
        // order Tiles by highest row (lowest row number) first
        List<DisappearingTile> orderedTiles = new ArrayList<>();
        for (int i=0; i<this.getRows(); i++) {
            for (DisappearingTile tile: disappearingTiles) {
                if (tile.getRow() == i) {
                    orderedTiles.add(tile);
                }
            }
        }

        // algorithm to swap display to top
        for (DisappearingTile tile: orderedTiles) {
            this.drop((Tile) tile);
        }

    }

    private void drop(Tile tile) {
        int row = tile.getRow();
        int column = tile.getColumn();

        // start from the current row and move upwards
        for (int currentRow = row; currentRow > 0; currentRow--) {
            Tile currentTile = super.getGameBoard().get(currentRow).get(column);
            Tile aboveTile = super.getGameBoard().get(currentRow - 1).get(column);

            // swap null tile to one above
            if (aboveTile.getDisplay() != null) {
                String tempDisplay = currentTile.getDisplay();
                currentTile.setDisplay(aboveTile.getDisplay());
                aboveTile.setDisplay(tempDisplay);
            } else {
                return;
            }
        }

    }

    @Override
    public boolean isFull() {
        List<List<Tile>> gameBoard = super.getGameBoard();
        for (List<Tile> row : gameBoard) {
            for (Tile tile : row) {
                if (tile.getDisplay() == null) {
                    return false;
                }
            }
        }
        return true;
    }


}
