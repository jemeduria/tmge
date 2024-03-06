import java.util.ArrayList;
import java.util.List;

public class MemoryTile extends DisappearingTile {
    private final List<String> displayOptions = new ArrayList<String>();

    public MemoryTile(List<Integer> tileOptions) {
        super();
        this.setDisplayOptions(tileOptions);
    }

    public void setDisplayOptions(List<Integer> tileOptions) {
        for (Integer num: tileOptions) {
            this.displayOptions.add(num.toString());
        }
        this.displayOptions.add("X"); // for already "picked" Memory Tiles
    }

    public String printDisplay() {
        if (super.getDisplay() == null) {
            return " ";
        } else {
            return this.getDisplay();
        }
    }

    public void disappear() {
        super.setDisplay(null);
    }

    public void addTileDisplay(String display) {
        super.setDisplay(display);
    }

}
