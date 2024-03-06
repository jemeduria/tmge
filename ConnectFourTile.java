import java.util.ArrayList;
import java.util.List;

public class ConnectFourTile extends DisappearingTile {

    private String display = null;

    public ConnectFourTile() {
        super();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String printDisplay() {
        if (this.getDisplay() == null) {
            return " ";
        } else {
            return this.getDisplay();
        }
    }

    public void disappear() {
        this.setDisplay(null);
    }
}
