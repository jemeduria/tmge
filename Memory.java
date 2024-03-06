import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memory extends Game {

    private final int minNumTile = 0;
    private final int maxNumTile = 9;
    private final List<Integer> numOptions = new ArrayList<>();
    public Memory() {
        super();
        for (int i=minNumTile; i<=9; i++) {
            this.numOptions.add(i);
        }
        super.setGameBoard(new MemoryBoard(this.numOptions));
    }

    public void gameLoop() {
        // to end game at any point, just a simple "return;" works
    }

    public boolean isGameOver() {
        return true;
    }

    public void takeTurn() {}

    public void chooseTile() {}

    public void checkMatch() {}

    public boolean isMatch(List<Tile> tiles) {
        return true;
    }

    public void chooseTile(List<Tile> tiles) {}
}
