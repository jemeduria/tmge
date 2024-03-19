import java.util.List;

public class SimpleDisappear implements Disappearable {

    public SimpleDisappear() {
    }

    public void disappear(List<DisappearingTile> tiles, List<List<Tile>> gameBoard) {
        for (DisappearingTile tile : tiles) {
            tile.disappear();
        }
    }
}
