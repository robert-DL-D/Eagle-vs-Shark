package model;

class MoveCommand
        extends AbstractCommand {

    private final int PREVIOUS_ROW;
    private final int PREVIOUS_COLUMN;
    private final MovablePiece MOVED_PIECE;
    private final MovablePiece CAPTURED_PIECE;
    private final int CAPTURED_PIECE_INDEX;
    private final Player<? extends MovablePiece> PLAYER;

    MoveCommand(int row, int column, MovablePiece movablePiece,
                MovablePiece newSquareMovablePiece, int index, Player<? extends MovablePiece> currentPlayer) {
        PREVIOUS_ROW = row;
        PREVIOUS_COLUMN = column;
        MOVED_PIECE = movablePiece;
        CAPTURED_PIECE = newSquareMovablePiece;
        CAPTURED_PIECE_INDEX = index;
        PLAYER = currentPlayer;
    }

    int getPREVIOUS_ROW() {
        return PREVIOUS_ROW;
    }

    int getPREVIOUS_COLUMN() {
        return PREVIOUS_COLUMN;
    }

    MovablePiece getMOVED_PIECE() {
        return MOVED_PIECE;
    }

    MovablePiece getCAPTURED_PIECE() {
        return CAPTURED_PIECE;
    }

    int getCAPTURED_PIECE_INDEX() {
        return CAPTURED_PIECE_INDEX;
    }

    Player<? extends MovablePiece> getPLAYER() {
        return PLAYER;
    }
}
