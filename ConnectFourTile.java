import java.util.ArrayList;
import java.util.List;

public class ConnectFourTile extends DisappearingTile {

    private final List<String> displayOptions = new ArrayList<String>();

    public ConnectFourTile() {
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
