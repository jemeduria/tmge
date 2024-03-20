import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class Memory extends Game implements Endable {
    private static Memory memoryInstance = null;
    private final int minNumTile = 0;
    private final int maxNumTile = 9;
    private final List<Integer> numOptions = new ArrayList<>();

    private Memory() {
        super();

        // initialize tile options
        for (int i=this.minNumTile; i<=this.maxNumTile; i++) {
            this.numOptions.add(i);
        }

        // initialize board
        super.setGameBoard(new MemoryBoard(this.numOptions));

        // initialize players
        super.getPlayers().add(new Player(1, null));
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

    public static synchronized Memory getInstance() {
        if (memoryInstance == null)
            memoryInstance = new Memory();
        return memoryInstance;
    }

    private MemoryBoard getMemoryBoard() {
        if (super.getGameBoard() instanceof MemoryBoard) {
            return (MemoryBoard) super.getGameBoard();
        }
        return null;
    }

    @Override
    public void gameLoop(Scanner scanner) {
        System.out.println("MEMORY RULES");
        System.out.println("     1. Match the numbers 0-9.");
        System.out.println("     2. You have 3 lives.");
        System.out.println("     3. You loose a life if you mis-match two tiles.");

        try {
            Thread.sleep(8000); // Pause for 8 seconds
        } catch (InterruptedException e) {}

        this.showAllValues();
        super.display();
        System.out.println("MEMORIZE THE BOARD.");
        System.out.println("You will have 20 seconds.");

        try {
            Thread.sleep(20000); // Pause for 20 seconds
        } catch (InterruptedException e) {}


        boolean gameIsOver = false;
        while (!gameIsOver) {
            this.hideAllValues();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            super.display();
            this.takeTurn(scanner);
            gameIsOver = isGameOver();
        }
        this.end();
    }

    @Override
    public void takeTurn(Scanner scanner) {
        // announce scores
        for (Player player : super.getPlayers()) {
            int livesLeft = 3-player.getScore();
            System.out.println("LIVES LEFT: " + livesLeft + "\n");
        }

        // get moves from user + check validity
        String move = this.getMove(scanner);

        // do the move
        this.executeMove(move);
        super.display();

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
                        System.out.println("***** Choose your FIRST tile.");
                    } else {
                        System.out.println("***** Choose your SECOND tile.");
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

            // the whole move is a String "<tile1ROW> <tile1COLUMN>,<tile2ROW> <tile2COLUMN>"
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
                String[] parts = chosenTile.split(" ");
                int row = Integer.parseInt(parts[0])-1;
                int col = Integer.parseInt(parts[1])-1;
                moves.add(board.getGameBoard().get(row).get(col));
            }

            board.execute(moves, super.getPlayers().get(0));
        }
    }

    @Override
    public void checkMatch() {
        MemoryBoard board = this.getMemoryBoard();
        if (!(board == null)) {

            List<Tile> matches = board.checkMatches();
            if (matches != null) {
                // make the Tiles disappear
                System.out.println("Found a match.");
                board.removeMatchedTiles(matches);
            } else {
                System.out.println("NOT A MATCH");
                this.addPlayerPoint(null);
            }

            try {
                // Pause for 3 seconds to give user one second to check displays
                Thread.sleep(3000);
            } catch (InterruptedException e) {}

        }
    }

    @Override
    public void addPlayerPoint(List<Tile> tiles) {
        this.getPlayers().get(0).addPoint();
    }

    @Override
    public boolean isGameOver() {

        MemoryBoard board = this.getMemoryBoard();
        if (!(board == null)) {

            boolean noLivesLeft = checkPlayerScore();
            boolean allMatched = board.isFull();

            if (allMatched) {
                System.out.println("SUCCESS! You matched all tiles.");
            }
            if (noLivesLeft) {
                System.out.println("No lives left.");
            }

            return (noLivesLeft || allMatched);

        }

        return true; // ERROR: board is not ConnectFourBoard type
    }

    @Override
    public boolean checkPlayerScore() {
        // if a score is greater than or equal to 3 then game is over = all 3 lives are lost
        return (super.getPlayers().get(0).getScore() >= 3);
    }

    @Override
    public void end() {
        System.out.println("\n===================================================\n");
        System.out.println("MEMORY: GAME OVER\n");
        memoryInstance = null;
    }

    private void hideAllValues() {
        MemoryBoard board = this.getMemoryBoard();
        if (!(board == null)) {

            for (List<Tile> row : board.getGameBoard()) {
                for (Tile tile : row) {
                    if (tile.getDisplay() != null && !tile.getDisplay().equals("X")) {
                        if (tile instanceof MemoryTile memTile) {
                            memTile.hideValue();
                        }
                    }
                }
            }

        }

    }

    private void showAllValues() {
        MemoryBoard board = this.getMemoryBoard();
        if (!(board == null)) {

            for (List<Tile> row : board.getGameBoard()) {
                for (Tile tile : row) {
                    if (tile instanceof MemoryTile memTile) {
                        memTile.showValue();
                    }
                }
            }

        }
    }

}
