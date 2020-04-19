package model;

public abstract class Piece {

    int row;
    int column;

    private Enum type;

    Piece(int position, Enum type) {
        this.type = type;

        // TODO please remember to change this
        row = position / 9;
        column = (position - (row) * 9) - 1;
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
