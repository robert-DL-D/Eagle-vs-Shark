package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import file.LoadGame;
import file.SaveGame;
import model.Eagle;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
import model.Square;
import model.StringText;
import view.GameView;

public class GameController
        implements ActionListener {

    private GameModel gameModel;
    private final GameView GAME_VIEW;

    public GameController() {
        gameModel = new GameModel();
        GAME_VIEW = new GameView(this);

        GAME_VIEW.initializeGameView(gameModel.getSQUARE_ARRAY(),
                gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getFLAG_LIST(),
                gameModel.getISLAND_LIST(),
                gameModel.getAllyPieceList(),
                gameModel.isEagleTurn());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        if ("Next Turn".equals(actionCommand)) {
            gameModel.updateNextTurn();
            GAME_VIEW.updateNextTurn(gameModel.getAllyPieceList(), gameModel.isEagleTurn());
        } else if ("STUN".equals(actionCommand) || "SLOW".equals(actionCommand)) {
            GAME_VIEW.selectedAbility(actionCommand, gameModel.getEnemyPieceList());
        } else if (StringText.SPEED.equals(actionCommand) || StringText.SHIELD.equals(actionCommand)) {
            GAME_VIEW.selectedAbility(actionCommand, gameModel.getAllyPieceList());
        } else if (actionCommand.contains("Use")) {
            useAbility(GAME_VIEW.getPieceJListSelectedItem(), actionCommand);
        } else if (actionCommand.contains(StringText.MOVING_MODE) || actionCommand.contains(StringText.ABILITY_MODE)) {
            int selectedButtonIndex = GAME_VIEW.getSelectedButtonIndex();
            GAME_VIEW.togglePieceMode(selectedButtonIndex, gameModel.getAllyPieceList());
            updateMovingMode(actionCommand, selectedButtonIndex);
        } else if (actionCommand.contains(StringText.EAGLE) || actionCommand.contains(StringText.SHARK)) {
            MovablePiece movablePiece = gameModel.getCurrentPlayer().getMOVABLEPIECE_LIST().get(GAME_VIEW.getSelectedButtonIndex());
            GAME_VIEW.movePiece(movablePiece);
        } else if ("Move".equals(actionCommand)) {
            movePiece(GAME_VIEW.getPIECE_PANEL().getSelectedButtonIndex(), GAME_VIEW.getMOVEMENT_PANEL().getMovementCoord());
        } else if ("Save Game".equals(actionCommand)) {
            saveGame();
        } else if ("Load Game".equals(actionCommand)) {
            loadGame();
        }
    }

    private void useAbility(int index, String actionCommand) {

        if (index != -1) {

            List<? extends MovablePiece> targetMovablePieceList = null;
            MovablePiece targetedMovablePiece;
            String abilityUsed = null;

            if (actionCommand.contains("STUN")) {
                targetMovablePieceList = gameModel.getEnemyPieceList();
                abilityUsed = "STUN";
            } else if (actionCommand.contains(StringText.SPEED)) {
                targetMovablePieceList = gameModel.getAllyPieceList();
                GAME_VIEW.hideMovementUI();
                GAME_VIEW.removeMoveablePiece();
                abilityUsed = StringText.SPEED;
            } else if (actionCommand.contains("SLOW")) {
                targetMovablePieceList = gameModel.getEnemyPieceList();
                abilityUsed = "SLOW";
            } else if (actionCommand.contains(StringText.SHIELD)) {
                targetMovablePieceList = gameModel.getAllyPieceList();
                abilityUsed = StringText.SHIELD;
            }

            for (MovablePiece movablePiece : gameModel.getAllyPieceList()) {
                if (movablePiece.getAbility().toString().equals(abilityUsed)) {
                    targetedMovablePiece = targetMovablePieceList.get(index);
                    movablePiece.useAbility(targetedMovablePiece);

                    gameModel.getCurrentPlayer().setAbilityUsed(abilityUsed);
                    GAME_VIEW.updateViewAfterAbilityUse(targetedMovablePiece, abilityUsed, gameModel.getAllyPieceList());
                }
            }

        }
    }

    private void updateMovingMode(String actionCommand, int selectedModeIndex) {
        MovablePiece movablePiece = gameModel.getAllyPieceList().get(selectedModeIndex * 2);
        movablePiece.setMovingMode(!actionCommand.contains(StringText.MOVING_MODE));

        movablePiece = gameModel.getAllyPieceList().get(selectedModeIndex * 2 + 1);
        movablePiece.setMovingMode(!actionCommand.contains(StringText.MOVING_MODE));

        gameModel.getCurrentPlayer().setPieceModeToggled(true);
        gameModel.getCurrentPlayer().setPieceModeToggledIndex(selectedModeIndex);
    }

    private void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1 && movementCoord != null) {

            if (gameModel.movePiece(index, movementCoord)) {
                gameModel.setPieceMoved();
                GAME_VIEW.updateViewAfterPieceMove(gameModel.getAllyPieceList(), gameModel.getAllyPieceList().get(index));
                checkVictoryCondition();
            }
        }
    }

    private void checkVictoryCondition() {

        for (Flag flag : gameModel.getFLAG_LIST()) {

            Square flagSquare = gameModel.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];

            if (flagSquare.getMovablePiece() != null) {
                System.out.println(flagSquare.getMovablePiece() instanceof Eagle ? "Eagle Won" : "Shark Won");
            }
        }
    }

    private void saveGame() {
        new SaveGame().saveGame(gameModel);

    }

    private void loadGame() {
        LoadGame loadGame = new LoadGame();
        gameModel = loadGame.loadGame();

        if (loadGame.isSaveFileExist()) {
            GAME_VIEW.initializeGameView(gameModel.getSQUARE_ARRAY(),
                    gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getFLAG_LIST(),
                    gameModel.getISLAND_LIST(),
                    gameModel.getAllyPieceList(),
                    gameModel.isEagleTurn());

            GAME_VIEW.loadGame(gameModel.getCurrentPlayer());
        }

    }

}
