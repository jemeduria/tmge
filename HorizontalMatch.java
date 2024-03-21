import java.util.ArrayList;
import java.util.List;

public class HorizontalMatch implements Matchable {
    public HorizontalMatch() {
        ;
    }

    public List<Tile> match(List<List<Tile>> gameBoard) {
        int rows = gameBoard.size();
        int columns = gameBoard.get(0).size();
        List<Tile> matched = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            if (row > 0) {
                if (matched.size() >= 4) {
                    return matched; // SUCCESS
                }
                matched.clear();
            }

            for (int col = 0; col < columns; col++) {
                Tile currentTile = gameBoard.get(row).get(col);
                String currentDisplay = currentTile.getDisplay();

                if (currentDisplay != null) {
                    if ((matched.isEmpty()) || (currentDisplay.equals(matched.get(0).getDisplay()))) {
                        matched.add(currentTile);
                    } else if (matched.size() <= 3) {
                        matched.clear();
                        matched.add(currentTile);
                    }
                } else {
                    if ((!matched.isEmpty()) && (matched.size() <= 3)) {
                        matched.clear();
                    }
                }
            }
        }

        if (matched.size() >= 4) {
            return matched; // SUCCESS
        }
        return null;
    }

}
