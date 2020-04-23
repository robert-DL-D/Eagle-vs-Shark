import controller.GameController;
import model.GameModel;
import view.GameView;
//import view.TemplateFrame;

public class Main {

    public static void main(String[] args) {

        new GameController(new GameModel(), new GameView());

        //new TemplateFrame().showStartView();

    }

}
