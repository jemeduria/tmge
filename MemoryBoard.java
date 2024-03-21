import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryBoard extends Board {
    // ATTRIBUTES
    private final int rows = 5;
    private final int columns = 4;
    private final List<Integer> numOptions;

    // CONSTRUCTOR
    public MemoryBoard(List<Integer> numOptions) {
        super();
        this.numOptions = numOptions;
        this.addMatchTypes();
        this.addDisappearTypes();
        this.createBoardGame(this.createBoardTiles());
    }

    // GETTERS
    public int getRows() {
        return this.rows;
    }
    public int getColumns() {
        return this.columns;
    }
    public List<Integer> getNumOptions() {
        return numOptions;
    }

    // ABSTRACT METHOD IMPLEMENTATIONS
    @Override
    public void addMatchTypes() {
        super.getMatchTypes().add(new SameTileMatch());
    }

    @Override
    public void addDisappearTypes() {
        super.getDisappearTypes().add(new SimpleDisappear());
    }

    @Override
    public void displayBoard() {
        System.out.println("Rows are displayed on the LEFT side of the board (displayed vertically).");
        System.out.println("Columns are displayed on the TOP of the board (displayed horizontally).\n");

        System.out.print("   ");
        for (int colNum=1; colNum<=columns; colNum++) {
            if (colNum == columns) {
                System.out.println(" " + colNum + " ");
            } else {
                System.out.print(" " + colNum + " ");
            }
        }

        for (int rowNum=1; rowNum<=rows; rowNum++) {
            System.out.print(rowNum + "  ");

            for (int colNum=1; colNum<=columns; colNum++) {
                String display = super.getGameBoard().get(rowNum-1).get(colNum-1).printDisplay();
                if (colNum == columns) {
                    System.out.println("[" + display + "]");
                } else {
                    System.out.print("[" + display + "]");
                }
            }
        }

        System.out.println();
    }

    @Override
    public List<Tile> createBoardTiles() {
        // main list to get create Memory Tiles
        List<Tile> memoryTiles = new ArrayList<>();

        // double the amount of each number (for matching)
        List<Integer> allMemoryValues = new ArrayList<>(this.getNumOptions());
        allMemoryValues.addAll(this.getNumOptions());

        // shuffle all values
        Collections.shuffle(allMemoryValues);

        // create and initialize all Memory Tiles with its value
        int valIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Integer value = allMemoryValues.get(valIndex % allMemoryValues.size());
                memoryTiles.add(new ValueTile(i, j, value));
                valIndex++;
            }
        }

        return memoryTiles;
    }

    @Override
    public void createBoardGame(List<Tile> tiles) {
        // create list of tiles per row
        List<Tile> rowTiles = new ArrayList<>();

        for (Tile tile: tiles) {
            // add a tile
            rowTiles.add(tile);

            // if the row is maxed out to column size
            if (rowTiles.size() == columns) {
                // add row of tiles to gameBoard
                super.getGameBoard().add(rowTiles);
                // empty list for new row
                rowTiles = new ArrayList<>();
            }
        }
    }

    @Override
    public boolean isValidMove(String move) {
        // move = <ROW> <COLUMN>
        String[] parts = move.split(" ");
        int row = Integer.parseInt(parts[0])-1;
        int col = Integer.parseInt(parts[1])-1;

        // return false if tile has "disappeared" or if tile has been "matched" already
        // return true if match has not been found yet
        return super.getGameBoard().get(row).get(col).getDisplay() == null;

    }

    @Override
    public void execute(List<Tile> tiles, Player player) {
        for (Tile tile: tiles) {
            if (tile instanceof ValueTile memTile) {
                memTile.showValue();
            }
        }
    }

    @Override
    public List<Tile> checkMatches() {
        // find any and all matches (same match)
        List<Tile> matchedTiles = new ArrayList<>();
        for (Matchable IMatch: super.getMatchTypes()) {
            List<Tile> foundMatches = IMatch.match(super.getGameBoard());
            if (foundMatches != null) {
                matchedTiles.addAll(foundMatches);
            }
        }

        // return all matchedTiles
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
    }

    @Override
    public boolean isFull() {
        // not really isFull, more like isAllMatched or isAllX
        List<List<Tile>> gameBoard = super.getGameBoard();
        for (List<Tile> row : gameBoard) {
            for (Tile tile : row) {
                if (tile.getDisplay() == null || !(tile.getDisplay().equals("X"))) {
                    return false;
                }
            }
        }
        return true;
    }


}
