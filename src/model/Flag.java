package model;

public class Flag extends Piece {

    private final Player owner;

    public Flag(int position, Player owner) {
        super(position);

        this.owner = owner;
    }

    Player getOwner() {
        return owner;
    }
}
