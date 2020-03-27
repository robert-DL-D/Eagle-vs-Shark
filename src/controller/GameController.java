package controller;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import model.GameModel;
import view.GameView;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;

    public GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.setGameController(this);

    }

    public void move(int shark, int direction) {
        String errorMsg = gameModel.moveSnake(shark, direction);

        /*if (errorMsg != null) {
            gameView.showErrorDialog(errorMsg);
            return;
        }*/

    }

}
