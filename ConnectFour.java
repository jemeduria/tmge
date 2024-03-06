import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectFour extends Game {
    public ConnectFour() {
        super();
        super.setGameBoard(new ConnectFourBoard());
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
