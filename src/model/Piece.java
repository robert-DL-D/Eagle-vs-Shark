package model;

public class Piece {

    private int row;
    private int column;

    private boolean stunned = false;

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    Piece(int position) {

        row = position / 9;
        column = (position - (row) * 9) - 1;
    }

    public boolean moveDirection(GameModel gameModel, Square[][] squares, String directions) {
        Square currentSquare = squares[row][column];

        switch (directions) {
            case "Up":
                if (row != 0) {
                    return moveUp(gameModel, squares, currentSquare);
                }
                break;
            case "Down":
                if (row != 9) {
                    return moveDown(gameModel, squares, currentSquare);
                }
                break;
            case "Left":
                if (column != 0) {
                    return moveLeft(gameModel, squares, currentSquare);
                }
                break;
            case "Right":
                if (column != 8) {
                    return moveRight(gameModel, squares, currentSquare);
                }
                break;
        }

        return false;
    }

    private boolean moveUp(GameModel gameModel, Square[][] squares, Square currentSquare) {

        Square newSquare = squares[row - 1][column];

        if (validSquare(currentSquare, newSquare)) {

            row -= 1;
            changePieceOnSquare(gameModel, currentSquare, newSquare);

            return true;
        }

        return false;
    }

    private boolean moveDown(GameModel gameModel, Square[][] squares, Square currentSquare) {

        Square newSquare = squares[row + 1][column];

        if (validSquare(currentSquare, newSquare)) {

            row += 1;
            changePieceOnSquare(gameModel, currentSquare, newSquare);

            return true;
        }

        return false;
    }

    private boolean moveLeft(GameModel gameModel, Square[][] squares, Square currentSquare) {

        Square newSquare = squares[row][column - 1];

        if (validSquare(currentSquare, newSquare)) {

            column -= 1;
            changePieceOnSquare(gameModel, currentSquare, newSquare);

            return true;
        }

        return false;
    }

    private boolean moveRight(GameModel gameModel, Square[][] squares, Square currentSquare) {

        Square newSquare = squares[row][column + 1];

        if (validSquare(currentSquare, newSquare)) {

            column += 1;
            changePieceOnSquare(gameModel, currentSquare, newSquare);

            return true;
        }

        return false;
    }

    private boolean validSquare(Square currentSquare, Square newSquare) {

        if (newSquare.getPieceList().isEmpty()) {
            return true;

        } else if (newSquare.getPiece() instanceof Flag) {
            return !((Flag) newSquare.getPiece()).getOwner().getPieceList().contains(currentSquare.getPiece());

        } else if (currentSquare.getPiece().getClass() != newSquare.getPiece().getClass()) {
            return true;
        }

        return false;
    }

    private void changePieceOnSquare(GameModel gameModel, Square currentSquare, Square newSquare) {

        if (!newSquare.getPieceList().isEmpty()
                && (currentSquare.getPiece().getClass() != newSquare.getPiece().getClass())
                && !(newSquare.getPiece() instanceof Flag)) {

            if (currentSquare.getPiece() instanceof Eagle) {
                gameModel.getSharkPlayer().getPieceList().remove(newSquare.getPiece());
            } else {
                gameModel.getEaglePlayer().getPieceList().remove(newSquare.getPiece());

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

}
