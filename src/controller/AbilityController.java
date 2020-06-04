package controller;

import java.util.List;

import model.GameModel;
import model.MovablePiece;
import model.StringText;
import view.GameView;

class AbilityController {

    void setAbilityTarget(String actionCommand, GameModel gameModel, GameView gameView) {

        int index = gameView.getPieceJListSelectedItem();

        if (index != -1) {

            List<? extends MovablePiece> targetMovablePieceList = null;
            String abilityUsed = null;

            if (actionCommand.contains(StringText.STUN)) {
                targetMovablePieceList = gameModel.getEnemyPieceList();
                abilityUsed = StringText.STUN;
            } else if (actionCommand.contains(StringText.SPEED)) {
                targetMovablePieceList = gameModel.getAllyPieceList();
                gameView.removeMoveablePiece();
                abilityUsed = StringText.SPEED;
            } else if (actionCommand.contains(StringText.SLOW)) {
                targetMovablePieceList = gameModel.getEnemyPieceList();
                abilityUsed = StringText.SLOW;
            } else if (actionCommand.contains(StringText.SHIELD)) {
                targetMovablePieceList = gameModel.getAllyPieceList();
                abilityUsed = StringText.SHIELD;
            } else if (actionCommand.contains(StringText.CLEANSE)) {
                targetMovablePieceList = gameModel.getStunnedPieceList();
                abilityUsed = StringText.CLEANSE;
            } else if (actionCommand.contains(StringText.JUMP)) {
                targetMovablePieceList = gameModel.getAllyPieceList();
                abilityUsed = StringText.JUMP;
            }

            setAbilityTarget(index, actionCommand, gameModel, gameView, gameView.isSuperAbility(), targetMovablePieceList, abilityUsed);

        }
    }

    private void setAbilityTarget(int index, String actionCommand, GameModel gameModel, GameView gameView,
                                  boolean superAbility, List<? extends MovablePiece> targetPieceList, String abilityUsed) {

        MovablePiece targetPiece = targetPieceList.get(index);

        for (MovablePiece abilityPiece : gameModel.getAllyPieceList()) {

            if (abilityPiece.getAbility().toString().equals(abilityUsed) && !targetPieceList.isEmpty()) {

                if (superAbility) {
                    if (actionCommand.contains(StringText.STUN) || actionCommand.contains(StringText.SHIELD)) {
                        for (MovablePiece piece : targetPieceList) {
                            if (piece.getType() == targetPiece.getType()) {
                                validTarget(gameModel, superAbility, abilityPiece, piece);
                            }
                        }
                    } else {
                        validTarget(gameModel, superAbility, abilityPiece, targetPiece);
                    }
                } else {
                    validTarget(gameModel, superAbility, abilityPiece, targetPiece);
                }
                break;
            }
        }

        gameModel.getCurrentPlayer().setAbilityUsed(actionCommand);
        gameView.updateViewAfterAbilityUse(actionCommand, gameModel.getAllyPieceList(), gameModel.getEnemyPieceList());
    }

    private void validTarget(GameModel gameModel, boolean superAbility, MovablePiece abilityPiece, MovablePiece targetPiece) {
        if (!abilityPiece.isImmune()) {
            abilityPiece.useAbility(targetPiece, gameModel, superAbility);
        }
    }

}
