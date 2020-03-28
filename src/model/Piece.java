package model;

public abstract class Piece {
    private int position;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private int row;
    private int column;

    Piece(int position) {
        this.position = position;

        row = position / 9;
        column = (position - (row) * 9) - 1;
    }

    // TODO add check for moving to same team flag
    public boolean moveDirection(GameModel gameModel, Square[][] squares, int steps, int index) {
        Square currSquare = getSquare(squares, getPosition());

        int pieceRow = currSquare.getRow();
        int pieceColumn = currSquare.getColumn();
        Square newSquare = null;

        if (steps == UP) {
            if (moveUp(gameModel, squares, pieceRow, pieceColumn, currSquare, newSquare)) {
                return true;
            }
        } else if (steps == DOWN) {
            if (moveDown(gameModel, squares, pieceRow, pieceColumn, currSquare, newSquare)) {
                return true;
            }

        } else if (steps == LEFT) {
            if (moveLeft(gameModel, squares, pieceRow, pieceColumn, currSquare, newSquare)) {
                return true;
            }

        } else if (steps == RIGHT) {
            if (moveRight(gameModel, squares, pieceRow, pieceColumn, currSquare, newSquare)) {
                return true;
            }
        }

        return false;

    }

    private boolean moveUp(GameModel gameModel, Square[][] squares, int pieceRow, int pieceColumn, Square currSquare, Square newSquare) {

        newSquare = squares[pieceRow - 1][pieceColumn];

        if (pieceRow == 0
                || (!newSquare.getPieceList().isEmpty()
                && currSquare.getPiece().getClass() == newSquare.getPiece().getClass())) {

            return false;
        } else {
            setRow(getRow() - 1);

            return move(gameModel, squares, currSquare, newSquare);
        }
    }

    private boolean moveDown(GameModel gameModel, Square[][] squares, int pieceRow, int pieceColumn, Square currSquare, Square newSquare) {

        newSquare = squares[pieceRow + 1][pieceColumn];

        if (pieceRow == 9
                || (!newSquare.getPieceList().isEmpty()
                && currSquare.getPiece().getClass() == newSquare.getPiece().getClass())) {

            return false;
        } else {
            setRow(getRow() + 1);

            return move(gameModel, squares, currSquare, newSquare);
        }
    }

    private boolean moveLeft(GameModel gameModel, Square[][] squares, int pieceRow, int pieceColumn, Square currSquare, Square newSquare) {

        newSquare = squares[pieceRow][pieceColumn - 1];

        if (pieceColumn == 0
                || (!newSquare.getPieceList().isEmpty()
                && currSquare.getPiece().getClass() == newSquare.getPiece().getClass())) {
            return false;
        } else {

            setColumn(getColumn() - 1);

            return move(gameModel, squares, currSquare, newSquare);
        }
    }

    private boolean moveRight(GameModel gameModel, Square[][] squares, int pieceRow, int pieceColumn, Square currSquare, Square newSquare) {

        newSquare = squares[pieceRow][pieceColumn + 1];

        if (pieceColumn == 8
                || (!newSquare.getPieceList().isEmpty()
                && currSquare.getPiece().getClass() == newSquare.getPiece().getClass())) {
            return false;
        } else {
            setColumn(getColumn() + 1);

            return move(gameModel, squares, currSquare, newSquare);
        }

    }

    private boolean move(GameModel gameModel, Square[][] squares, Square currSquare, Square
            newSquare) {

        if (!newSquare.getPieceList().isEmpty()
                && (currSquare.getPiece().getClass() != newSquare.getPiece().getClass())
                && !(newSquare.getPiece() instanceof Flag)) {

            if (currSquare.getPiece() instanceof Eagle) {
                gameModel.getSharkPlayer().getPieceList().remove(newSquare.getPiece());
            } else {
                gameModel.getEaglePlayer().getPieceList().remove(newSquare.getPiece());

            }

            newSquare.removePiece();

        }

        currSquare.removePiece();
        setPosition(newSquare.getSquareNo());
        newSquare.addPiece(this);

        return true;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Square getSquare(Square[][] squares, int squareNo) {
        return squares[row][column];
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
