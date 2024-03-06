public abstract class Tile {
    private int rows;
    private int columns;

    private String display = null;

    public Tile() {
        ;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void setRows(int row) {
        this.rows = row;
    }

    public void setColumns(int column) {
        this.columns = column;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
