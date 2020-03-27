package model;

public abstract class Piece {
    private int position;
    private int x; // column
    private int y; //row

    Piece(int position) {
        this.position = position;

        y = position / 9;
        x = (position - (y) * 9) - 1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Square getSquare(Square[][] squares, int squareNo) {

        return squares[y][x];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
