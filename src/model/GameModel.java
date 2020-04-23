package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {

    private final Square[][] SQUARE_ARRAY = new Square[BoardSize.BOARD_ROWS][BoardSize.BOARD_COLUMNS];
    private final Player<Eagle> EAGLE_PLAYER = new Player<>();
    private final Player<Shark> SHARK_PLAYER = new Player<>();
    private final List<Flag> FLAG_LIST = new ArrayList<>();
    private final List<Island> ISLAND_LIST = new ArrayList<>();

    private boolean isEagleTurn;

    public GameModel() {
        initializeSquare();

        addEagle(38, Types.RED);
        addEagle(41, Types.RED);
        addEagle(44, Types.GREEN);
        addEagle(77, Types.GREEN);
        addEagle(4, Types.BLUE);
        addEagle(29, Types.BLUE);

        addShark(30, Types.RED);
        addShark(50, Types.RED);
        addShark(39, Types.GREEN);
        addShark(14, Types.GREEN);
        addShark(40, Types.BLUE);
        addShark(61, Types.BLUE);

        addFlag(5, EAGLE_PLAYER);
        addFlag(86, SHARK_PLAYER);

        addIsland(32);
        addIsland(59);

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

    private void addEagle(int position, Enum type) {
        Eagle eagle = null;

        if (type == Types.RED) {
            eagle = new EagleRed(position, Types.RED);
        } else if (type == Types.GREEN) {
            eagle = new EagleGreen(position, Types.GREEN);
        } else if (type == Types.BLUE) {
            eagle = new EagleBlue(position, Types.BLUE);
        }

        EAGLE_PLAYER.addMovablePiece(eagle);
        Square square = getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
        square.addMovablePiece(eagle);
    }

    private void addShark(int position, Enum type) {

        Shark shark = null;

        if (type == Types.RED) {
            shark = new SharkRed(position, Types.RED);
        } else if (type == Types.GREEN) {
            shark = new SharkGreen(position, Types.GREEN);
        } else if (type == Types.BLUE) {
            shark = new SharkBlue(position, Types.BLUE);
        }

        SHARK_PLAYER.addMovablePiece(shark);
        Square square = getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
        square.addMovablePiece(shark);
    }

    private void addFlag(int position, Player owner) {

        Flag flag = new Flag(position, owner, Types.FLAG);
        FLAG_LIST.add(flag);

        Square square = getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    private void addIsland(int position) {

        Island island = new Island(position, Types.ISLAND);
        ISLAND_LIST.add(island);

        Square square = getSQUARE_ARRAY()[island.getRow()][island.getColumn()];
        square.addPiece(island);
    }

    public void changePlayerTurn() {
        isEagleTurn = !isEagleTurn;
    }

    public void updatePieceStatus() {
        if (isEagleTurn) {
            for (Shark shark : SHARK_PLAYER.getMOVABLEPIECE_LIST()) {
                if (shark.isStunned()) {
                    shark.setStunned(false);
                }
            }

        } else {
            for (Eagle eagle : EAGLE_PLAYER.getMOVABLEPIECE_LIST()) {
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

    public List<Island> getISLAND_LIST() {
        return ISLAND_LIST;
    }

    public boolean isEagleTurn() {
        return isEagleTurn;
    }

}
