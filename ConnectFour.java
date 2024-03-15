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
        this.playerTurn = this.players.get(1);
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<String> getTileOptions() {
        return tileOptions;
    }


    private ConnectFourBoard getConnectFourBoard() {
        if (super.getGameBoard() instanceof ConnectFourBoard) {
            return (ConnectFourBoard) super.getGameBoard();
        }
        return null;
    }

    private void switchPlayer() {
        int currentPlayerID = (this.getPlayerTurn().getID() % this.getPlayers().size()) + 1;

        for (Player player : this.getPlayers()) {
            if (player.getID() == currentPlayerID) {
                this.setPlayerTurn(player);
                break;
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
        List<Tile> moves = new ArrayList<Tile>();
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {
            // return the Tile at top of the chosen column
//            moves.add(board.getGameBoard().getFirst().get(Integer.parseInt(move)));
            moves.add(board.getGameBoard().get(0).get(Integer.parseInt(move)));
            board.execute(moves, this.getPlayerTurn());
        }
    }

    @Override
    public void checkMatch() {
        ConnectFourBoard board = this.getConnectFourBoard();
        if (!(board == null)) {

            List<Tile> matches = board.checkMatches();
            if (!matches.isEmpty()) {

                // addPlayerPoint();
                // board.removeMatchedTiles(matchedTiles);

            }
        }
    }

    @Override
    public void addPlayerPoint(List<Tile> tiles) {
        //Add up the matched tiles corresspoding to the player's display.
        //if it is over 25, Game is Over.
        for (Tile tile : tiles) {
            for (Player player : this.getPlayers()) {
                if (tile.getDisplay().equals(player.getDisplay())) {
                    player.addPoint();
//                    if (player.getScore() >= 25) {
//                        System.out.println("Player " + player.getID() + " wins with 25 or more points!");
//                        //this.isGameOver = true;
//                        // Somehow END THE GAME
//                        //Then What?
                    }
                }
            return;
            }
        }
    }

    @Override
    public boolean isGameOver() {
        // gameBoard.isFull();
            //is this implemented? -> No -> Create a abstract in Game.
        // checkPlayerScores();
            //check player score whether it is over 25, then  return true,
        //check whole isFull, checkPlayerSocres and if both true, return true.
        return true;
    }
}
