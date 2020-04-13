package model;

import java.util.ArrayList;
import java.util.List;

public class Square {

    private final int SQUARE_NUMBER;
    private final List<Piece> PIECE_LIST = new ArrayList<>();

    Square(int squareNumber) {
        SQUARE_NUMBER = squareNumber;
    }

    void addPiece(Piece piece) {
        PIECE_LIST.add(piece);
    }

    void removePiece() {
        PIECE_LIST.clear();
    }

    Piece getPiece() {
        return PIECE_LIST.get(0);
    }

    public List<Piece> getPIECE_LIST() {
        return PIECE_LIST;
    }

    public int getSQUARE_NUMBER() {
        return SQUARE_NUMBER;
    }

}
