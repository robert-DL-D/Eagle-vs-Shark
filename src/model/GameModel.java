package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {

    private final Square[][] SQUARE_ARRAY = new Square[BoardSize.BOARD_ROWS][BoardSize.BOARD_COLUMNS];
    private final Player<Eagle> EAGLE_PLAYER = new Player<>();
    private final Player<Shark> SHARK_PLAYER = new Player<>();
    private final List<Flag> FLAG_LIST = new ArrayList<>();

    private boolean isEagleTurn;

    public GameModel() {
        initializeSquare();

        addEagle(38, Types.RED);
        addEagle(41, Types.RED);
        addEagle(44, Types.GREEN);
        addEagle(77, Types.GREEN);
        addEagle(4, Types.BLUE);
        addEagle(21, Types.BLUE);

        addShark(47, Types.RED);
        addShark(50, Types.RED);
        addShark(53, Types.GREEN);
        addShark(14, Types.GREEN);
        addShark(85, Types.BLUE);
        addShark(61, Types.BLUE);

        addFlag(5, EAGLE_PLAYER);
        addFlag(86, SHARK_PLAYER);

        isEagleTurn = ThreadLocalRandom.current().nextInt(0, 2) == 0;
    }

    private void initializeSquare() {

        int increment = 1;

        for (int i = 0; i < BoardSize.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardSize.BOARD_COLUMNS; j++) {
                SQUARE_ARRAY[i][j] = new Square(increment);
                increment++;
            }
        }
    }

    private void addShark(int position, Enum type) {

        if (type == Types.RED) {
            SharkRed shark = new SharkRed(position, Types.RED);
            SHARK_PLAYER.addPiece(shark);

            Square square = getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        } else if (type == Types.GREEN) {
            SharkGreen shark = new SharkGreen(position, Types.GREEN);
            SHARK_PLAYER.addPiece(shark);

            Square square = getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        } else if (type == Types.BLUE) {
            SharkBlue shark = new SharkBlue(position, Types.BLUE);
            SHARK_PLAYER.addPiece(shark);

            Square square = getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        }
    }

    private void addEagle(int position, Enum type) {

        if (type == Types.RED) {
            EagleRed eagle = new EagleRed(position, Types.RED);
            EAGLE_PLAYER.addPiece(eagle);

            Square square = getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        } else if (type == Types.GREEN) {
            EagleGreen eagle = new EagleGreen(position, Types.GREEN);
            EAGLE_PLAYER.addPiece(eagle);

            Square square = getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        } else if (type == Types.BLUE) {
            Eagle eagle = new EagleBlue(position, Types.BLUE);
            EAGLE_PLAYER.addPiece(eagle);

            Square square = getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        }

    }

    private void addFlag(int position, Player owner) {

        Flag flag = new Flag(position, owner, Types.FLAG);

        FLAG_LIST.add(flag);

        Square square = getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    public void changePlayerTurn() {
        isEagleTurn = !isEagleTurn;
    }

    public void updatePieceStatus() {
        if (isEagleTurn) {
            for (Shark shark : SHARK_PLAYER.getPIECE_LIST()) {
                if (shark.isStunned()) {
                    shark.setStunned(false);
                }
            }

        } else {
            for (Eagle eagle : EAGLE_PLAYER.getPIECE_LIST()) {
                if (eagle.isStunned()) {
                    eagle.setStunned(false);
                }
            }
        }
    }

    public Player<Eagle> getEAGLE_PLAYER() {
        return EAGLE_PLAYER;
    }

    public Player<Shark> getSHARK_PLAYER() {
        return SHARK_PLAYER;
    }

    public Square[][] getSQUARE_ARRAY() {
        return SQUARE_ARRAY;
    }

    public List<Flag> getFLAG_LIST() {
        return FLAG_LIST;
    }

    public boolean isEagleTurn() {
        return isEagleTurn;
    }
}
