import java.util.ArrayList;
import java.util.List;

public class MemoryBoard extends Board {

    private final int rows = 5;
    private final int columns = 4;

    private final List<Integer> numOptions;

    public MemoryBoard(List<Integer> numOptions) {
        super();
        this.addMatches();
        this.numOptions = numOptions;
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
                	if(!this.getGameBoard().get(row).get(col).getDisplay().equals("X"))
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
    public List<Tile> createBoardTiles(List<Tile> tiles) {
        return new ArrayList<Tile>();
    }

    @Override
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

    @Override
    public List<Tile> checkMatches() {
        return new ArrayList<Tile>();
    }

    @Override
    public void removeMatchedTiles(List<Tile> matchedTiles) {}

}
