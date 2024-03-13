//package tmge;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DiagonalMatch implements Matchable {
    public DiagonalMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
    	HashSet<Tile> matches = new HashSet<>();
    	ArrayList<ArrayList<Tile>> leftRightDiags = tlbrDiagonals(gameBoard);
    	ArrayList<ArrayList<Tile>> rightLeftDiags = trblDiagonals(gameBoard);
    	for(ArrayList<Tile> diag : leftRightDiags) {
    		ArrayList<Tile> matchesInDiag = findMatches(diag);
    		for(Tile t : matchesInDiag) {
    			matches.add(t);
    		}
    	}
    	for(ArrayList<Tile> diag : rightLeftDiags) {
    		ArrayList<Tile> matchesInDiag = findMatches(diag);
    		for(Tile t : matchesInDiag) {
    			matches.add(t);
    		}
    	}
    	if(!matches.isEmpty()) {
    		return new ArrayList<>(matches);
    	}
        return null;
    }
    
    ArrayList<ArrayList<Tile>> tlbrDiagonals(List<List<Tile>> board){
    	int rows = board.size();
    	int cols = board.get(0).size();
    	ArrayList<ArrayList<Tile>> diags = new ArrayList<>();
    	for(int c = 0; c < cols; c++) {
    		ArrayList<Tile> diag = new ArrayList<>();
    		int row = 0;
    		int col = c;
    		while(row < rows && col < cols) {
    			diag.add(board.get(row).get(col));
    			row++;
    			col++;
    		}
    		diags.add(diag);
    	}
    	for(int r = 1; r < rows; r++) {
    		ArrayList<Tile> diag = new ArrayList<>();
    		int row = r;
    		int col = 0;
    		while(row < rows && col < cols) {
    			diag.add(board.get(row).get(col));
    			row++;
    			col++;
    		}
    		diags.add(diag);
    	}
    	return diags;
    }
    
    ArrayList<ArrayList<Tile>> trblDiagonals(List<List<Tile>> board){
    	int rows = board.size();
    	int cols = board.get(0).size();
    	ArrayList<ArrayList<Tile>> diags = new ArrayList<>();
    	for(int c = cols - 1; c >= 0; c--) {
    		ArrayList<Tile> diag = new ArrayList<>();
    		int row = 0;
    		int col = c;
    		while(row < rows && col >= 0) {
    			diag.add(board.get(row).get(col));
    			row++;
    			col--;
    		}
    		diags.add(diag);
    	}
    	for(int r = 1; r < rows; r++) {
    		ArrayList<Tile> diag = new ArrayList<>();
    		int row = r;
    		int col = cols - 1;
    		while(row < rows && col >= 0) {
    			diag.add(board.get(row).get(col));
    			row++;
    			col--;
    		}
    		diags.add(diag);
    	}
    	return diags;
    }
    
    ArrayList<Tile> findMatches(ArrayList<Tile> diag){
    	ArrayList<Tile> matches = new ArrayList<>();
    	ArrayList<Tile> group = new ArrayList<>();
        for (int i = 0; i < diag.size() - 1; i++) {
            Tile cur = diag.get(i);
            Tile next = diag.get(i + 1);
            if(cur.getDisplay() == null) {
            	continue;
            }
            if (next.getDisplay().equals(cur.getDisplay())) {
                group.add(cur);
                if (i == diag.size() - 2) {
                    group.add(next);
                    matches.addAll(group);
                }
            } else {
                if (!group.isEmpty()) {
                    group.add(cur);
                    if(group.size() >= 4) {
                    	matches.addAll(group);
                    }
                    group.clear();
                }
            }
        }
        return matches;
    }

}
