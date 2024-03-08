import java.util.List;

public class SimpleDisappear implements Disappearable {

    public SimpleDisappear() {
        ;
    }

    public void disappear(List<DisappearingTile> tiles, List<List<Tile>> gameBoard) {
        // check whether it is vertical or not
        if (tiles.get(0).getRows() == tiles.get(1).getRows()) {
            //vertical
        }
        for (Tile tile : tiles){
            int x = tile.getRows();
            int y = tile.getColumns();
            // remove the board's tile  make tile's display to be null
            board[x,y];
            // swap until it is the top and make the top null or the board is null
            for () {
                ;
            }
        }
    }

}
