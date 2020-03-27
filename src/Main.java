import controller.GameController;
import model.GameModel;
import view.GameView;

public class Main {

    public static void main(String[] args) {

        GameModel gameModel = new GameModel();
        GameView gameView = new GameView(gameModel);
        GameController gameController = new GameController(gameView, gameModel);
    }

}
