package controller;

import java.util.List;

import model.Eagle;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
import model.Square;
import view.GameView;

public class GameController {

    private final GameView GAME_VIEW;
    private final GameModel GAME_MODEL;

    public GameController(GameModel gameModel, GameView gameView) {
        GAME_MODEL = gameModel;
        GAME_VIEW = gameView;
        gameView.setCurrentPlayer(gameModel.isEagleTurn());

        int numberOfButtons = gameModel.getCurrentPieceList().size();

        gameView.initializeGameView(this,
                gameModel.getSQUARE_ARRAY(),
                gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getFLAG_LIST(),
                gameModel.getISLAND_LIST(),
                numberOfButtons,
                gameModel.getCurrentPieceList());

    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1 && movementCoord != null) {

            if (GAME_MODEL.movePiece(index, movementCoord)) {

                MovablePiece movablePiece = getCurrentPieceList().get(index);

                GAME_VIEW.updateViewAfterPieceMove(getCurrentPieceList(), movablePiece);

                checkVictoryCondition();
            }
        }
    }

    public void useAbility(int index, String actionCommand) {

        if (index != -1) {

            String abilityUsed = null;

            if (actionCommand.contains("STUN")) {
                GAME_VIEW.setAfterUseText(GAME_MODEL.stunPiece(index));
                abilityUsed = "STUN";

            } else if (actionCommand.contains("SPEED")) {
                GAME_VIEW.setAfterUseText(GAME_MODEL.speedPiece(index));
                GAME_VIEW.hideMovementUI();
                abilityUsed = "SPEED";

            } else if (actionCommand.contains("SLOW")) {
                GAME_VIEW.setAfterUseText(GAME_MODEL.slowPiece(index));
                abilityUsed = "SLOW";

            }

            GAME_VIEW.hideUnmovablePiece(abilityUsed, getCurrentPieceList());
        }
    }

    public void updateNextTurn() {
        GAME_MODEL.changePlayerTurn();
        GAME_MODEL.resetPieceMovementStatus();
        GAME_VIEW.setCurrentPlayer(GAME_MODEL.isEagleTurn());

        GAME_VIEW.updateNextTurn(getCurrentPieceList());
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

    public List<? extends MovablePiece> getCurrentPieceList() {
        return GAME_MODEL.getCurrentPieceList();
    }

    public List<? extends MovablePiece> getOtherPieceList() {
        return GAME_MODEL.getCurrentPieceList();
    }
}
