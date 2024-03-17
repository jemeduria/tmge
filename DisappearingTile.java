public abstract class DisappearingTile extends Tile {

    public DisappearingTile(int row, int column) {
        super(row, column);
    }

    public abstract void disappear();

}
