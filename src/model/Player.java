package model;

import java.util.ArrayList;
import java.util.List;

public class Player<T> {

    private Flag flag;

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    private List<T> pieceList;

    public Player() {
        pieceList = new ArrayList<>();
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
