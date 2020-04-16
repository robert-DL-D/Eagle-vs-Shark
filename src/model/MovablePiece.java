package model;

import java.util.LinkedList;
import java.util.List;

public abstract class MovablePiece
        extends Piece {

    final List<int[]> MOVEMENT_COORD = new LinkedList<>();
    Enum ability;

    private boolean stunned;

    MovablePiece(int position, Enum type) {
        super(position, type);
    }

    public boolean updatePieceRowColumn(GameModel gameModel, Square[][] squares, int[] movementCoord) {

        Square currentSquare = squares[row][column];
        Square newSquare = squares[row + movementCoord[0]][column + movementCoord[1]];

        if (checkValidNewSquare(currentSquare, newSquare)) {

            row += movementCoord[0];
            column += movementCoord[1];
            changePieceOnSquare(gameModel, currentSquare, newSquare);

            return true;
        }

        return false;
    }

    private boolean checkValidNewSquare(Square currentSquare, Square newSquare) {

        if (newSquare.getPIECE_LIST().isEmpty()) {
            return true;

        } else if (newSquare.getPiece() instanceof Flag) {
            return !((Flag) newSquare.getPiece()).getOWNER().getPIECE_LIST().contains(currentSquare.getPiece());

        } else if (currentSquare.getPiece().getClass().getSuperclass() != newSquare.getPiece().getClass().getSuperclass()) {
            return true;
        }

        return false;
    }

    private void changePieceOnSquare(GameModel gameModel, Square currentSquare, Square newSquare) {

        Piece pieceOnCurrentSquare = currentSquare.getPiece();

        if (!newSquare.getPIECE_LIST().isEmpty()) {
            Piece pieceOnNewSquare = newSquare.getPiece();

            if (pieceOnCurrentSquare.getClass() != pieceOnNewSquare.getClass()
                    && !(pieceOnNewSquare instanceof Flag)) {

                Player<? extends MovablePiece> player;

                player = pieceOnCurrentSquare instanceof Eagle ? gameModel.getSHARK_PLAYER() : gameModel.getEAGLE_PLAYER();

                player.getPIECE_LIST().remove(pieceOnNewSquare);

                newSquare.removePiece();
            }
        }

        currentSquare.removePiece();
        newSquare.addPiece(this);
    }

    public List<int[]> getMovableCoords() {
        return MOVEMENT_COORD;
    }

    ;

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public Enum getAbility() {
        return ability;
    }
}
