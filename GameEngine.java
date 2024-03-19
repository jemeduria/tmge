import java.util.Scanner;

public class GameEngine {
    private Scanner scanner;
    private String user;
    private Game game;

    public GameEngine(String user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    private String getUserInput(String prompt) {
        System.out.print(">> " + prompt);
        return this.getScanner().nextLine();
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

    public Game chooseGame() {
        System.out.println("===================================================\n");
        System.out.println("MAIN MENU:");
        System.out.println("     1. GAME: Memory (1 Player)");
        System.out.println("     2. GAME: Connect Four (2 Players)");
        System.out.println("     3. Logout");
        System.out.println("     4. Quit Program\n");

        int chosenGame = parseGameInput(1,4);

        switch (chosenGame) {
            case 1:
                System.out.println("\n===================================================\n");
                setGame(new Memory());
                break;
            case 2:
                System.out.println("\n===================================================\n");
                setGame(new ConnectFour());
                break;
            case 3:
                return null;
            default:
                quitProgram();
                return null; // if correct, should never run
        }

        return getGame();
    }

    public void runGame() {
        getGame().gameLoop(this.getScanner());
    }

    public void quitProgram() {
        System.out.println("\n===================================================");
        System.out.println("\nGoodbye!\n");
        System.out.println("Program shutting down...!\n");
        this.getScanner().close();
        System.exit(0);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
