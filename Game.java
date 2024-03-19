import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game implements Endable {

    private Board gameBoard;
    private final List<Player> players = new ArrayList<>();

    private Tile chosenTile = null;

    public Game() {}

    public Board getGameBoard() {
        return this.gameBoard;
    }
    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public Tile getChosenTile() {
        return chosenTile;
    }
    public void setChosenTile(Tile chosenTile) {
        this.chosenTile = chosenTile;
    }

    public int parseNumber(Scanner scanner, String prompt, int minChoices, int maxChoices) {
        int inputNum = -1;
        boolean isValidInput = false;

        do {
            String chosenGame = getUserInput(scanner, prompt);
            try {
                inputNum = Integer.parseInt(chosenGame);
                isValidInput = inputNum <= maxChoices && inputNum >= minChoices;
                if (!isValidInput) {
                    System.out.println("ERROR: Please enter a number between " + minChoices + " and " + maxChoices + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input. Please enter a valid number.");
            }
        } while (!isValidInput);

        return inputNum;
    }

    public String getUserInput(Scanner scanner, String prompt) {
        System.out.print(">> " + prompt);
        return scanner.nextLine();
    }

    public void display() {
        System.out.println("\n===================================================\n");
        System.out.println("Rows are displayed on the LEFT side of the board (displayed vertically).");
        System.out.println("Columns are displayed on the TOP of the board (displayed horizontally).\n");

        int rows = this.getGameBoard().getGameBoard().size();
        int columns = this.getGameBoard().getGameBoard().get(0).size();

        System.out.print("   ");
        for (int colNum=1; colNum<=columns; colNum++) {
            if (colNum == columns) {
                System.out.println(" " + colNum + " ");
            } else {
                System.out.print(" " + colNum + " ");
            }
        }

        for (int rowNum=1; rowNum<=rows; rowNum++) {
            System.out.print(rowNum + "  ");

            for (int colNum=1; colNum<=columns; colNum++) {
                String display = this.getGameBoard().getGameBoard().get(rowNum-1).get(colNum-1).printDisplay();
                if (colNum == columns) {
                    System.out.println("[" + display + "]");
                } else {
                    System.out.print("[" + display + "]");
                }
            }
        }

        System.out.println();

        // System.out.println("    1  2  3  4 ");
        // System.out.println("1  [ ][ ][ ][ ]");
        // System.out.println("2  [ ][ ][ ][ ]");
        // System.out.println("3  [ ][ ][ ][ ]");
    }

    public abstract void gameLoop(Scanner scanner);
    public abstract void takeTurn(Scanner scanner);
    public abstract String getMove(Scanner scanner);
    public abstract String choose(Scanner scanner);
    public abstract void executeMove(String move);
    public abstract void checkMatch();
    public abstract void addPlayerPoint(List<Tile> tiles);
    public abstract boolean isGameOver();
    public abstract boolean checkPlayerScore();
    public abstract void end();

}
