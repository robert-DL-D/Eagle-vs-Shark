package controller;

import java.util.List;

import model.GameModel;
import model.MovablePiece;
import model.StringText;
import view.GameView;

class AbilityController {

    void useAbility(int index, String actionCommand, GameModel gameModel, GameView gameView) {

        if (index != -1) {

            List<? extends MovablePiece> targetMovablePieceList = null;
            MovablePiece targetedMovablePiece;
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
            }

            for (MovablePiece movablePiece : gameModel.getAllyPieceList()) {
                if (movablePiece.getAbility().toString().equals(abilityUsed)
                        && null != targetMovablePieceList
                        && targetMovablePieceList.size() > 0) {
                    targetedMovablePiece = targetMovablePieceList.get(index);
                    movablePiece.useAbility(targetedMovablePiece);

                    gameModel.getCurrentPlayer().setAbilityUsed(abilityUsed);
                    gameView.updateViewAfterAbilityUse(targetedMovablePiece, abilityUsed, gameModel.getAllyPieceList());
                }
            }

        }
    }

}
