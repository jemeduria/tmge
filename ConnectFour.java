import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectFour extends Game {

    private Player playerTurn = null;
    private List<Player> players = new ArrayList<>();
    private final List<String> tileOptions = new ArrayList<>();

    public ConnectFour() {
        super();
        // fix?? : duplicate code in ConnectFourTile.java
        // initialize tile options
        this.tileOptions.add("X");
        this.tileOptions.add("O");

        // initialize board
        super.setGameBoard(new ConnectFourBoard());

        // initialize players
        this.players.add(new Player(1, this.tileOptions.get(0)));
        this.players.add(new Player(2, this.tileOptions.get(1)));
        this.playerTurn =  this.players.get(1);
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<String> getTileOptions() {
        return tileOptions;
    }

    public String getMove(Scanner scanner) {
        if (super.getGameBoard() instanceof ConnectFourBoard board) {
            int maxColumns = board.getColumns();
            int minColumns = 1;

            // ********** LATER: move checking whether column is free to gameBoard.isValidMove()
            while (true) {
                // change user input into int
                int move = super.parseNumber(scanner, "Enter a column: ", minColumns, maxColumns);

                // check if column is free (there is at least one available space at the top in that column)
                if (board.getGameBoard().get(0).get(move-1).getDisplay() == null) {
                    return String.valueOf(move);
                } else {
                    System.out.println("ERROR: Column is full. Choose a different column.");
                }
            }
        }
        return "-1";
    }

    private void switchPlayer() {
        int currentPlayer = this.getPlayerTurn().getID() + 1;
        if (currentPlayer > this.getPlayers().size()) {
            currentPlayer = 1;
        }

        for (Player player : this.getPlayers()) {
            if (player.getID() == currentPlayer) {
                this.setPlayerTurn(player);
            }
        }
    }

    public void gameLoop(Scanner scanner) {
        System.out.println("Please determine who will play Player 1 and Player 2.");

        boolean gameRunning = true;
        while (gameRunning) {
            this.switchPlayer();
            this.takeTurn(scanner);
            gameRunning = !this.isGameOver();
        }
    }

    public boolean isGameOver() {
        // gameBoard.isFull();
        // checkPlayerScores();
        return true;
    }

    public void takeTurn(Scanner scanner) {
        System.out.println("    Player "
                + this.getPlayerTurn().getID()
                + ", place down a "
                + this.getPlayerTurn().getDisplay());
        this.getMove(scanner);
        // super.getGameBoard().isValidMove()
        // this.checkMatch()

    }

    public void chooseTile() {}

    public void checkMatch() {}

    public boolean isMatch(List<Tile> tiles) {
        return true;
    }

    public void chooseTile(List<Tile> tiles) {}
}
