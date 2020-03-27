package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import controller.GameController;
import model.GameModel;

public class GameView
        extends JFrame
        implements ActionListener {

    private static final String WINDOW_TITLE = "Eagle vs Shark";
    private BoardView boardView;
    private TurnPanel turnPanel;
    private GameController gameController;

    public GameView(GameModel gameModel) {
        super(WINDOW_TITLE);

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 900);

        Container contentPane = getContentPane();

        boardView = new BoardView(gameModel);
        boardView.setLocation(0, 0);
        contentPane.add(boardView);

        turnPanel = new TurnPanel(this, gameModel, this.getBackground());
        turnPanel.setLocation(650, 20);
        turnPanel.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(turnPanel);

        revalidate();
        repaint();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Up":
                gameController.move(0, 0);
                //boardView.moveImage(0,0);
                System.out.println("Up");
                break;
            /*case "Down":
                gameController.move(0, 1);
                break;
            case "Left":
                gameController.move(0, 2);
                break;
            case "Right":
                gameController.move(0, 3);
                break;*/
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
