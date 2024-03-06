import java.util.ArrayList;
import java.util.List;

public class MemoryTile extends DisappearingTile {
    private final List<Integer> displayOptions = new ArrayList<Integer>();

    public MemoryTile() {
        super();
        this.setDisplayOptions();
    }

    public void setDisplayOptions() {
        for (int i=0; i<=9; i++) {
            this.displayOptions.add(i);
        }
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
