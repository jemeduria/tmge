import java.util.ArrayList;
import java.util.List;

public class ConnectFourTile extends DisappearingTile {

    private final List<String> displayOptions = new ArrayList<String>();

    public ConnectFourTile(List<String> tileOptions, int row, int column) {
        super(row, column);
        this.setDisplayOptions(tileOptions);
    }

    public void setDisplayOptions(List<String> tileOptions) {
        this.displayOptions.addAll(tileOptions);
    }

    public void disappear() {
        super.setDisplay(null);
    }


}
