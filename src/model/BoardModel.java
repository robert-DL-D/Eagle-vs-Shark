package model;

import java.io.Serializable;

class BoardModel implements Serializable {

    private static BoardModel instance;
    private final Square[][] SQUARE_ARRAY = new Square[BoardSize.BOARD_ROWS][BoardSize.BOARD_COLUMNS];

    private BoardModel() {
        int increment = 1;

        for (int i = 0; i < BoardSize.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardSize.BOARD_COLUMNS; j++) {
                SQUARE_ARRAY[i][j] = new Square(increment);
                increment++;
            }
        }
    }

    void addMovablePieceToSquare(MovablePiece movablePiece) {
        Square square = getSQUARE_ARRAY()[movablePiece.getRow()][movablePiece.getColumn()];
        square.addMovablePiece(movablePiece);
    }

    void addPieceToSquare(Piece piece) {
        Square square = getSQUARE_ARRAY()[piece.getRow()][piece.getColumn()];
        square.addPiece(piece);
    }

    static synchronized BoardModel getInstance() {

        if (instance == null) {
            instance = new BoardModel();
        }

        return instance;
    }

    Square[][] getSQUARE_ARRAY() {
        return SQUARE_ARRAY;
    }

}
