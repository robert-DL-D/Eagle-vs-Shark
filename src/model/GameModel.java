package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final int ROW = 10;
    private static final int COLUMN = 9;
    private final Square[][] squares = new Square[ROW][COLUMN];

    private final List<Flag> flagList = new ArrayList<>();
    private Player<Eagle> eaglePlayer = new Player<>();
    private Player<Shark> sharkPlayer = new Player<>();
    private boolean isEagleTurn;

    public Player<Eagle> getEaglePlayer() {
        return eaglePlayer;
    }

    public Player<Shark> getSharkPlayer() {
        return sharkPlayer;
    }

    public static int getROW() {
        return ROW;
    }

    public static int getCOLUMN() {
        return COLUMN;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Flag> getFlagList() {
        return flagList;
    }

    public boolean isEagleTurn() {
        return isEagleTurn;
    }

    public void setIsEagleTurn(boolean isEagleTurn) {
        this.isEagleTurn = isEagleTurn;
    }
}
