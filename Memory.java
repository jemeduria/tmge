import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memory extends Game {

    private final int minNumTile = 0;
    private final int maxNumTile = 9;
    private final List<Integer> numOptions = new ArrayList<>();
    public Memory() {
        super();
        for (int i=this.minNumTile; i<=this.maxNumTile; i++) {
            this.numOptions.add(i);
        }
        super.setGameBoard(new MemoryBoard(this.numOptions));
    }

    public String getMove(Scanner scanner) {
        return "";
    }

    public void gameLoop(Scanner scanner) {

        boolean gameIsOver = false;
        while (!gameIsOver) {
            ;
            if (isGameOver()) {
                gameIsOver = true;
            }
        }

    }

    public boolean isGameOver() {
        return true;
    }

    public void takeTurn(Scanner scanner) {}

    public void chooseTile() {}

    public void checkMatch() {}

    public boolean isMatch(List<Tile> tiles) {
        return true;
    }

    public void chooseTile(List<Tile> tiles) {}
}
