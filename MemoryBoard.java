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
    public List<MemoryTile> createBoardTiles() {
    	//populates list with tiles with row, col
    	List<MemoryTile> list = new ArrayList<MemoryTile>();
    	for(int i = 0; i < rows; i++) {
    		for(int j = 0; j < columns; j++) {
    			list.add(new MemoryTile(numOptions, i, j, 0));
    		}
    	}
    	
    	//creates values for tiles and randomizes
    	List<Integer> temp = new ArrayList<Integer>();
    	for(int i = 0; i < (rows*columns)/2; i++){
    		temp.add(i);
    		temp.add(i);
    	}
    	Collections.shuffle(temp);
    	
    	//assigns the values to tiles
    	for(int i = 0; i < temp.size(); i++) {
    		list.get(i).setValue(temp.get(i));
    	}
    	
        return list;
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
