package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import controller.GameController;
import model.Eagle;
import model.Flag;
import model.Shark;

public class GameView
        extends JFrame
        implements ActionListener {

    private static final int PANEL_MARGIN = 5;
    private final BoardView boardView;
    private final TurnPanel turnPanel;
    private final MovementPanel movementPanel;
    private final AbilityPanel abilityPanel;
    private final TimePanel timePanel;
    private GameController gameController;

    private List<Shark> sharkList;
    private List<Eagle> eagleList;
    private List<Flag> flagList;

    public GameView() {
        super("Eagle vs Shark");

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 850);

        Container contentPane = getContentPane();

        boardView = new BoardView(this);
        boardView.setLocation(0, 0);
        boardView.setSize(590, 650);
        contentPane.add(boardView);

        timePanel = new TimePanel();
        timePanel.setLocation(boardView.getWidth() + PANEL_MARGIN, BoardView.getBoardMargin());
        timePanel.setSize(150, 30);
        timePanel.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(timePanel);

        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.setLocation(boardView.getWidth() + PANEL_MARGIN + timePanel.getWidth() + PANEL_MARGIN, BoardView.getBoardMargin());
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(this);
        contentPane.add(nextTurnButton);

        turnPanel = new TurnPanel(this, this, getBackground());
        turnPanel.setLocation(boardView.getWidth() + PANEL_MARGIN, BoardView.getBoardMargin() + timePanel.getHeight() + PANEL_MARGIN);
        turnPanel.setSize(320, 180);
        turnPanel.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(turnPanel);

        movementPanel = new MovementPanel(this, this, getBackground());
        movementPanel.setLocation(boardView.getWidth() + PANEL_MARGIN,
                BoardView.getBoardMargin() + timePanel.getHeight() + PANEL_MARGIN + turnPanel.getHeight() + PANEL_MARGIN);
        movementPanel.setSize(320, 500);
        movementPanel.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(movementPanel);

        abilityPanel = new AbilityPanel(this, this, getBackground());
        abilityPanel.setLocation(boardView.getWidth() + PANEL_MARGIN + turnPanel.getWidth() + PANEL_MARGIN,
                BoardView.getBoardMargin());
        abilityPanel.setSize(160, 500);
        abilityPanel.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(abilityPanel);

        TextArea rulesTextArea = new TextArea("placeholder text - to be added", 1, 1);
        rulesTextArea.setSize(540, 150);
        rulesTextArea.setLocation(BoardView.getBoardMargin(),
                boardView.getHeight() + PANEL_MARGIN);
        rulesTextArea.setEditable(false);
        contentPane.add(rulesTextArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if ("Next Turn".equals(actionCommand)) {
            gameController.nextTurn();
        } else if ("Stun".equals(actionCommand)) {
            gameController.useAbility(abilityPanel.getPieceJListSelectedItem(), e.getActionCommand());
        } else if (actionCommand.contains("Eagle")) {
            movementPanel.updateMoveJList(turnPanel.getSelectedPieceIndex(), gameController.getEaglePiece(turnPanel.getSelectedPieceIndex()));
        } else if (actionCommand.contains("Shark")) {
            movementPanel.updateMoveJList(turnPanel.getSelectedPieceIndex(), gameController.getSharkPiece(turnPanel.getSelectedPieceIndex()));
        } else if (actionCommand.equals("Move")) {
            gameController.movePiece(turnPanel.getSelectedPieceIndex(), movementPanel.getMovementCoord());
        }
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public TurnPanel getTurnPanel() {
        return turnPanel;
    }

    public AbilityPanel getAbilityPanel() {
        return abilityPanel;
    }

    public TimePanel getTimePanel() {
        return timePanel;
    }

    List<Shark> getSharkList() {
        return sharkList;
    }

    public void setSharkList(List<Shark> sharkList) {
        this.sharkList = sharkList;

    }

    List<Eagle> getEagleList() {
        return eagleList;
    }

    public void setEagleList(List<Eagle> eagleList) {
        this.eagleList = eagleList;

    }

    List<Flag> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<Flag> flagList) {
        this.flagList = flagList;

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

}
