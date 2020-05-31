package model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

class PlayerManagement implements Serializable {

    private final Player<Eagle> EAGLE_PLAYER = new Player<>();
    private final Player<Shark> SHARK_PLAYER = new Player<>();

    private boolean eagleTurn = new Random().nextBoolean();

    MovablePiece addMovablePiece(Enum type, String playerString, int position) {

        AbstractFactory factory = null;
        Player player = null;
        MovablePiece movablePiece = null;

        if (type == Types.RED) {
            factory = new RedPieceFactory();
        } else if (type == Types.GREEN) {
            factory = new GreenPieceFactory();
        } else if (type == Types.BLUE) {
            factory = new BluePieceFactory();
        }

        if (playerString.equals(StringText.EAGLE)) {
            player = EAGLE_PLAYER;
            movablePiece = factory.getEaglePiece(position);
        } else if (playerString.equals(StringText.SHARK)) {
            player = SHARK_PLAYER;
            movablePiece = factory.getSharkPiece(position);
        }

        player.addMovablePiece(movablePiece);

        return movablePiece;

    }

    void updateMovingMode(String actionCommand, int selectedModeIndex) {
        for (MovablePiece movablePiece : getOwnPieceList()) {
            if (actionCommand.contains(movablePiece.getType().toString())) {
                movablePiece.setMovingMode(!actionCommand.contains(StringText.MOVING_MODE));
            }
        }
        getCurrentPlayer().setPieceModeToggledIndex(selectedModeIndex);
    }

    void updateNextTurn() {
        Player<? extends MovablePiece> currentPlayer = getCurrentPlayer();
        currentPlayer.setPieceMoved(false);
        currentPlayer.setAbilityUsed(null);
        currentPlayer.setPieceModeToggledIndex(-1);

        eagleTurn = !eagleTurn;
        resetPieceMovementStatus();

    }

    private void resetPieceMovementStatus() {
        boolean resetShield = true;

        for (MovablePiece movablePiece : getEnemyPieceList()) {

            if (movablePiece.getAbility() == Abilities.SHIELD) {
                resetShield = false;
            }

            movablePiece.getMOVEMENT_COORD().clear();
            movablePiece.addMovementCoord(MovablePiece.DEFAULT_MOVEMENT_DISTANCE);
            movablePiece.setStunned(false);
            movablePiece.setSlowed(false);

        }

        if (resetShield) {
            for (MovablePiece movablePiece : getOwnPieceList()) {
                movablePiece.setShielded(false);
            }
        }

    }

    Player<Eagle> getEAGLE_PLAYER() {
        return EAGLE_PLAYER;
    }

    Player<Shark> getSHARK_PLAYER() {
        return SHARK_PLAYER;
    }

    List<? extends MovablePiece> getOwnPieceList() {
        return eagleTurn ? EAGLE_PLAYER.getMOVABLEPIECE_LIST() : SHARK_PLAYER.getMOVABLEPIECE_LIST();
    }

    List<? extends MovablePiece> getEnemyPieceList() {
        return eagleTurn ? SHARK_PLAYER.getMOVABLEPIECE_LIST() : EAGLE_PLAYER.getMOVABLEPIECE_LIST();
    }

    Player<? extends MovablePiece> getCurrentPlayer() {
        return eagleTurn ? EAGLE_PLAYER : SHARK_PLAYER;
    }

    boolean isEagleTurn() {
        return eagleTurn;
    }

}
