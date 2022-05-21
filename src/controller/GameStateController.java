package controller;

import file.LoadGame;
import file.SaveGame;
import model.BoardConfig;
import model.GameModel;
import view.GameView;

class GameStateController {

    void saveGame(GameModel gameModel, int turnTime) {
        new SaveGame().saveGame(gameModel, turnTime);
    }

    GameModel loadGame(GameView gameView) {
        LoadGame loadGame = new LoadGame();
        loadGame.loadGame();

        GameModel gameModel = loadGame.getGameModel();

        if (loadGame.isSaveFileExist()) {
            BoardConfig.BOARD_ROWS = loadGame.getRowValue();
            BoardConfig.BOARD_COLUMNS = loadGame.getColumnValue();
            BoardConfig.PIECE_NUMBER = loadGame.getPieceNum();
            BoardConfig.TURN_LIMIT = loadGame.getTurnLimit();
            int turnTime = loadGame.getTurnTime();

            gameView.initGameView(gameModel.getSQUARE_ARRAY(),
                    gameModel.getEAGLE_PLAYER().getMOVABLE_PIECE_LIST(),
                    gameModel.getSHARK_PLAYER().getMOVABLE_PIECE_LIST(),
                    gameModel.getFLAG_LIST(),
                    gameModel.getISLAND_LIST(),
                    gameModel.getAllyPieceList(),
                    gameModel.getEnemyPieceList(),
                    gameModel.isEagleTurn());

            gameView.loadGame(gameModel.getCurrentPlayer(), gameModel.getEnemyPieceList(), turnTime);
        }

        return gameModel;
    }

}
