import java.util.List;

public class SimpleDisappear implements Disappearable {

    public SimpleDisappear() {
        ;
    }

    public void disappear(List<DisappearingTile> tiles, List<List<Tile>> gameBoard) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        // check whether it is vertical or not
        if (tiles.get(0).getRow() == tiles.get(1).getRow()) {
            //vertical
            if (tiles.get(0).getRow() > tiles.get(3).getRow()) {
                int row = tiles.get(0).getRow();
                int column = tiles.get(0).getColumn();
            }
            else{
                int row = tiles.get(3).getRow();
                int column = tiles.get(3).getColumn();
            }
            while (row >= 0 and board[row-1][column] != null){
                if (row == 0 || boarc[row-1][column] == null){
                    break;
                }
                board[row][column] = board[row-1][column];
                row -=1;
            }
            board[row][column] = null;
        }
        else {
            for (Tile tile : tiles) {
                int x = tile.getRow();
                int y = tile.getColumn();
                while (x >= 0 || board[x-1][y] != null) {
                    if (x == 0 || board[x-1][y] == null) {
                        // When y is 0 or the next tile to move is null, break the loop to avoid ArrayIndexOutOfBoundsException
                        break;
                    }
                    board[x][y] = board[x-1][y];
                    y -= 1;
                }
                if (y >= 0) {
                    board[x][y] = null;
                }
//            }
            }
=======
=======
>>>>>>> Stashed changes
        for (DisappearingTile tile: tiles) {
            tile.disappear();
>>>>>>> Stashed changes
        }
    }

}
