import controller.GameController;
import model.GameModel;
import view.GameView;

public class Main {

    public static void main(String[] args) {

        new GameController(new GameView(), new GameModel());
    }

}
