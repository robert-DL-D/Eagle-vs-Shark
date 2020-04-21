package model;

public abstract class Piece {

    int row;
    int column;

    private Enum type;

    Piece(int position, Enum type) {
        this.type = type;

        row = position / BoardSize.BOARD_COLUMNS;
        column = (position - (row) * BoardSize.BOARD_COLUMNS) - 1;
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
