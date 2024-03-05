import java.util.ArrayList;
import java.util.List;

public class ConnectFourBoard extends Board {

    private final int rows = 6;
    private final int columns = 7;

    public ConnectFourBoard() {
        super();
        this.addMatches();
    }

    private void addMatches() {
        super.getMatches().add(new VerticalMatch());
        super.getMatches().add(new HorizontalMatch());
        super.getMatches().add(new DiagonalMatch());
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public boolean isValidMove(String move) {
        return true;
    }

    public void executeMove(List<Tile> tiles) {
        ;
    }

    public List<Tile> createBoardTiles(List<Tile> tiles) {
        return new ArrayList<>();
    }

    public void createBoardGame(List<Tile> tiles) {
        for(int i=0; i < this.getRows(); i++) {
            List gameTiles = new ArrayList<>();

            // add Tiles within the rows
            for (int j=0; j < this.getColumns(); j++) {
                gameTiles.add();
            }
            // add a new ArrayList for each row
            super.getGameBoard().add(new ArrayList<>());
        }
    }
}
