package model;

import java.util.concurrent.ThreadLocalRandom;

class JumpDecorator extends AbilityDecorator {

    private final Square[][] SQUARE_ARRAY;

    JumpDecorator(GameModel gameModel) {
        SQUARE_ARRAY = gameModel.getSQUARE_ARRAY();
    }

    @Override
    void useAbility(MovablePiece movablePiece) {
        jumpPiece(movablePiece);
    }

    private void jumpPiece(MovablePiece movablePiece) {

        ThreadLocalRandom current = ThreadLocalRandom.current();

        int row;
        int column;

        do {
            row = current.nextInt(BoardConfig.BOARD_ROWS / 2 - 1);
            column = current.nextInt(BoardConfig.BOARD_COLUMNS);

        } while (SQUARE_ARRAY[row][column].getPiece() != null || SQUARE_ARRAY[row][column].getMovablePiece() != null);

        SQUARE_ARRAY[movablePiece.getRow()][movablePiece.getColumn()].removeMovablePiece();

        movablePiece.setRow(row);
        movablePiece.setColumn(column);

        SQUARE_ARRAY[row][column].setMovablePiece(movablePiece);

    }

}
