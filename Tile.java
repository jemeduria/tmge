public abstract class Tile {
    private int rows;
    private int columns;

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

//    public abstract String getDisplay();

}
