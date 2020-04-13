package model;

public class Flag extends Piece {

    private final Player OWNER;

    Flag(int position, Player owner, Enum type) {
        super(position, type);

        OWNER = owner;
    }

    Player getOWNER() {
        return OWNER;
    }
}
