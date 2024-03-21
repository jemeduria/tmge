import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    // ATTRIBUTES
    private static UserManager userManagerInstance = null;
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> users = new ArrayList<>();
    private String currentUser = null;

    // CONSTRUCTOR + SINGLETON PROVIDER
    private UserManager() {}
    public static synchronized UserManager getInstance() {
        if (userManagerInstance == null)
            userManagerInstance = new UserManager();
        return userManagerInstance;
    }

    // GETTERS AND SETTERS
    public Scanner getScanner() { return this.scanner; }
    public List<String> getUsers() {
        return this.users;
    }
    public String getCurrentUser() {
        return this.currentUser;
    }
    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    // OPERATIONS
    private void login(Scanner scanner) {
        String username = this.getUserInput(scanner, "Enter a username: ");

        if (!this.getUsers().contains(username)) {
            this.getUsers().add(username);
        }
        this.setCurrentUser(username);

        System.out.println("Hello " + username + "!\n");
    }

    private void logout() {
        System.out.println("\n===================================================\n");
        System.out.println("Goodbye " + this.getCurrentUser() + "!");
        this.setCurrentUser(null);
        System.out.println("You have been LOGGED OUT! Enter a username to login again.");
        System.out.println("\n===================================================\n");
    }

    // HELPER METHOD
    private String getUserInput(Scanner scanner, String prompt) {
        System.out.print(">> " + prompt);
        return scanner.nextLine();
    }

    // MAIN
    public static void main(String[] args) {
        UserManager tmge = getInstance();
        System.out.println();

        while (true) {
            tmge.login(tmge.getScanner());

            if (tmge.getCurrentUser() != null) {
                boolean isLoggedIn = true;

                GameEngine gameEngine = new GameEngine(tmge.getCurrentUser(), tmge.getScanner());

                while (isLoggedIn) {
                    Game game = gameEngine.chooseGame();
                    if (game != null) {
                        gameEngine.runGame();
                    } else {
                        isLoggedIn = false;
                    }
                }

                tmge.logout();
            }
        }
    }

}
