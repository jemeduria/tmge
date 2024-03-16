import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memory extends Game implements Endable {

    private final int minNumTile = 0;
    private final int maxNumTile = 9;
    private final List<Integer> numOptions = new ArrayList<>();
    private final Player player = new Player(1, null);

    public Memory() {
        super();
        for (int i=this.minNumTile; i<=this.maxNumTile; i++) {
            this.numOptions.add(i);
        }
        super.setGameBoard(new MemoryBoard(this.numOptions));
        
    }

    public int getMinNumTile() {
        return minNumTile;
    }

    public int getMaxNumTile() {
        return maxNumTile;
    }

    public List<Integer> getNumOptions() {
        return numOptions;
    }

    public Player getPlayer() {
        return player;
    }

    private MemoryBoard getMemoryBoard() {
        if (super.getGameBoard() instanceof MemoryBoard) {
            return (MemoryBoard) super.getGameBoard();
        }
        return null;
    }

    @Override
    public void gameLoop(Scanner scanner) {

        boolean gameIsOver = false;
        while (!gameIsOver) {

            // loop here
            this.takeTurn(scanner);

            gameIsOver = isGameOver();
        }


    }

    @Override
    public void takeTurn(Scanner scanner) {
        // announce turn
        System.out.println("    Enter two Tiles to check match.");

        // get moves from user + check validity
        String move = this.getMove(scanner);

        // do the move
        this.executeMove(move);

        // check for matches & update status of game
        this.checkMatch();

    }

    @Override
    public String getMove(Scanner scanner) {
        MemoryBoard board = getMemoryBoard();
        if (!(board == null)) {

            // allow user to pick two tiles to match together
            String tile1 = null;
            String tile2 = null;

            boolean pickedTwo = false;
            while (!pickedTwo) {
                String tile;
                do {
                    if (tile1 == null) {
                        System.out.println("Choose your FIRST tile.");
                    } else {
                        System.out.println("Choose your SECOND tile.");
                    }
                    // tile = <ROW> <COLUMN>
                    tile = this.choose(scanner);
                }
                while (!board.isValidMove(tile));

                // ensure both tiles have been picked
                if (tile1 == null) {
                    tile1 = tile;
                } else {
                    tile2 = tile;
                    pickedTwo = true;
                }
            }

            // the whole move is a String "<tile1ROW> <tile2COLUMN>,<tile2ROW> <tile2COLUMN>"
            return tile1 + "," + tile2;
        }
        return null; // MAJOR ISSUE IF THIS CODE IS REACHED
    }

    @Override
    public String choose(Scanner scanner) {
        // ensures that the chosen move is a valid column number
        MemoryBoard board = this.getMemoryBoard();
        if (!(board == null)) {
            int maxColumns = board.getColumns();
            int minColumns = 1;
            int maxRows = board.getRows();
            int minRows = 1;

            int tileRow = super.parseNumber(scanner, "Enter the ROW number of your choice: ", minRows, maxRows);
            int tileColumn = super.parseNumber(scanner, "Enter the COLUMN number of your choice: ", minColumns, maxColumns);
            return String.valueOf(tileRow) + " " + String.valueOf(tileColumn);
        }
        return null; // MAJOR ISSUE IF THIS CODE IS REACHED
    }

    @Override
    public void executeMove(String move) {
        // move = "<tile1ROW> <tile2COLUMN>,<tile2ROW> <tile2COLUMN>"
        String[] chosenTiles = move.split(",");
        List<Tile> moves = new ArrayList<>();

        MemoryBoard board = this.getMemoryBoard();
        if (!(board == null)) {

            // parse choices into moves
            for (String chosenTile : chosenTiles) {
                // chosenTile = "<tile1ROW> <tile2COLUMN>
                String[] parts = move.split(" ");
                int row = Integer.parseInt(parts[0])-1;
                int col = Integer.parseInt(parts[0])-1;
                moves.add(board.getGameBoard().get(row).get(col));
            }

            board.execute(moves, this.getPlayer());
        }
    }

    @Override
    public void checkMatch() {}

    @Override
    public void addPlayerPoint(List<Tile> tiles) {
        if (tiles != null) {
            this.getPlayers().get(0).addPoint();
        }
    }

    @Override
    public boolean isGameOver() {
        for(int i = 0; i < this.getMemoryBoard().getRows(); i++) {
            for(int j = 0; j < this.getMemoryBoard().getColumns(); j++) {
                if(!this.getMemoryBoard().getGameBoard().get(i).get(j).equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkPlayerScore() {
        return true;
    }

    public void end() {}

}
