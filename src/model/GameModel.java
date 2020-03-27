package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static final int COLUMN = 9;
    private static final int ROW = 10;
    Player<Shark> sharkPlayer;
    private Square[][] squares = new Square[COLUMN][ROW];

    public GameModel() {
        initSquare();
        initPlayers();
        autoAddShark(71);
        autoAddShark(49);
        autoAddShark(23);

        System.out.println(sharkPlayer.getPieceList().size());
    }

    public static int getROW() {
        return ROW;
    }

    public static int getCOLUMN() {
        return COLUMN;
    }

    private void initSquare() {

        int increment = 1;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                squares[j][i] = new Square(increment, j, i);
                increment++;

            }
        }
    }

    public void initPlayers() {
        sharkPlayer = new Player<>();

    }

    public void autoAddShark(int position) {

        Shark shark = new Shark(position);

        sharkPlayer.addPiece(shark);

        Square square = squares[shark.getX()][shark.getY()];
        square.addPiece(shark);

    }

    public void addShark() {

        Shark shark = new Shark(71);

        sharkPlayer.addPiece(shark);

        Square square = squares[shark.getX()][shark.getY()];
        square.addPiece(shark);

    }

    public List<Shark> getSharkList() {
        return sharkPlayer.getPieceList();
    }

    public String moveSnake(int index, int steps) {

        final Shark piece = sharkPlayer.getPiece(index);

        try {
            String temp = piece.move(squares, steps, index);
            return temp;
        } catch (NullPointerException nullEx) {
            return "Please select a snake";
        }
    }

    public Square[][] getSquares() {
        return squares;
    }
}
