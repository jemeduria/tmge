import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private ArrayList<String> users;
    private String currentUser;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void login() {
        ;
    }

    public void createAccount() {
        ;
    }

    public static void main(String[] args) {
        // loop
            // user has choice to login() or createAccount()
            // once logged in: new GameEngine()
            // loop: Game = GameEngine.chooseGame()
            //      if (Game == null), then logout
            //      otherwise:
            //         runGame()
            // this.currentUser == null;
    }

}
