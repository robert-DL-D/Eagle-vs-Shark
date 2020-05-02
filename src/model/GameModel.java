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

        addMovablePiece(Types.RED, "Eagle", 38);
        addMovablePiece(Types.RED, "Eagle", 33);
        addMovablePiece(Types.GREEN, "Eagle", 59);
        addMovablePiece(Types.GREEN, "Eagle", 77);
        addMovablePiece(Types.BLUE, "Eagle", 14);
        addMovablePiece(Types.BLUE, "Eagle", 29);

        addMovablePiece(Types.RED, "Shark", 30);
        addMovablePiece(Types.RED, "Shark", 50);
        addMovablePiece(Types.GREEN, "Shark", 39);
        addMovablePiece(Types.GREEN, "Shark", 42);
        addMovablePiece(Types.BLUE, "Shark", 40);
        addMovablePiece(Types.BLUE, "Shark", 61);

        addFlag(5, EAGLE_PLAYER);
        addFlag(86, SHARK_PLAYER);

        addIsland(1);
        addIsland(82);

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

    private void addMovablePiece(Enum type, String playerString, int position) {

        AbstractFactory factory = null;
        Player player = null;
        MovablePiece movablePiece;

        if (type == Types.RED) {
            factory = new RedPieceFactory();
        } else if (type == Types.GREEN) {
            factory = new GreenPieceFactory();
        } else if (type == Types.BLUE) {
            factory = new BluePieceFactory();
        }

        movablePiece = factory.getPiece(playerString, position);

        if (playerString.equals("Eagle")) {
            player = EAGLE_PLAYER;
        } else if (playerString.equals("Shark")) {
            player = SHARK_PLAYER;
        }

        player.addMovablePiece(movablePiece);
        Square square = getSQUARE_ARRAY()[movablePiece.getRow()][movablePiece.getColumn()];
        square.addMovablePiece(movablePiece);

    }

    private void addFlag(int position, Player player) {

        Flag flag = new Flag(position, player);
        FLAG_LIST.add(flag);

        Square square = getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    private void addIsland(int position) {

        Island island = new Island(position);
        ISLAND_LIST.add(island);

        Square square = getSQUARE_ARRAY()[island.getRow()][island.getColumn()];
        square.addPiece(island);
    }

    public void changePlayerTurn() {
        isEagleTurn = !isEagleTurn;
    }

    public boolean movePiece(int index, int[] movementCoord) {

        return (isEagleTurn ? EAGLE_PLAYER : SHARK_PLAYER).getMovablePiece(index).updatePieceRowColumn(EAGLE_PLAYER, SHARK_PLAYER, SQUARE_ARRAY, movementCoord);

    }

    public MovablePiece stunPiece(int index) {

        MovablePiece movablePiece = (isEagleTurn ? SHARK_PLAYER.getMOVABLEPIECE_LIST() : EAGLE_PLAYER.getMOVABLEPIECE_LIST()).get(index);
        movablePiece.setStunned(true);
        return movablePiece;
    }

    public MovablePiece speedPiece(int index) {

        MovablePiece movablePiece = (isEagleTurn ? EAGLE_PLAYER.getMOVABLEPIECE_LIST() : SHARK_PLAYER.getMOVABLEPIECE_LIST()).get(index);
        movablePiece.getMOVEMENT_COORD().clear();
        movablePiece.addMovementCoord(3);

        return movablePiece;
    }

    public MovablePiece slowPiece(int index) {

        MovablePiece movablePiece = (isEagleTurn ? SHARK_PLAYER.getMOVABLEPIECE_LIST() : EAGLE_PLAYER.getMOVABLEPIECE_LIST()).get(index);
        movablePiece.getMOVEMENT_COORD().clear();
        movablePiece.addMovementCoord(1);
        movablePiece.setSlowed(true);

        return movablePiece;
    }

    public void resetPieceMovementStatus() {

        List<? extends MovablePiece> movablePieceList;

        if (isEagleTurn) {
            movablePieceList = SHARK_PLAYER.getMOVABLEPIECE_LIST();
        } else {
            movablePieceList = EAGLE_PLAYER.getMOVABLEPIECE_LIST();
        }

        for (MovablePiece movablePiece : movablePieceList) {

            movablePiece.getMOVEMENT_COORD().clear();
            movablePiece.addMovementCoord(MovablePiece.DEFAULT_MOVEMENT_DISTANCE);

            if (movablePiece.isStunned()) {
                movablePiece.setStunned(false);
            }

            if (movablePiece.isSlowed()) {
                movablePiece.setSlowed(false);
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

    public List<? extends MovablePiece> getCurrentPieceList() {

        return isEagleTurn ? EAGLE_PLAYER.getMOVABLEPIECE_LIST() : SHARK_PLAYER.getMOVABLEPIECE_LIST();
    }

    public List<? extends MovablePiece> getOtherPieceList() {

        return isEagleTurn ? SHARK_PLAYER.getMOVABLEPIECE_LIST() : EAGLE_PLAYER.getMOVABLEPIECE_LIST();
    }
}
