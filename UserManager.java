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


    public void login() {
        String username = "user choose";
        this.setCurrentUser(username);
    }

    public void logout() {
        this.currentUser = null;
    }

    public void createAccount() {
        ;
    }

    public static void main(String[] args) {
        UserManager tmge = new UserManager(); // change later: singleton
        Scanner scanner = new Scanner(System.in); // could change this to be created within constructor of tmge

        // LOOP: login/createAccount, chooseGame/runGame, logout/quit
        while (true) {

            // CODE THIS: login/createAccount

            // double check that user is logged in, otherwise retry login()
            if (tmge.currentUser != null) {
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
