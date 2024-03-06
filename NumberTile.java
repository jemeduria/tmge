import java.util.ArrayList;
import java.util.List;

public class NumberTile extends DisappearingTile {
    private final List<Integer> displayOptions = new ArrayList<Integer>();

    public NumberTile(List<Integer> tileOptions) {
        super();
        this.setDisplayOptions(tileOptions);
    }

    public void setDisplayOptions(List<Integer> tileOptions) {
        this.displayOptions.addAll(tileOptions);
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
