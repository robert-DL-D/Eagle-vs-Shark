package model;

import java.util.ArrayList;
import java.util.List;

public class Square {

    private final int squareNo;
    private final List<Piece> pieceList = new ArrayList<>();

    public Square(int squareNo) {
        this.squareNo = squareNo;

    }

    public void addPiece(Piece piece) {
        pieceList.add(piece);
    }

    void removePiece() {
        pieceList.clear();
    }

    public Piece getPiece() {
        return pieceList.get(0);
    }

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public int getSquareNo() {
        return squareNo;
    }

}
