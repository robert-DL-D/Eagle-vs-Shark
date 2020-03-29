package model;

import java.util.LinkedList;
import java.util.List;

public class GameModel {

    private static final int ROW = 10;
    private static final int COLUMN = 9;
    private final Square[][] squares = new Square[ROW][COLUMN];

    private Player<Eagle> eaglePlayer;
    private Player<Shark> sharkPlayer;
    private final List<Flag> flagList = new LinkedList<>();
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

    public void setEaglePlayer(Player<Eagle> eaglePlayer) {
        this.eaglePlayer = eaglePlayer;
    }

    public void setSharkPlayer(Player<Shark> sharkPlayer) {
        this.sharkPlayer = sharkPlayer;
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
