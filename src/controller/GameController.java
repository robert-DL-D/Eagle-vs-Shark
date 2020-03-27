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

    public void move(int snake, int steps) {
        String errorMsg = gameModel.moveSnake(snake, steps);

        /*if (errorMsg != null) {
            gameView.showErrorDialog(errorMsg);
            return;
        }*/

    }

}
