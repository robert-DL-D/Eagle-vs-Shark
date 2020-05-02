package controller;

import model.Eagle;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
import model.Square;
import view.GameView;

public class GameController {

    private final GameView GAME_VIEW;
    private final GameModel GAME_MODEL;

    public GameController(GameModel GAME_MODEL, GameView GAME_VIEW) {
        this.GAME_MODEL = GAME_MODEL;
        this.GAME_VIEW = GAME_VIEW;
        GAME_VIEW.setCurrentPlayer(GAME_MODEL.isEagleTurn());
        GAME_VIEW.initializeGameView(this, GAME_MODEL);

    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1 && movementCoord != null) {

            if (GAME_MODEL.movePiece(index, movementCoord)) {
                GAME_VIEW.updateViewAfterPieceMove(GAME_MODEL.getEAGLE_PLAYER(), GAME_MODEL.getSHARK_PLAYER());

                checkVictoryCondition();
            }
        }
    }

    public void useAbility(int index, String actionCommand) {

        if (index != -1) {

            if (actionCommand.contains("STUN")) {
                GAME_VIEW.setAfterUseText(GAME_MODEL.stunPiece(index));
            } else if (actionCommand.contains("SPEED")) {
                GAME_VIEW.setAfterUseText(GAME_MODEL.speedPiece(index));
            } else if (actionCommand.contains("SLOW")) {
                GAME_VIEW.setAfterUseText(GAME_MODEL.slowPiece(index));
            }
        }
    }

    public void updateNextTurn() {
        GAME_MODEL.changePlayerTurn();
        GAME_MODEL.resetPieceMovementStatus();
        GAME_VIEW.setCurrentPlayer(GAME_MODEL.isEagleTurn());
        GAME_VIEW.updateNextTurn(GAME_MODEL.isEagleTurn());
    }

    private void checkVictoryCondition() {

        for (Flag flag : GAME_MODEL.getFLAG_LIST()) {

            Square flagSquare = GAME_MODEL.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];

            if (flagSquare.getMovablePiece() != null) {

                MovablePiece movablepiece = flagSquare.getMovablePiece();

                if (movablepiece instanceof Eagle) {
                    System.out.println("Eagle Won");
                } else {
                    System.out.println("Shark Won");
                }
            }
        }
    }

    public MovablePiece getEaglePiece(int selectedPieceIndex) {
        return GAME_MODEL.getEAGLE_PLAYER().getMovablePiece(selectedPieceIndex);
    }

    public MovablePiece getSharkPiece(int selectedPieceIndex) {
        return GAME_MODEL.getSHARK_PLAYER().getMovablePiece(selectedPieceIndex);
    }
}
