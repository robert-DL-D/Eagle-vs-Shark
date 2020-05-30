package controller;

import file.LoadGame;
import file.SaveGame;
import model.BoardConfig;
import model.GameModel;
import view.GameView;

public class GameStateController {

    void saveGame(GameModel gameModel, int turnTime) {
        new SaveGame().saveGame(gameModel, turnTime);
    }

    GameModel loadGame(GameView gameView) {
        LoadGame loadGame = new LoadGame();
        loadGame.loadGame();

        GameModel gameModel = loadGame.getGameModel();
        BoardConfig.BOARD_ROWS = loadGame.getRowValue();
        BoardConfig.BOARD_COLUMNS = loadGame.getColumnValue();
        BoardConfig.PIECE_NUMBER = loadGame.getPieceNum();
        BoardConfig.TURN_LIMIT = loadGame.getTurnLimit();
        int turnTime = loadGame.getTurnTime();

        if (loadGame.isSaveFileExist()) {
            gameView.initializeGameView(gameModel.getSQUARE_ARRAY(),
                    gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getFLAG_LIST(),
                    gameModel.getISLAND_LIST(),
                    gameModel.getAllyPieceList(),
                    gameModel.isEagleTurn());

            gameView.loadGame(gameModel.getCurrentPlayer(), turnTime);
        }

        return gameModel;
    }

}
