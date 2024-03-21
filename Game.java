import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game implements Endable {
    // ATTRIBUTES
    private Board gameBoard;
    private final List<Player> players = new ArrayList<>();

    // CONSTRUCTOR
    public Game() {}

    // GETTERS + SETTERS
    public Board getGameBoard() {
        return this.gameBoard;
    }
    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }
    public List<Player> getPlayers() {
        return players;
    }

    // OPERATION
    public void display() {
        System.out.println("\n===================================================\n");
        this.getGameBoard().displayBoard();
    }

    // ABSTRACT METHODS
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

    // HELPER FUNCTIONS FOR ALL SUB-CLASSES
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

}
