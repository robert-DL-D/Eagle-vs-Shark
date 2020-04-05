package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final Square[][] SQUARE_ARRAY = new Square[BoardSize.BOARD_ROWS][BoardSize.BOARD_COLUMNS];

    private final Player<Eagle> EAGLE_PLAYER = new Player<>();
    private final Player<Shark> SHARK_PLAYER = new Player<>();
    private final List<Flag> FLAG_LIST = new ArrayList<>();
    private boolean isEagleTurn;

    public GameModel() {
        initSquare();
    }

    private void initSquare() {

        int increment = 1;

        for (int i = 0; i < BoardSize.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardSize.BOARD_COLUMNS; j++) {
                SQUARE_ARRAY[i][j] = new Square(increment);
                increment++;
            }
        }
    }

    public Player<Eagle> getEAGLE_PLAYER() {
        return EAGLE_PLAYER;
    }

    public Player<Shark> getSHARK_PLAYER() {
        return SHARK_PLAYER;
    }

    public Square[][] getSQUARE_ARRAY() {
        return SQUARE_ARRAY;
    }

    public List<Flag> getFLAG_LIST() {
        return FLAG_LIST;
    }

    public boolean isEagleTurn() {
        return isEagleTurn;
    }

    public void setIsEagleTurn(boolean isEagleTurn) {
        this.isEagleTurn = isEagleTurn;
    }
}
