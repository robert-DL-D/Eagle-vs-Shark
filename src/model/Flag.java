package model;

public class Flag extends Piece {

    private final Player PLAYER;

    Flag(int position, Player player) {
        super(Types.FLAG, position);

        PLAYER = player;
    }

    Player getPLAYER() {
        return PLAYER;
    }
}
