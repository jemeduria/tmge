public abstract class Tile {
    private int row;
    private int column;
    private String display = null;

    public Tile(int r, int c) {
        this.row = r;
        this.column = c;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDisplay() {
        return this.display;
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

}
