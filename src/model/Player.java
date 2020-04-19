package model;

import java.util.ArrayList;
import java.util.List;

public class Player<T extends MovablePiece> {

    private final List<T> MOVABLEPIECE_LIST = new ArrayList<>();

    public List<T> getMOVABLEPIECE_LIST() {
        return MOVABLEPIECE_LIST;
    }

    void addMovablePiece(T movablePiece) {
        MOVABLEPIECE_LIST.add(movablePiece);
    }

    public T getPiece(int index) {

        return MOVABLEPIECE_LIST.get(index);
    }

}
