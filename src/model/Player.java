package model;

import java.util.ArrayList;
import java.util.List;

public class Player<T> {

    private final List<T> PIECE_LIST;

    Player() {
        PIECE_LIST = new ArrayList<>();
    }

    public List<T> getPIECE_LIST() {
        return PIECE_LIST;
    }

    public void addPiece(T piece) {
        PIECE_LIST.add(piece);
    }

    public T getPiece(int index) {

        return PIECE_LIST.get(index);
    }

}
