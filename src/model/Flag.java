package model;

import java.io.Serializable;

public class Flag extends Piece implements Serializable {

    private final Player<? extends MovablePiece> PLAYER;

    public Flag(int position, Player<? extends MovablePiece> player) {
        super(Types.FLAG, position);
        PLAYER = player;
    }

    Player<? extends MovablePiece> getPLAYER() {
        return PLAYER;
    }

}
