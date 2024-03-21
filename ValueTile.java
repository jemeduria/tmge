public class ValueTile extends DisappearingTile {
    // ATTRIBUTE
    private final int value;

    // CONSTRUCTOR
    public ValueTile(int row, int col, int val) {
    	super(row, col);
        this.value = val;
    }

    // GETTER
    public int getValue() { return value; }

    // OPERATIONS
    public void showValue() { super.setDisplay(String.valueOf(this.getValue())); }
    public void hideValue() { super.setDisplay(null); }

    // ABSTRACT METHOD IMPLEMENTATION
    @Override
    public void disappear() { super.setDisplay("X"); }

}
