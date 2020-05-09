package controller;

import java.util.List;

import file.LoadGame;
import file.SaveGame;
import model.Eagle;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
import model.Square;
import view.GameView;

public class GameController {

    private GameModel gameModel;
    private final GameView GAME_VIEW;

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        GAME_VIEW = gameView;

        gameView.setGameController(this);
        gameView.initializeGameView(gameModel.getSQUARE_ARRAY(),
                gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getFLAG_LIST(),
                gameModel.getISLAND_LIST(),
                gameModel.getCurrentPieceList(),
                gameModel.isEagleTurn());

    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1 && movementCoord != null) {

            if (gameModel.movePiece(index, movementCoord)) {
                gameModel.setPieceMoved();
                GAME_VIEW.updateViewAfterPieceMove(getAllyPieceList(), getAllyPieceList().get(index));
                checkVictoryCondition();
            }
        }
    }

    public void useAbility(int index, String actionCommand) {

        if (index != -1) {

            List<? extends MovablePiece> targetMovablePieceList = null;
            MovablePiece targetedMovablePiece = null;
            String abilityUsed = null;

            if (actionCommand.contains("STUN")) {
                targetMovablePieceList = getEnemyPieceList();
                abilityUsed = "STUN";
            } else if (actionCommand.contains("SPEED")) {
                targetMovablePieceList = getAllyPieceList();
                GAME_VIEW.hideMovementUI();
                GAME_VIEW.removeMoveablePiece();
                abilityUsed = "SPEED";
            } else if (actionCommand.contains("SLOW")) {
                targetMovablePieceList = getEnemyPieceList();
                abilityUsed = "SLOW";
            } else if (actionCommand.contains("SHIELD")) {
                targetMovablePieceList = getAllyPieceList();
                abilityUsed = "SHIELD";
            }

            for (MovablePiece movablePiece : getAllyPieceList()) {
                if (movablePiece.getAbility().toString().equals(abilityUsed)) {
                    targetedMovablePiece = targetMovablePieceList.get(index);
                    movablePiece.useAbility(targetedMovablePiece);
                }
            }

            gameModel.getCurrentPlayer().setAbilityUsed(abilityUsed);

            GAME_VIEW.setAfterAbilityUseText(targetedMovablePiece);
            GAME_VIEW.disableUIAfterAbilityUse(abilityUsed, getAllyPieceList());

        }
    }

    public void updateMovingMode(String actionCommand, int selectedModeIndex) {
        MovablePiece movablePiece = gameModel.getCurrentPieceList().get(selectedModeIndex * 2);
        movablePiece.setMovingMode(!actionCommand.contains("Moving Mode"));

        movablePiece = gameModel.getCurrentPieceList().get(selectedModeIndex * 2 + 1);
        movablePiece.setMovingMode(!actionCommand.contains("Moving Mode"));

        gameModel.getCurrentPlayer().setPieceModeToggled(true);
        gameModel.getCurrentPlayer().setPieceModeToggledIndex(selectedModeIndex);
    }

    public void updateNextTurn() {
        gameModel.updateNextTurn();
        GAME_VIEW.updateNextTurn(getAllyPieceList(), gameModel.isEagleTurn());
    }

    private void checkVictoryCondition() {

        for (Flag flag : gameModel.getFLAG_LIST()) {

            Square flagSquare = gameModel.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];

            if (flagSquare.getMovablePiece() != null) {

                if (flagSquare.getMovablePiece() instanceof Eagle) {
                    System.out.println("Eagle Won");
                } else {
                    System.out.println("Shark Won");
                }
            }
        }
    }

    public void saveGame() {
        new SaveGame().saveGame(gameModel);

    }

    public void loadGame() {
        LoadGame loadGame = new LoadGame();
        gameModel = loadGame.loadGame();

        if (loadGame.isSaveFileExist()) {
            GAME_VIEW.initializeGameView(gameModel.getSQUARE_ARRAY(),
                    gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getFLAG_LIST(),
                    gameModel.getISLAND_LIST(),
                    gameModel.getCurrentPieceList(),
                    gameModel.isEagleTurn());

            GAME_VIEW.loadGame(gameModel.getCurrentPlayer());
        }

    }

    public MovablePiece getMovablePiece(String actionCommand, int selectedPieceIndex) {
        List<? extends MovablePiece> movablePieceList = actionCommand.contains("Eagle") ?
                gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST() : gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST();
        return movablePieceList.get(selectedPieceIndex);
    }

    public List<? extends MovablePiece> getAllyPieceList() {
        return gameModel.getCurrentPieceList();
    }

    public List<? extends MovablePiece> getEnemyPieceList() {
        return gameModel.getOtherPieceList();
    }

}
