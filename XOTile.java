import java.util.ArrayList;
import java.util.List;

public class XOTile extends DisappearingTile {

    private final List<String> displayOptions = new ArrayList<String>();

    public XOTile() {
        super();
        this.setDisplayOptions();
    }

    public void setDisplayOptions() {
        this.displayOptions.add("X");
        this.displayOptions.add("0");
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
