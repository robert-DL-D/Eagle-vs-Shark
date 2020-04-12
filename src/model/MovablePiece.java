package model;

import java.util.List;

public abstract class MovablePiece extends Piece {

    MovablePiece(int position, Enum type) {
        super(position, type);
    }

    public abstract List<int[]> getMovableCoords();

}
