package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import controller.GameController;
import model.Eagle;
import model.Flag;
import model.Shark;

public class GameView
        extends JFrame
        implements ActionListener {

    private BoardView boardView;
    private TurnPanel turnPanel;
    private GameController gameController;

    private List<Shark> sharkList;
    private List<Eagle> eagleList;
    private List<Flag> flagList;

    public GameView() {
        super("Eagle vs Shark");

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 850);

        Container contentPane = getContentPane();

        boardView = new BoardView(this);
        boardView.setLocation(0, 0);
        contentPane.add(boardView);

        turnPanel = new TurnPanel(this, this, this.getBackground());
        turnPanel.setLocation(650, 20);
        turnPanel.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(turnPanel);

        setVisible(true);
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Up":
                gameController.movePiece(turnPanel.getPieceJListSelectedItem(), 0);
                break;
            case "Down":
                gameController.movePiece(turnPanel.getPieceJListSelectedItem(), 1);
                break;
            case "Left":
                gameController.movePiece(turnPanel.getPieceJListSelectedItem(), 2);
                break;
            case "Right":
                gameController.movePiece(turnPanel.getPieceJListSelectedItem(), 3);
                break;
            case "Next Turn":
                gameController.nextTurn();
                break;
        }
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public TurnPanel getTurnPanel() {
        return turnPanel;
    }

    public List<Shark> getSharkList() {
        return sharkList;
    }

    public void setSharkList(List<Shark> sharkList) {
        this.sharkList = sharkList;

    }

    public List<Eagle> getEagleList() {
        return eagleList;
    }

    public void setEagleList(List<Eagle> eagleList) {
        this.eagleList = eagleList;

    }

    public List<Flag> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<Flag> flagList) {
        this.flagList = flagList;

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

}
