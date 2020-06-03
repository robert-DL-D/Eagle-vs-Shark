package model;

import java.util.concurrent.ThreadLocalRandom;

class JumpDecorator extends AbilityDecorator {

    private final Square[][] squareArray;

    JumpDecorator(GameModel gameModel) {
        squareArray = gameModel.getSQUARE_ARRAY();
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

        } while (squareArray[row][column].getPiece() != null || squareArray[row][column].getMovablePiece() != null);

        squareArray[movablePiece.getRow()][movablePiece.getColumn()].removeMovablePiece();

        movablePiece.setRow(row);
        movablePiece.setColumn(column);

        squareArray[row][column].setMovablePiece(movablePiece);

    }

}
