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
    private final BoardView BOARDVIEW;
    private final TurnPanel TURN_PANEL;
    private final MovementPanel MOVEMENT_PANEL;
    private final AbilityPanel ABILITY_PANEL;
    private final TimePanel TIME_PANEL;
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

        BOARDVIEW = new BoardView(this);
        BOARDVIEW.setLocation(0, 0);
        BOARDVIEW.setSize(590, 650);
        contentPane.add(BOARDVIEW);

        TIME_PANEL = new TimePanel();
        TIME_PANEL.setLocation(BOARDVIEW.getWidth() + PANEL_MARGIN, BoardView.getBoardMargin());
        TIME_PANEL.setSize(150, 30);
        TIME_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(TIME_PANEL);

        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.setLocation(BOARDVIEW.getWidth() + PANEL_MARGIN + TIME_PANEL.getWidth() + PANEL_MARGIN, BoardView.getBoardMargin());
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(this);
        contentPane.add(nextTurnButton);

        TURN_PANEL = new TurnPanel(this, this);
        TURN_PANEL.setLocation(BOARDVIEW.getWidth() + PANEL_MARGIN, BoardView.getBoardMargin() + TIME_PANEL.getHeight() + PANEL_MARGIN);
        TURN_PANEL.setSize(320, 180);
        TURN_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(TURN_PANEL);

        MOVEMENT_PANEL = new MovementPanel(this, this, getBackground());
        MOVEMENT_PANEL.setLocation(BOARDVIEW.getWidth() + PANEL_MARGIN,
                BoardView.getBoardMargin() + TIME_PANEL.getHeight() + PANEL_MARGIN + TURN_PANEL.getHeight() + PANEL_MARGIN);
        MOVEMENT_PANEL.setSize(320, 500);
        MOVEMENT_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(MOVEMENT_PANEL);

        ABILITY_PANEL = new AbilityPanel(this, this, getBackground());
        ABILITY_PANEL.setLocation(BOARDVIEW.getWidth() + PANEL_MARGIN + TURN_PANEL.getWidth() + PANEL_MARGIN,
                BoardView.getBoardMargin());
        ABILITY_PANEL.setSize(160, 500);
        ABILITY_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(ABILITY_PANEL);

        TextArea rulesTextArea = new TextArea("placeholder text - to be added", 1, 1);
        rulesTextArea.setSize(540, 150);
        rulesTextArea.setLocation(BoardView.getBoardMargin(),
                BOARDVIEW.getHeight() + PANEL_MARGIN);
        rulesTextArea.setEditable(false);
        contentPane.add(rulesTextArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if ("Next Turn".equals(actionCommand)) {
            gameController.updateNextTurn();
        } else if ("Stun".equals(actionCommand)) {
            gameController.useAbility(ABILITY_PANEL.getPieceJListSelectedItem(), e.getActionCommand());
        } else if (actionCommand.contains("Eagle")) {
            MOVEMENT_PANEL.updateMoveJList(TURN_PANEL.getSelectedPieceIndex(), gameController.getEaglePiece(TURN_PANEL.getSelectedPieceIndex()));
        } else if (actionCommand.contains("Shark")) {
            MOVEMENT_PANEL.updateMoveJList(TURN_PANEL.getSelectedPieceIndex(), gameController.getSharkPiece(TURN_PANEL.getSelectedPieceIndex()));
        } else if (actionCommand.equals("Move")) {
            gameController.movePiece(TURN_PANEL.getSelectedPieceIndex(), MOVEMENT_PANEL.getMovementCoord());
        }
    }

    public BoardView getBOARDVIEW() {
        return BOARDVIEW;
    }

    public TurnPanel getTURN_PANEL() {
        return TURN_PANEL;
    }

    public MovementPanel getMOVEMENT_PANEL() {
        return MOVEMENT_PANEL;
    }

    public AbilityPanel getABILITY_PANEL() {
        return ABILITY_PANEL;
    }

    public TimePanel getTIME_PANEL() {
        return TIME_PANEL;
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
