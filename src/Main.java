import controller.GameController;
import model.GameModel;
import view.GameView;

public class Main {

    public static void main(String[] args) {

//        new GameController(new GameModel(), new GameView());

        // add a new start panel to custom board
        GameController gameController= new GameController();
        gameController.showStartView();

    }

}
