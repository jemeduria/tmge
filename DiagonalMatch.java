package tmge;
import java.util.ArrayList;
import java.util.List;

public class DiagonalMatch implements Matchable {

    public DiagonalMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
    	
    	
    	for(int row = 0; row < 6; row++) {
    		for(int col = 0; col < 7; col++) {
    			
    			
    		}
    	}
        return null;
    }
    
    List<Tile> diagFromTop(List<List<Tile>> gameBoard){
    	
    	for(int i = 0; i < 2; i++) {
            List<List<Tile>> repeatedElements = new ArrayList<>();
            List<Tile> repeatedGroup = new ArrayList<>();
    		for(int j = 0; j < 4; j++) {
    			String curVal = gameBoard.get(j).get(j + i).getDisplay();
    			String nextVal = gameBoard.get(j + 1).get(j + i + 1).getDisplay();
    			if( != null && gameBoard.get(j).get(j).getDisplay().equals(cur)) {
    				cur = gameBoard.get(j).get(j);
    				
    			}
    		}
    	}
    }

    List<Tile> topRight(List<List<Tile>> gameBoard, Tile tile){
    	Tile cur;
    	List<Tile> tileList = new ArrayList<Tile>();
    	tileList.add(tile);

		for(int i = 1; i < 4; i++) {
			if((tile.getRows() - i) < 0 || (tile.getColumns() + i) > 7) {
				break;
			}
			cur = gameBoard.get(tile.getRows() - i).get(tile.getColumns() + i);
			String curVal = cur.getDisplay();
			if(curVal != null && curVal.equals(tile.getDisplay())) {
				tileList.add(cur);
			}else {
				break;
			}
		}
    	
		if(tileList.size() == 4) {
			return tileList;
		}
    	return new ArrayList<Tile>();
    }
    
    List<Tile> topLeft(List<List<Tile>> gameBoard, Tile tile){
    	Tile cur;
    	List<Tile> tileList = new ArrayList<Tile>();
    	tileList.add(tile);

		for(int i = 1; i < 4; i++) {
			if((tile.getRows() - i) < 0 || (tile.getColumns() - i) < 0) {
				break;
			}
			cur = gameBoard.get(tile.getRows() - i).get(tile.getColumns() - i);
			String curVal = cur.getDisplay();
			if(curVal != null && curVal.equals(tile.getDisplay())) {
				tileList.add(cur);
			}else {
				break;
			}
		}
    	
		if(tileList.size() == 4) {
			return tileList;
		}
    	return new ArrayList<Tile>();
    }
    
    List<Tile> bottomRight(List<List<Tile>> gameBoard, Tile tile){
    	Tile cur;
    	List<Tile> tileList = new ArrayList<Tile>();
    	tileList.add(tile);

		for(int i = 1; i < 4; i++) {
			if((tile.getRows() + i) > 6 || (tile.getColumns() + i) > 7) {
				break;
			}
			cur = gameBoard.get(tile.getRows() + i).get(tile.getColumns() + i);
			String curVal = cur.getDisplay();
			if(curVal != null && curVal.equals(tile.getDisplay())) {
				tileList.add(cur);
			}else {
				break;
			}
		}
    	
		if(tileList.size() == 4) {
			return tileList;
		}
    	return new ArrayList<Tile>();
    }
    
    List<Tile> bottomLeft(List<List<Tile>> gameBoard, Tile tile){
    	Tile cur;
    	List<Tile> tileList = new ArrayList<Tile>();
    	tileList.add(tile);

		for(int i = 1; i < 4; i++) {
			if((tile.getRows() + i) > 6 || (tile.getColumns() - i) < 0) {
				break;
			}
			cur = gameBoard.get(tile.getRows() + i).get(tile.getColumns() - i);
			String curVal = cur.getDisplay();
			if(curVal != null && curVal.equals(tile.getDisplay())) {
				tileList.add(cur);
			}else {
				break;
			}
		}
    	
		if(tileList.size() == 4) {
			return tileList;
		}
    	return new ArrayList<Tile>();
    }
}
