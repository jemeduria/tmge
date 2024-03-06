import java.util.List;

public class SimpleDisappear implements Disappearable {

    public SimpleDisappear() {
        ;
    }

    public void disappear(List<DisappearingTile> tiles) {
        for (DisappearingTile tile: tiles) {
            tile.disappear();
        }
    }
}
