package model;

import java.util.List;

public abstract class Movable extends Piece {

    Movable(int position, Enum type) {
        super(position, type);
    }

    public abstract List<int[]> getMovableCoords();

}
