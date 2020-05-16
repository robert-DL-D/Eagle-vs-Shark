package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class AddPieceModel implements Serializable {
    private final List<Flag> FLAG_LIST = new ArrayList<>();
    private final List<Island> ISLAND_LIST = new ArrayList<>();
    private final PlayerModel PLAYER_MODEL;
    private final SquaresModel SQUARES_MODEL;

    AddPieceModel(PlayerModel playerModel, SquaresModel squaresModel) {
        PLAYER_MODEL = playerModel;
        SQUARES_MODEL = squaresModel;

        addMovablePiece(Types.RED, StringText.EAGLE, 38);
        addMovablePiece(Types.RED, StringText.EAGLE, 33);
        addMovablePiece(Types.GREEN, StringText.EAGLE, 59);
        addMovablePiece(Types.GREEN, StringText.EAGLE, 77);
        addMovablePiece(Types.BLUE, StringText.EAGLE, 14);
        addMovablePiece(Types.BLUE, StringText.EAGLE, 29);

        addMovablePiece(Types.RED, StringText.SHARK, 30);
        addMovablePiece(Types.RED, StringText.SHARK, 50);
        addMovablePiece(Types.GREEN, StringText.SHARK, 39);
        addMovablePiece(Types.GREEN, StringText.SHARK, 42);
        addMovablePiece(Types.BLUE, StringText.SHARK, 40);
        addMovablePiece(Types.BLUE, StringText.SHARK, 61);

        addFlag(5, playerModel.getEAGLE_PLAYER());
        addFlag(86, playerModel.getSHARK_PLAYER());

        addIsland(1);
        addIsland(82);
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
