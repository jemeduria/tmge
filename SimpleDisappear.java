import java.util.List;

public class SimpleDisappear implements Disappearable {
    // CONSTRUCTOR
    public SimpleDisappear() {}

    // INTERFACE IMPLEMENTATION
    public void disappear(List<DisappearingTile> tiles, List<List<Tile>> gameBoard) {
        for (DisappearingTile tile : tiles) {
            tile.disappear();
        }
    }
}
