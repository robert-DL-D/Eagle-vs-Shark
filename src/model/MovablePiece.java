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

        if (newSquare.getPiece() == null && newSquare.getMovablePiece() == null) {
            return true;
        } else {
            MovablePiece movablePieceOnCurrentSquare = currentSquare.getMovablePiece();
            Piece pieceOnNewSquare = newSquare.getPiece();

            if (pieceOnNewSquare instanceof Island) {
                return false;
            } else if (pieceOnNewSquare instanceof Flag) {
                return !((Flag) pieceOnNewSquare).getOWNER().getMOVABLEPIECE_LIST().contains(movablePieceOnCurrentSquare);
            } else {
                MovablePiece movablePieceOnNewSquare = newSquare.getMovablePiece();

                Enum movablePieceOnCurrentSquareType = movablePieceOnCurrentSquare.getType();
                Enum movablePieceOnNewSquareType = movablePieceOnNewSquare.getType();

                return (movablePieceOnCurrentSquare.getClass().getSuperclass() != movablePieceOnNewSquare.getClass().getSuperclass())
                        && ((movablePieceOnCurrentSquareType == Types.RED && movablePieceOnNewSquareType == Types.GREEN)
                        || (movablePieceOnCurrentSquareType == Types.GREEN && movablePieceOnNewSquareType == Types.BLUE)
                        || (movablePieceOnCurrentSquareType == Types.BLUE && movablePieceOnNewSquareType == Types.RED));
            }
        }
    }

    private void changePieceOnSquare(GameModel gameModel, Square currentSquare, Square
            newSquare) {

        Piece pieceOnCurrentSquare = currentSquare.getMovablePiece();

        if (newSquare.getMovablePiece() != null) {
            Piece pieceOnNewSquare = newSquare.getMovablePiece();

            if (pieceOnCurrentSquare.getClass() != pieceOnNewSquare.getClass()
                    && !(pieceOnNewSquare instanceof Flag)) {

                Player<? extends MovablePiece> player = pieceOnCurrentSquare instanceof Eagle ? gameModel.getSHARK_PLAYER() : gameModel.getEAGLE_PLAYER();

                player.getMOVABLEPIECE_LIST().remove(pieceOnNewSquare);

                newSquare.removeMovablePiece();
            }
        }

        currentSquare.removeMovablePiece();
        newSquare.addMovablePiece(this);
    }

    public List<int[]> getMovableCoords() {
        return MOVEMENT_COORD;
    }

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
