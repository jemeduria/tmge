import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memory extends Game {

    private final int minNumTile = 0;
    private final int maxNumTile = 9;
    private final List<Integer> numOptions = new ArrayList<>();
    public Memory() {
        super();
        for (int i=this.minNumTile; i<=this.maxNumTile; i++) {
            this.numOptions.add(i);
        }
        super.setGameBoard(new MemoryBoard(this.numOptions));
    }

    @Override
    public String getMove(Scanner scanner) {
    	String move;
    	do {
    		System.out.println("Enter the position of your choice in this format <Row Number><Space><Column Number>");
    		move = scanner.nextLine();
    	}
    	while(!this.getGameBoard().isValidMove(move));
        return move;
    }

    @Override
    public void gameLoop(Scanner scanner) {

        boolean gameIsOver = false;
        while (!gameIsOver) {
            ;
            if (isGameOver()) {
                gameIsOver = true;
            }
        }

    }

    @Override
    public boolean isGameOver() {
    	for(int i = 0; i < this.getMemoryBoard().getRows(); i++) {
    		for(int j = 0; j < this.getMemoryBoard().getColumns(); j++) {
    			
    		}
    	}
        return true;
    }

    @Override
    public void takeTurn(Scanner scanner) {}

    @Override
    public String choose(Scanner scanner) {
        return "";
    }

    @Override
    public void executeMove(String move) {
        ;
    }

    @Override
    public void checkMatch() {}

    private MemoryBoard getMemoryBoard() {
        if (super.getGameBoard() instanceof MemoryBoard) {
            return (MemoryBoard) super.getGameBoard();
        }
        return null;
    }

}
