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

    @Override
    public String getMove(Scanner scanner) {
        // choose a Tile and check if a valid move

        ConnectFourBoard board = getConnectFourBoard();
        if (!(board == null)) {

            String move = null;
            boolean validMove = false;
            while (!validMove) {
                move = this.chooseTile(scanner);
                validMove = board.isValidMove(String.valueOf(move));
            }

            return move;
        }
        return null; // MAJOR ISSUE IF THIS CODE IS REACHED
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

    @Override
    public void gameLoop(Scanner scanner) {
        System.out.println("Please determine who will play Player 1 and Player 2.");

        boolean gameRunning = true;
        while (gameRunning) {
            this.switchPlayer();
            this.takeTurn(scanner);
            gameRunning = !this.isGameOver();
        }
    }

    @Override
    public boolean isGameOver() {
        // gameBoard.isFull();
        // checkPlayerScores();
        return true;
    }

    @Override
    public void takeTurn(Scanner scanner) {
        // announce turn
        System.out.println("    Player "
                + this.getPlayerTurn().getID()
                + ", place down a "
                + this.getPlayerTurn().getDisplay());

        // get move from user + check validity
        String move = this.getMove(scanner);

        // do the move
        this.executeMove(move);

        // check for matches & update status of game
        // this.checkMatch()

    }

    @Override
    public String chooseTile(Scanner scanner) {
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
    public void checkMatch() {
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {
            board.checkMatches();
        }
    }

    @Override
    public boolean isMatch(List<List<Tile>> gameBoard) {
        return true;
    }

    private ConnectFourBoard getConnectFourBoard() {
        if (super.getGameBoard() instanceof ConnectFourBoard) {
            return (ConnectFourBoard) super.getGameBoard();
        }
        return null;
    }

    @Override
    public void executeMove(String move) {
        List<Tile> moves = new ArrayList<Tile>();
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {
            // return the til at top of the chosen column
            moves.add(board.getGameBoard().getFirst().get(Integer.parseInt(move)));
            board.execute(moves, this.getPlayerTurn());
        }
    }
}
