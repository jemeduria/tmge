public class NumberTile extends DisappearingTile {

    private String display = null;

    public NumberTile() {
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
