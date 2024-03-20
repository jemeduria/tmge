import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectFour extends Game implements Endable {

    private static ConnectFour connectFourInstance = null;
    private Player playerTurn;
    private final List<String> tileOptions = new ArrayList<>();

    private ConnectFour() {
        super();

        // initialize tile options
        this.tileOptions.add("X");
        this.tileOptions.add("O");

        // initialize board
        super.setGameBoard(new ConnectFourBoard(this.tileOptions));

        // initialize players
        super.getPlayers().add(new Player(1, this.tileOptions.get(0)));
        super.getPlayers().add(new Player(2, this.tileOptions.get(1)));
        this.playerTurn =  super.getPlayers().get(1);
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }
    public List<String> getTileOptions() {
        return tileOptions;
    }

    public static synchronized ConnectFour getInstance() {
        if (connectFourInstance == null)
            connectFourInstance = new ConnectFour();
        return connectFourInstance;
    }

    private ConnectFourBoard getConnectFourBoard() {
        if (super.getGameBoard() instanceof ConnectFourBoard) {
            return (ConnectFourBoard) super.getGameBoard();
        }
        return null;
    }

    private void switchPlayer() {
        int currentPlayerID = (this.getPlayerTurn().getID() % super.getPlayers().size()) + 1;

        for (Player player : super.getPlayers()) {
            if (player.getID() == currentPlayerID) {
                this.setPlayerTurn(player);
                break;
            }
        }
    }

    @Override
    public void gameLoop(Scanner scanner) {
        System.out.println("CONNECT FOUR RULES");
        System.out.println("     1. First player to match 25 tiles (or more) wins!");
        System.out.println("     2. Matches exists with 4 (or more) tiles in a row.");
        System.out.println("     3. Matches can be diagonal, horizontal, and vertical.");
        System.out.println("     4. Please determine who will play Player 1 and Player 2 amongst yourselves.\n");

        boolean gameRunning = true;
        while (gameRunning) {
            this.switchPlayer();
            super.display();

            try {
                // Pause for 0.5 seconds to give user the chance to look at board
                Thread.sleep(500);
            } catch (InterruptedException e) {}

            this.takeTurn(scanner);
            gameRunning = !this.isGameOver();
        }
        this.end();
    }

    @Override
    public void takeTurn(Scanner scanner) {
        // announce scores
        for (Player player : super.getPlayers()) {
            System.out.println("PLAYER " + player.getID() + " SCORE: " + player.getScore());
        }

        // announce turn
        System.out.println("\nPLAYER "
                + this.getPlayerTurn().getID()
                + ", place down a tile ["
                + this.getPlayerTurn().getDisplay() + "]");

        // get move from user + check validity
        String move = this.getMove(scanner);

        // do the move
        this.executeMove(move);

        // check for matches & update status of game
        this.checkMatch();

    }

    @Override
    public String getMove(Scanner scanner) {
        // choose a Tile and check if a valid move

        ConnectFourBoard board = getConnectFourBoard();
        if (!(board == null)) {

            String move = null;
            boolean validMove = false;
            while (!validMove) {
                move = this.choose(scanner);
                validMove = board.isValidMove(String.valueOf(move));
            }

            return move;
        }
        return null; // MAJOR ISSUE IF THIS CODE IS REACHED
    }

    @Override
    public String choose(Scanner scanner) {
        // ensures that the chosen move is a valid column number
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {
            int maxColumns = board.getColumns();
            int minColumns = 1;

            int move = super.parseNumber(scanner, "Enter a column: ", minColumns, maxColumns);
            return String.valueOf(move);
        }
        return null; // MAJOR ISSUE IF THIS CODE IS REACHED
    }

    @Override
    public void executeMove(String move) {
        List<Tile> moves = new ArrayList<>();
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {
            // return the Tile at top of the chosen column
            moves.add(board.getGameBoard().get(0).get(Integer.parseInt(move)-1));
            board.execute(moves, this.getPlayerTurn());
        }
    }

    @Override
    public void checkMatch() {
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {

            List<Tile> matches;
            do {
                // match the tiles
                matches = board.checkMatches();

                if (matches != null) {
                    // add points to the players
                    this.addPlayerPoint(matches);

                    // make the Tiles disappear
                    board.removeMatchedTiles(matches);
                }

            } while (matches != null);

        }
    }

    @Override
    public void addPlayerPoint(List<Tile> tiles) {
        for (Tile tile : tiles) {
            for (Player player : super.getPlayers()) {
                if (tile.getDisplay().equals(player.getDisplay())) {
                    player.addPoint();
                }
            }
        }
    }


    @Override
    public boolean isGameOver() {
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {

            boolean isScore25 = checkPlayerScore();
            boolean isFull = board.isFull();

            return (isScore25 || isFull);

        }

        return true; // ERROR: board is not ConnectFourBoard type
    }

    @Override
    public boolean checkPlayerScore() {
        // if a score is greater than or equal to 25 then game is over = winner found
        for (Player player: super.getPlayers()) {
            if (player.getScore() >= 25) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void end() {
        System.out.println("\n===================================================\n");
        System.out.println("CONNECT FOUR: GAME OVER");

        List<Player> winner = this.findHighestScore();
        if (winner.size() > 1) {
            System.out.println("TIE!");
            System.out.println("Score :" + winner.get(0).getScore() + "\n");
        }
        System.out.println("PLAYER " + winner.get(0).getID() + " WINS!");
        System.out.println("Score :" + winner.get(0).getScore() + "\n");

        connectFourInstance = null;
    }

    private List<Player> findHighestScore() {
        List<Player> playersWithHighestScore = new ArrayList<>();
        int highestScore = 0;

        for (Player player: super.getPlayers()) {
            if (player.getScore() > highestScore) {
                playersWithHighestScore.clear();
                playersWithHighestScore.add(player);
                highestScore = player.getScore();
            } else if (player.getScore() == highestScore) {
                playersWithHighestScore.add(player);
            }
        }

        return playersWithHighestScore;
    }



}

