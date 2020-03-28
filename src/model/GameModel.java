package model;

import java.util.LinkedList;
import java.util.List;

public class GameModel {

    private static final int ROW = 10;
    private static final int COLUMN = 9;
    private Square[][] squares = new Square[ROW][COLUMN];

    private Player<Eagle> eaglePlayer;
    private Player<Shark> sharkPlayer;
    private List<Flag> flagList = new LinkedList<>();

    private boolean isEaglePlayerTurn;

    public GameModel() {
    }

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

    public void setSharkPlayer(Player<Shark> sharkPlayer) {
        this.sharkPlayer = sharkPlayer;
    }

    public List<Flag> getFlagList() {
        return flagList;
    }

    public boolean isEaglePlayerTurn() {
        return isEaglePlayerTurn;
    }

    public void setEaglePlayerTurn(Player<Eagle> eaglePlayerTurn) {
        this.eaglePlayer = eaglePlayerTurn;
    }

    public void setIsEaglePlayerTurn(boolean isEaglePlayerTurn) {
        this.isEaglePlayerTurn = isEaglePlayerTurn;
    }
}
