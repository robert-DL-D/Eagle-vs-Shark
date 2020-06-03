package model;

import java.io.Serializable;

class BoardModel implements Serializable {

    private static BoardModel instance;
    private static Square[][] SQUARE_ARRAY;

    void addMovablePieceToSquare(MovablePiece movablePiece) {
        Square square = getSQUARE_ARRAY()[movablePiece.getRow()][movablePiece.getColumn()];
        square.setMovablePiece(movablePiece);
    }

    void addPieceToSquare(Piece piece) {
        Square square = getSQUARE_ARRAY()[piece.getRow()][piece.getColumn()];
        square.setPiece(piece);
    }

    static synchronized BoardModel getInstance() {
        initSquare();

        if (instance == null) {
            instance = new BoardModel();
        }

        return instance;
    }

    private static void initSquare() {
        SQUARE_ARRAY = new Square[BoardConfig.BOARD_ROWS][BoardConfig.BOARD_COLUMNS];

        int increment = 1;

        for (int i = 0; i < BoardConfig.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardConfig.BOARD_COLUMNS; j++) {
                SQUARE_ARRAY[i][j] = new Square(increment);
                increment++;
            }
        }
    }

    Square[][] getSQUARE_ARRAY() {
        return SQUARE_ARRAY;
    }

}
