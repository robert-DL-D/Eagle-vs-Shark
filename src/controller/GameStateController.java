package controller;

import file.LoadGame;
import file.SaveGame;
import model.GameModel;
import view.GameView;

class GameStateController {

    void saveGame(GameModel gameModel, String turnLimit, int turnTime) {
        new SaveGame().saveGame(gameModel, turnLimit, turnTime);
    }

    GameModel loadGame(GameView gameView) {
        LoadGame loadGame = new LoadGame();
        loadGame.loadGame();

        GameModel gameModel = loadGame.getGameModel();
        String turnLimit = loadGame.getTurnLimit();
        int turnTime = loadGame.getTurnTime();

        if (loadGame.isSaveFileExist()) {
            gameView.initializeGameView(gameModel.getSQUARE_ARRAY(),
                    gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                    gameModel.getFLAG_LIST(),
                    gameModel.getISLAND_LIST(),
                    gameModel.getAllyPieceList(),
                    gameModel.isEagleTurn(), turnLimit);

            gameView.loadGame(gameModel.getCurrentPlayer(), turnTime);
        }

        return gameModel;
    }

}
