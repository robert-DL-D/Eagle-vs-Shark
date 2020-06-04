package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PieceManagement implements Serializable {
    private final List<Flag> FLAG_LIST = new ArrayList<>();
    private final List<Island> ISLAND_LIST = new ArrayList<>();
    private final PlayerManagement PLAYER_MODEL;
    private final BoardModel SQUARES_MODEL;
    private static final int ISLAND_NUMBER = 2;

    PieceManagement(PlayerManagement playerModel, BoardModel boardModel) {
        PLAYER_MODEL = playerModel;
        SQUARES_MODEL = boardModel;

        // add flags
        addFlag((BoardConfig.BOARD_COLUMNS + 1) / 2, playerModel.getEAGLE_PLAYER());
        addFlag(BoardConfig.BOARD_ROWS * BoardConfig.BOARD_COLUMNS - BoardConfig.BOARD_COLUMNS / 2, playerModel.getSHARK_PLAYER());

        Set<Object> positionSet = new HashSet<>();
        positionSet.add((BoardConfig.BOARD_COLUMNS + 1) / 2);
        positionSet.add(BoardConfig.BOARD_ROWS * BoardConfig.BOARD_COLUMNS - BoardConfig.BOARD_COLUMNS / 2);

        // add eagles
        int count = 0;
        int m = 1;
        int n = (BoardConfig.BOARD_ROWS - 2) * BoardConfig.BOARD_COLUMNS / 2;
        do {
            // calculate the random positions of eagle
            // range:[m,n]
            int eaglePosition = (int) (Math.random() * (n - m + 1) + m);
            if (!positionSet.contains(eaglePosition) && eaglePosition != BoardConfig.BOARD_COLUMNS / 2) {
                addMovablePiece(Types.values()[count / BoardConfig.PIECE_NUMBER], StringText.EAGLE, eaglePosition);
                positionSet.add(eaglePosition);
                count++;
            }
        } while (count < 3 * BoardConfig.PIECE_NUMBER);

        // add the islands of eagles
        count = 0;
        do {
            // calculate the random positions of island
            // range:[m,n]
            int islandPosition = (int) (Math.random() * (n - m + 1) + m);
            if (!positionSet.contains(islandPosition) && islandPosition != BoardConfig.BOARD_COLUMNS / 2) {
                addIsland(islandPosition);
                positionSet.add(islandPosition);
                count++;
            }
        } while (count < ISLAND_NUMBER);

        // add sharks
        count = 0;
        m = (BoardConfig.BOARD_ROWS + 2) * BoardConfig.BOARD_COLUMNS / 2 + 1;
        n = BoardConfig.BOARD_ROWS * BoardConfig.BOARD_COLUMNS;
        do {
            // calculate the random positions of eagle
            // range:[m,n]
            int sharkPosition = (int) (Math.random() * (n - m + 1) + m);
            if (!positionSet.contains(sharkPosition) && sharkPosition != BoardConfig.BOARD_COLUMNS / 2) {
                addMovablePiece(Types.values()[count / BoardConfig.PIECE_NUMBER], StringText.SHARK, sharkPosition);
                positionSet.add(sharkPosition);
                count++;
            }
        } while (count < 3 * BoardConfig.PIECE_NUMBER);

        // add the islands of sharks
        count = 0;
        do {
            // calculate the random positions of island
            // range:[m,n]
            int islandPosition = (int) (Math.random() * (n - m + 1) + m);
            if (!positionSet.contains(islandPosition) && islandPosition != BoardConfig.BOARD_COLUMNS / 2) {
                addIsland(islandPosition);
                positionSet.add(islandPosition);
                count++;
            }
        } while (count < ISLAND_NUMBER);

    }

    private void addMovablePiece(Enum type, String playerString, int position) {
        SQUARES_MODEL.addMovablePieceToSquare(PLAYER_MODEL.addMovablePiece(type, playerString, position));
    }

    private void addFlag(int position, Player<? extends MovablePiece> player) {
        Flag flag = new Flag(position, player);
        FLAG_LIST.add(flag);
        SQUARES_MODEL.addPieceToSquare(flag);
    }

    private void addIsland(int position) {
        Island island = new Island(position);
        ISLAND_LIST.add(island);
        SQUARES_MODEL.addPieceToSquare(island);
    }

    List<Flag> getFLAG_LIST() {
        return FLAG_LIST;
    }

    List<Island> getISLAND_LIST() {
        return ISLAND_LIST;
    }
}
