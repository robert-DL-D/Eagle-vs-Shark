package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameModel;
import model.MovablePiece;
import model.StringText;
import view.GameView;

public class GameController
        implements ActionListener {

    private final GameView GAME_VIEW = new GameView(this);
    private final AbilityController ABILITY_CONTROLLER = new AbilityController();
    private final GameStateController gameStateController = new GameStateController();
    private GameModel gameModel = new GameModel();

    public GameController() {
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

        if (StringText.NEXT_TURN.equals(actionCommand)) {

            gameModel.updateNextTurn();
            GAME_VIEW.updateNextTurn(gameModel.getAllyPieceList(), gameModel.isEagleTurn());

        } else if (StringText.STUN.equals(actionCommand) || StringText.SLOW.equals(actionCommand)) {

            GAME_VIEW.selectedAbility(actionCommand, gameModel.getEnemyPieceList());

        } else if (StringText.SPEED.equals(actionCommand) || StringText.SHIELD.equals(actionCommand)) {

            GAME_VIEW.selectedAbility(actionCommand, gameModel.getAllyPieceList());

        } else if (actionCommand.contains(StringText.USE)) {

            ABILITY_CONTROLLER.useAbility(GAME_VIEW.getPieceJListSelectedItem(), actionCommand, gameModel, GAME_VIEW);

        } else if (actionCommand.contains(StringText.MOVING_MODE) || actionCommand.contains(StringText.ABILITY_MODE)) {

            int selectedButtonIndex = GAME_VIEW.getSelectedButtonIndex();
            GAME_VIEW.togglePieceMode(selectedButtonIndex, gameModel.getAllyPieceList());
            gameModel.updateMovingMode(actionCommand, selectedButtonIndex);

        } else if (actionCommand.contains(StringText.EAGLE) || actionCommand.contains(StringText.SHARK)) {

            MovablePiece movablePiece = gameModel.getCurrentPlayer().getMOVABLEPIECE_LIST().get(GAME_VIEW.getSelectedButtonIndex());
            GAME_VIEW.movePiece(movablePiece);

        } else if (StringText.MOVE.equals(actionCommand)) {

            if (gameModel.movePiece(GAME_VIEW.getSelectedButtonIndex(), GAME_VIEW.getMovementCoord())) {
                GAME_VIEW.updateViewAfterPieceMove(gameModel.getAllyPieceList(), GAME_VIEW.getSelectedButtonIndex());
            }
        } else if (StringText.SAVE_GAME.equals(actionCommand)) {

            gameStateController.saveGame(gameModel, GAME_VIEW.getTurnTime());

        } else if (StringText.LOAD_GAME.equals(actionCommand)) {

            GameModel gameModel = gameStateController.loadGame(GAME_VIEW);
            if (gameModel != null) {
                this.gameModel = gameModel;
            }

        }
    }

}
