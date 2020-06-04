package model;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class JumpDecorator extends AbilityDecorator {

    private final Square[][] SQUARE_ARRAY;
    private final boolean SUPER_ABILITY;

    JumpDecorator(GameModel gameModel, boolean superAbility) {
        SQUARE_ARRAY = gameModel.getSQUARE_ARRAY();
        SUPER_ABILITY = superAbility;
    }

    @Override
    void useAbility(MovablePiece movablePiece) {
        jumpPiece(movablePiece);

        if (SUPER_ABILITY) {
            List<MovablePiece> enemyPieceList = new LinkedList<>();

            for (Square[] squares : SQUARE_ARRAY) {
                for (Square square : squares) {
                    if (square.getMovablePiece() != null) {
                        if (square.getMovablePiece().getClass().getSuperclass() != movablePiece.getClass().getSuperclass()) {
                            enemyPieceList.add(square.getMovablePiece());
                        }
                    }
                }
            }

            jumpPiece(enemyPieceList.get(ThreadLocalRandom.current().nextInt(enemyPieceList.size())));
        }
    }

    private void jumpPiece(MovablePiece movablePiece) {

        ThreadLocalRandom current = ThreadLocalRandom.current();

        int row;
        int column;

        do {
            row = movablePiece instanceof Eagle ? current.nextInt(BoardConfig.BOARD_ROWS / 2 - 1) : current.nextInt(BoardConfig.BOARD_ROWS / 2 - 1, BoardConfig.BOARD_ROWS);
            column = current.nextInt(BoardConfig.BOARD_COLUMNS);

        } while (SQUARE_ARRAY[row][column].getPiece() != null || SQUARE_ARRAY[row][column].getMovablePiece() != null);

        SQUARE_ARRAY[movablePiece.getRow()][movablePiece.getColumn()].removeMovablePiece();

        movablePiece.setRow(row);
        movablePiece.setColumn(column);

        SQUARE_ARRAY[row][column].setMovablePiece(movablePiece);

    }

}
