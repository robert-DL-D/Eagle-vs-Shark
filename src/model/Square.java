package model;

import java.util.ArrayList;
import java.util.List;

public class Square {

    private int squareNo;
    private int column;
    private int row;
    private List<Piece> pieceList = new ArrayList<>();

    public Square(int squareNo, int column, int row) {
        this.squareNo = squareNo;
        this.row = row;
        this.column = column;
    }

    public void addPiece(Piece piece) {
        pieceList.add(piece);
    }

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public int getSquareNo() {
        return squareNo;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
