import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryBoard extends Board {

    private final int rows = 5;
    private final int columns = 4;

    private final List<Integer> numOptions;

    public MemoryBoard(List<Integer> numOptions) {
        super();
        this.addMatches();
        this.numOptions = numOptions;

        this.createBoardGame(this.createBoardTiles());
    }
    @Override
    public void addMatches() {
        super.getMatches().add(new SameTileMatch());
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public List<Integer> getNumOptions() {
        return numOptions;
    }

    @Override
    public boolean isValidMove(String move) {
    	String pattern = "\\d+\\s\\d+";
    	if(move.matches(pattern)) {
    		String[] parts = move.split(" ");
            try 
            {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                if(0 <= row && row <= 4 && col < 5 && col >= 0) {
                	if(!super.getGameBoard().get(row).get(col).getDisplay().equals("X"))
                		return true;
                }
                return false;
                
            } catch (NumberFormatException e) {
                return false;
            }
    	}
        return false;
    }

    @Override
    public void execute(List<Tile> tiles, Player player) {}

    @Override
    public List<Tile> createBoardTiles() {
        // main list to get create Memory Tiles
        List<Tile> memoryTiles = new ArrayList<>();

        // double the amount of the each number (for matching)
        List<Integer> allMemoryValues = new ArrayList<>(numOptions);
        allMemoryValues.addAll(numOptions);

        // shuffle all values
        Collections.shuffle(allMemoryValues);

        // create and initialize all Memory Tiles with its value
        int valIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Integer value = allMemoryValues.get(valIndex % allMemoryValues.size());
                memoryTiles.add(new MemoryTile(numOptions, i, j, value));
                valIndex++;
            }
        }

        return memoryTiles;
    }

    @Override
    public void createBoardGame(List<Tile> tiles) {
        // Clear the existing game board before populating it
        super.getGameBoard().clear();

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

}
