package model;

public abstract class Piece {

    int row;
    int column;

    private Enum type;

    Piece(int position, Enum type) {
        this.type = type;

        row = (position - 1) / BoardSize.BOARD_COLUMNS;
        column = (position - 1) % BoardSize.BOARD_COLUMNS;
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
