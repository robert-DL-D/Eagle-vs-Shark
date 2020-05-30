package model;

import java.io.Serializable;

public class Square implements Serializable {

    private final int SQUARE_NUMBER;
    private Piece piece;
    private MovablePiece movablePiece;

    Square(int squareNumber) {
        SQUARE_NUMBER = squareNumber;
    }

    void addMovablePiece(MovablePiece movablePiece) {
        this.movablePiece = movablePiece;
    }

    void addPiece(Piece piece) {
        this.piece = piece;
    }

    void removeMovablePiece() {
        movablePiece = null;
    }

    public MovablePiece getMovablePiece() {
        return movablePiece;
    }

    Piece getPiece() {
        return piece;
    }

    public int getSQUARE_NUMBER() {
        return SQUARE_NUMBER;
    }
}
