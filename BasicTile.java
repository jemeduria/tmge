public class BasicTile extends DisappearingTile {
    // CONSTRUCTOR
    public BasicTile(int row, int column) {
        super(row, column);
    }

    // ABSTRACT METHOD IMPLEMENTATION
    @Override
    public void disappear() {
        super.setDisplay(null);
    }

}
