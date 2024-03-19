import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private final List<String> users;
    private String currentUser;

    public UserManager() {
        this.users = new ArrayList<>();
        this.currentUser = null;
    }

    public String getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    public List<String> getUsers() {
        return this.users;
    }


    public void login(Scanner scanner) {
        String username = this.getUserInput(scanner, "Enter a username: ");

        if (!this.getUsers().contains(username)) {
            this.getUsers().add(username);
        }
        this.setCurrentUser(username);

        System.out.println("Hello " + username + "!\n");
    }

    public void logout() {
        System.out.println("\n===================================================\n");
        System.out.println("Goodbye " + this.getCurrentUser() + "!");
        this.setCurrentUser(null);
        System.out.println("You have been LOGGED OUT! Enter a username to login again.");
        System.out.println("\n===================================================\n");
    }

    public String getUserInput(Scanner scanner, String prompt) {
        System.out.print(">> " + prompt);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        UserManager tmge = new UserManager(); // change later: singleton
        Scanner scanner = new Scanner(System.in); // could change this to be created within constructor of tmge
        System.out.println();

        // LOOP: login, chooseGame/runGame, logout/quit
        while (true) {
            // login (also creates an account if not already made)
            tmge.login(scanner);

            // double check that user is logged in, otherwise retry login()
            if (tmge.getCurrentUser() != null) {
                boolean isLoggedIn = true;

                // new GameEngine with every login
                GameEngine gameEngine = new GameEngine(tmge.getCurrentUser(), scanner);

                while (isLoggedIn) {
                    // let user choose a game
                    Game game = gameEngine.chooseGame();
                    if (game != null) {
                        // run the game
                        gameEngine.runGame();
                    } else {
                        // user wants to log out (or quit again possibly: gameEngine.quitProgram())
                        isLoggedIn = false;
                    }
                }

                tmge.logout();
            }

        }

    }

}
