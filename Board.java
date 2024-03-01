import java.util.ArrayList;

public abstract class Board {
	private ArrayList<ArrayList<Tile>> gameBoard;
	
	Board(int height, int width){
		gameBoard = new ArrayList<>();
		for(int i; i < height; i++) {
			//new innerArraylist
			for(int j; j < width; j++) {
				//add tile
			}
		}
	}
}
