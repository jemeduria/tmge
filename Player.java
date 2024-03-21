public class Player {
    // ATTRIBUTES
    private final int ID;
    private int score;
    private String display = null;

    // CONSTRUCTOR
    public Player(int ID, String display) {
        this.ID = ID;
        this.score = 0;
        this.display = display;
    }

    // GETTERS + SETTERS
    public int getID() { return ID; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getDisplay() { return display; }
    public void setDisplay(String display) { this.display = display; }

    // OPERATION
    public void addPoint() {
        this.setScore(this.getScore() + 1);
    }
}
