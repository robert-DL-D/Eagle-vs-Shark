package model;

import java.util.ArrayList;
import java.util.List;

public class Player<T> {

    private final List<T> pieceList;

    public Player() {
        pieceList = new ArrayList<>();
    }

    public List<T> getPieceList() {
        return pieceList;
    }

    public void addPiece(T piece) {
        pieceList.add(piece);
    }

    public T getPiece(int index) {

        return pieceList.get(index);
    }

}
