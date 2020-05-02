package model;

public abstract class Piece {

    int row;
    int column;

    Enum type;

    Piece(Enum type, int position) {
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
