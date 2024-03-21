import java.util.Scanner;

public class GameEngine {
    // ATTRIBUTES
    private final Scanner scanner;
    private final String user;
    private Game game;

    // CONSTRUCTOR
    public GameEngine(String user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    private String getUserInput(String prompt) {
        System.out.print(">> " + prompt);
        return this.getScanner().nextLine();
    }

    // GETTERS AND SETTERS
    public Scanner getScanner() { return scanner; }
    public String getUser() { return user; }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    // OPERATIONS
    public Game chooseGame() {
        System.out.println("===================================================\n");
        System.out.println("MAIN MENU:");
        System.out.println("     1. GAME: Memory (1 Player)");
        System.out.println("     2. GAME: Connect Four (2 Players)");
        System.out.println("     3. Logout");
        System.out.println("     4. Quit Program\n");

        int chosenGame = this.parseGameInput(1,4);

        switch (chosenGame) {
            case 3:
                return null;
            case 4:
                this.quitProgram();
                return null; // if correct, should never run
            default:
                System.out.println("\n===================================================\n");
                this.getGameInstance(chosenGame);
        }

        return this.getGame();
    }

    public void runGame() {
        this.getGame().gameLoop(this.getScanner());
    }

    public void quitProgram() {
        System.out.println("\n===================================================");
        System.out.println("\nGoodbye!\n");
        System.out.println("Program shutting down...!\n");
        this.getScanner().close();
        System.exit(0);
    }

    // HELPER FUNCTIONS
    private void getGameInstance(int gameNum) {
        if (this.getGame() != null) {
            this.setGame(null);
        }
        switch (gameNum) {
            case 1:
                this.setGame(Memory.getInstance());
                break;
            case 2:
                this.setGame(ConnectFour.getInstance());
                break;
        }
    }

    private int parseGameInput(int minChoices, int maxChoices) {
        int inputNum = -1;
        boolean isValidInput = false;

        do {
            String chosenGame = getUserInput("Choose an option: ");
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

}
