// GameEngine.java
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
        return scanner.nextLine();
    }

    private int parseGameInput(int maxChoices) {
        int inputNum = -1;
        boolean isValidInput = false;

        do {
            String chosenGame = getUserInput(">> Choose a game: ");
            try {
                inputNum = Integer.parseInt(chosenGame);
                isValidInput = inputNum <= maxChoices && inputNum > 0;
                if (!isValidInput) {
                    System.out.println("ERROR: Please enter a number between 1 and " + maxChoices + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input. Please enter a valid number.");
            }
        } while (!isValidInput);

        return inputNum;
    }

    public Game chooseGame() {
        System.out.println("Game Options:");
        System.out.println("1.     Connect Four (2 Players)");
        System.out.println("2.     Memory (1 Player)");
        System.out.println("2.     Logout");
        System.out.println("3.     QUIT PROGRAM");
        int chosenGame = parseGameInput(4);

        switch (chosenGame) {
            case 1:
                this.game = new ConnectFour();
                break;
            case 2:
                this.game = new Memory();
                break;
            case 3:
                return null;
            default:
                quitProgram();
                return null;
        }

        return this.game;
    }

    public void runGame() {
        ;
    }

    public void quitProgram() {
        System.out.print("Goodbye!");
        this.scanner.close();
        System.exit(0);
    }

}
