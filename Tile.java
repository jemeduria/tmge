public abstract class Tile {
    // ATTRIBUTES
    private int row;
    private int column;
    private String display = null;

    // CONSTRUCTOR
    public Tile(int row, int col) {
        this.row = row;
        this.column = col;
    }

    // GETTERS + SETTERS
    public int getRow() { return this.row;}
    public int getColumn() { return this.column;}
    public String getDisplay() {
        return this.display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }

    // OPERATION
    public String printDisplay() {
        if (this.getDisplay() == null) {
            return " ";
        } else {
            return this.getDisplay();
        }
    }

}
