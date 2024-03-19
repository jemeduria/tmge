import java.util.ArrayList;
import java.util.List;

public class MemoryTile extends DisappearingTile {
    private final List<String> displayOptions = new ArrayList<String>();
    private final int value;
    
    public MemoryTile(List<Integer> tileOptions, int row, int col, int val) {
    	super(row, col);
        this.setDisplayOptions(tileOptions);

        this.value = val;
    }

    public int getValue() {
        return value;
    }

    public void setDisplayOptions(List<Integer> tileOptions) {
        for (Integer num: tileOptions) {
            this.displayOptions.add(num.toString());
        }
        this.displayOptions.add("X"); // for already "picked" Memory Tiles
    }

    public void disappear() {
        super.setDisplay("X");
    }

    public void showValue() {
        super.setDisplay(String.valueOf(this.getValue()));
    }

    public void hideValue() { super.setDisplay(null); }

}
