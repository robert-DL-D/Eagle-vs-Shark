package model;

public class Piece {

    private int row;
    private int column;

    private Enum type;
    private boolean stunned = false;

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    Piece(int position, Enum type) {
        this.type = type;

        row = position / 9;
        column = (position - (row) * 9) - 1;
    }

    public boolean moveDirection(GameModel gameModel, Square[][] squares, int[] movementCoord) {
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
            return !((Flag) newSquare.getPiece()).getOwner().getPIECE_LIST().contains(currentSquare.getPiece());

        } else if (currentSquare.getPiece().getClass().getSuperclass() != newSquare.getPiece().getClass().getSuperclass()) {
            return true;
        }

        return false;
    }

    private void changePieceOnSquare(GameModel gameModel, Square currentSquare, Square newSquare) {

        if (!newSquare.getPIECE_LIST().isEmpty()
                && (currentSquare.getPiece().getClass() != newSquare.getPiece().getClass())
                && !(newSquare.getPiece() instanceof Flag)) {

            if (currentSquare.getPiece() instanceof Eagle) {
                gameModel.getSHARK_PLAYER().getPIECE_LIST().remove(newSquare.getPiece());
            } else {
                gameModel.getEAGLE_PLAYER().getPIECE_LIST().remove(newSquare.getPiece());

            }

            newSquare.removePiece();
        }

        currentSquare.removePiece();
        newSquare.addPiece(this);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Enum getType() {
        return type;
    }
}
