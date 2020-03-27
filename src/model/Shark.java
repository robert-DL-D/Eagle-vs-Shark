package model;

public class Shark extends Piece {

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public Shark(int position) {
        super(position);

    }

    public String move(Square[][] squares, int steps, int index) {
        Square currSquare = getSquare(squares, getPosition());

        if (steps == UP) {
            if (moveUp(squares, currSquare)) {
                return null;
            }
        } /*else if (steps == DOWN) {
            if (moveDown(squares, currSquare)) {
                return null;
            }

        } else if (steps == LEFT) {
            if (moveLeft(squares, currSquare)) {
                return null;
            }

        } else if (steps == RIGHT) {
            if (moveRight(squares, currSquare)) {
                return null;
            }
        }*/

        return "";
    }

    private boolean moveUp(Square[][] squares, Square currSquare) {
        int headRow = currSquare.getRow();
        int headCol = currSquare.getColumn();
        Square newHeadSquare;

        /*if (headRow + 1 > 9) {
            return false;
        } else {*/
        newHeadSquare = squares[headCol][headRow + 1];
        setY(getY() - 1);
        return moveShark(squares, currSquare, newHeadSquare);
        //}
    }

    /*private boolean moveDown(Square[][] squares, Square currSquare) {
        int headRow = currSquare.getRow();
        int headCol = currSquare.getColumn();
        Square tail = getSquare(squares, getConnectedPosition());
        Square newHeadSquare;
        int initialLength = getLength();

        if (headRow - 1 < 0) {
            throw new SnakeMoveOutOfBoundsException();
        } else {
            newHeadSquare = squares[headCol][headRow - 1];
            return moveSnake(squares, currSquare, tail, newHeadSquare, initialLength);
        }
    }

    private boolean moveRight(Square[][] squares, Square currSquare) throws SnakeMoveOutOfBoundsException, SnakeMoveToGuardedSquareException {
        int headRow = currSquare.getRow();
        int headCol = currSquare.getColumn();
        Square tail = getSquare(squares, getConnectedPosition());
        Square newHeadSquare;
        int initialLength = getLength();

        if ((headCol + 1 > 9)) {
            throw new SnakeMoveOutOfBoundsException();
        } else {
            newHeadSquare = squares[headCol + 1][headRow];
            return moveSnake(squares, currSquare, tail, newHeadSquare, initialLength);
        }
    }

    private boolean moveLeft(Square[][] squares, Square currSquare) throws SnakeMoveOutOfBoundsException, SnakeMoveToGuardedSquareException {
        int headCol = currSquare.getColumn();
        int headRow = currSquare.getRow();
        Square tail = getSquare(squares, getConnectedPosition());
        Square newHeadSquare = squares[headCol - 1][headRow];
        int initialLength = getLength();

        if ((headCol - 1 < 0)) {
            throw new SnakeMoveOutOfBoundsException();
        } else {

            return moveSnake(squares, currSquare, tail, newHeadSquare, initialLength);
        }
    }*/

    private boolean moveShark(Square[][] squares, Square currSquare, Square newSquare) {

        //move head up
        //currSquare.removePiece(this);
        setPosition(newSquare.getSquareNo());
        //newHeadSquare = getSquare(squares, getPosition());
        //newHeadSquare.addPiece(this);

        return true;

    }
}
