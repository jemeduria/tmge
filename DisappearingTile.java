public abstract class DisappearingTile extends Tile {

    public DisappearingTile() {
        super();
    }

    public DisappearingTile(int r, int c) {
        super(r, c);
    }

    public abstract void disappear();

    public abstract void addTileDisplay(String display);

}
