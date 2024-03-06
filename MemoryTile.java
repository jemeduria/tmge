import java.util.ArrayList;
import java.util.List;

public class MemoryTile extends DisappearingTile {
    private final List<Integer> displayOptions = new ArrayList<Integer>();

    public MemoryTile() {
        super();
        this.setDisplayOptions();
    }

    public void setDisplayOptions() {
        this.displayOptions.add(0);
        this.displayOptions.add(1);
        this.displayOptions.add(2);
        this.displayOptions.add(3);
        this.displayOptions.add(4);
        this.displayOptions.add(5);
        this.displayOptions.add(6);
        this.displayOptions.add(7);
        this.displayOptions.add(8);
        this.displayOptions.add(9);
        // probably a better way to do this
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
