package model;

import java.util.ArrayList;
import java.util.List;

public class Player<T> {

    private List<T> pieceList;

    public Player() {
        pieceList = new ArrayList<>();
    }

    public boolean isSnake() {
        return pieceList.get(0) instanceof Shark;
    }

    public List<T> getPieceList() {
        return pieceList;
    }

    public void addPiece(T piece) {
        this.pieceList.add(piece);
    }

    public T getPiece(int index) {
        if (index >= pieceList.size() || index < 0) {
            return null;
        }

        return pieceList.get(index);
    }

    public void removePiece(T t) {
        pieceList.remove(t);
    }

}
