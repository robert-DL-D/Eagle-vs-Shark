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
import model.GameModel;
import model.Player;
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
        setSize(1400, 850);

        Container contentPane = getContentPane();

        BOARDVIEW = new BoardView(this);
        BOARDVIEW.setLocation(0, 0);
        BOARDVIEW.setSize(590, 650);
        contentPane.add(BOARDVIEW);

        int boardViewX = BOARDVIEW.getWidth();

        int timePanelX = boardViewX + PANEL_MARGIN;
        TIME_PANEL = new TimePanel();
        TIME_PANEL.setLocation(timePanelX, BoardView.getBoardMargin());
        TIME_PANEL.setSize(150, 30);
        TIME_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(TIME_PANEL);

        int nexTurnButtonX = timePanelX + TIME_PANEL.getWidth() + PANEL_MARGIN;
        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.setLocation(nexTurnButtonX, BoardView.getBoardMargin());
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(this);
        contentPane.add(nextTurnButton);

        int turnPanelX = boardViewX + PANEL_MARGIN;
        int turnPanelY = BoardView.getBoardMargin() + TIME_PANEL.getHeight() + PANEL_MARGIN;
        TURN_PANEL = new TurnPanel(this, this);
        TURN_PANEL.setLocation(turnPanelX, turnPanelY);
        TURN_PANEL.setSize(150, 360);
        TURN_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(TURN_PANEL);

        int movementPanelX = turnPanelX + TURN_PANEL.getWidth() + PANEL_MARGIN;
        MOVEMENT_PANEL = new MovementPanel(this, this, getBackground());
        MOVEMENT_PANEL.setLocation(movementPanelX, turnPanelY);
        MOVEMENT_PANEL.setSize(150, 360);
        MOVEMENT_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(MOVEMENT_PANEL);

        int abilityPanelX = movementPanelX + MOVEMENT_PANEL.getWidth() + PANEL_MARGIN;
        ABILITY_PANEL = new AbilityPanel(this, this, getBackground());
        ABILITY_PANEL.setLocation(abilityPanelX, turnPanelY);
        ABILITY_PANEL.setSize(200, 360);
        ABILITY_PANEL.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(ABILITY_PANEL);

        int rulesTextAreaY = turnPanelY + TURN_PANEL.getHeight() + PANEL_MARGIN;
        TextArea rulesTextArea = new TextArea("placeholder text - to be added", 1, 1);
        rulesTextArea.setSize(470, 200);
        rulesTextArea.setLocation(boardViewX + PANEL_MARGIN, rulesTextAreaY);
        rulesTextArea.setEditable(false);
        contentPane.add(rulesTextArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        if ("Next Turn".equals(actionCommand)) {
            gameController.updateNextTurn();
        } else if ("STUN".equals(actionCommand)) {
            ABILITY_PANEL.selectedStun(actionCommand);
        } else if (actionCommand.contains("Use Ability")) {
            gameController.useAbility(ABILITY_PANEL.getPieceJListSelectedItem(), actionEvent.getActionCommand());
        } else if (actionCommand.contains("Eagle")) {
            MOVEMENT_PANEL.updateMoveJList(gameController.getEaglePiece(TURN_PANEL.getSelectedPieceIndex()));
        } else if (actionCommand.contains("Shark")) {
            MOVEMENT_PANEL.updateMoveJList(gameController.getSharkPiece(TURN_PANEL.getSelectedPieceIndex()));
        } else if (actionCommand.equals("Move")) {
            gameController.movePiece(TURN_PANEL.getSelectedPieceIndex(), MOVEMENT_PANEL.getMovementCoord());
        }
    }

    public void initializeGameView(GameController gameController, GameModel gameModel) {
        this.gameController = gameController;
        BOARDVIEW.setSquares(gameModel.getSQUARE_ARRAY());
        sharkList = gameModel.getSHARK_PLAYER().getPIECE_LIST();
        eagleList = gameModel.getEAGLE_PLAYER().getPIECE_LIST();
        flagList = gameModel.getFLAG_LIST();
        TURN_PANEL.updateTurnText();
        TURN_PANEL.setButtonText();
        //getABILITY_PANEL().updatePieceJList();
        ABILITY_PANEL.setAbilityButtonText();
        TIME_PANEL.setGameController(gameController);
    }

    public void updateViewAfterPieceMove(Player<Eagle> eaglePlayer, Player<Shark> sharkPlayer) {
        repaint();
        eagleList = eaglePlayer.getPIECE_LIST();
        sharkList = sharkPlayer.getPIECE_LIST();
        TURN_PANEL.disableAllPieceButton();
        TURN_PANEL.updateTurnText();
        TURN_PANEL.setButtonText();
    }

    public void updateNextTurn() {
        TURN_PANEL.updateTurnText();
        TURN_PANEL.setButtonText();
        TURN_PANEL.setEnabledButton();
        MOVEMENT_PANEL.getMOVE_JLIST().setVisible(false);
        ABILITY_PANEL.getPIECE_JLIST().setVisible(false);
        ABILITY_PANEL.resetAbilityButtonText();
        ABILITY_PANEL.setUseAbilityButton(false);
        TIME_PANEL.resetTimer();
    }

    public void setCurrentPlayer(boolean isEagleTurn) {
        TURN_PANEL.setIsEaglePlayer(isEagleTurn);
        ABILITY_PANEL.setIsEaglePlayer(isEagleTurn);
    }

    List<Shark> getSharkList() {
        return sharkList;
    }

    List<Eagle> getEagleList() {
        return eagleList;
    }

    List<Flag> getFlagList() {
        return flagList;
    }

}
