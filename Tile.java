public abstract class Tile {
    private int row;
    private int column;

    private String display = null;

    public Tile() {
        ;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRows(int row) {
        this.row = row;
    }

    public void setColumns(int column) {
        this.column = column;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
