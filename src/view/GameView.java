package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.GameController;
import model.Eagle;
import model.Flag;
import model.Island;
import model.MovablePiece;
import model.Shark;
import model.Square;

public class GameView
        extends JFrame
        implements ActionListener {

    private final BoardPanel BOARD_PANEL;
    private final PiecePanel PIECE_PANEL;
    private final MovementPanel MOVEMENT_PANEL;
    private final AbilityPanel ABILITY_PANEL;
    private final TimePanel TIME_PANEL;
    private GameController gameController;

    public GameView() {
        super("Eagle vs Shark");

        int panelMargin = 5;

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 820);

        Container contentPane = getContentPane();

        BOARD_PANEL = new BoardPanel();
        BOARD_PANEL.setLocation(0, 0);
        BOARD_PANEL.setSize(590, 650);
        contentPane.add(BOARD_PANEL);

        int boardViewX = BOARD_PANEL.getWidth();

        int timePanelX = boardViewX + panelMargin;
        TIME_PANEL = new TimePanel();
        contentPane.add(addPanel(TIME_PANEL, timePanelX, BoardPanel.getBoardMargin(), 150, 30));

        int nexTurnButtonX = timePanelX + TIME_PANEL.getWidth() + panelMargin;
        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.setLocation(nexTurnButtonX, BoardPanel.getBoardMargin());
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(this);
        contentPane.add(nextTurnButton);

        int turnPanelX = boardViewX + panelMargin;
        int turnPanelY = BoardPanel.getBoardMargin() + TIME_PANEL.getHeight() + panelMargin;
        PIECE_PANEL = new PiecePanel(this, this);
        contentPane.add(addPanel(PIECE_PANEL, turnPanelX, turnPanelY, 220, 660));

        int movementPanelX = turnPanelX + PIECE_PANEL.getWidth() + panelMargin;
        MOVEMENT_PANEL = new MovementPanel(this, getBackground());
        contentPane.add(addPanel(MOVEMENT_PANEL, movementPanelX, turnPanelY, 150, PIECE_PANEL.getHeight()));

        int abilityPanelX = movementPanelX + MOVEMENT_PANEL.getWidth() + panelMargin;
        ABILITY_PANEL = new AbilityPanel(this, this, getBackground());
        contentPane.add(addPanel(ABILITY_PANEL, abilityPanelX, turnPanelY, 180, PIECE_PANEL.getHeight()));

        int rulesTextAreaX = abilityPanelX + ABILITY_PANEL.getWidth() + panelMargin;
        TextArea rulesTextArea = new TextArea("placeholder text - to be added", 1, 1);
        rulesTextArea.setSize(220, PIECE_PANEL.getHeight());
        rulesTextArea.setLocation(rulesTextAreaX, turnPanelY);
        rulesTextArea.setEditable(false);
        contentPane.add(rulesTextArea);

        setVisible(true);
    }

    private JPanel addPanel(JPanel panel, int panelX, int panelY, int width, int height) {
        panel.setLocation(panelX, panelY);
        panel.setSize(width, height);
        panel.setBorder(new LineBorder(Color.BLACK));

        return panel;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        if ("Next Turn".equals(actionCommand)) {
            gameController.updateNextTurn();
        } else if ("STUN".equals(actionCommand) || "SLOW".equals(actionCommand)) {
            ABILITY_PANEL.selectedAbilityOnEnemy(actionCommand, gameController.getOtherPieceList());
        } else if ("SPEED".equals(actionCommand)) {
            ABILITY_PANEL.selectedAbilityOnAlly(actionCommand, gameController.getCurrentPieceList());
        } else if (actionCommand.contains("Use")) {
            gameController.useAbility(ABILITY_PANEL.getPieceJListSelectedItem(), actionCommand);
            ABILITY_PANEL.disableAbilityUI();
        } else if (actionCommand.contains("Eagle")) {
            MOVEMENT_PANEL.updateMoveJList(gameController.getEaglePiece(PIECE_PANEL.getSelectedPieceIndex()));
        } else if (actionCommand.contains("Shark")) {
            MOVEMENT_PANEL.updateMoveJList(gameController.getSharkPiece(PIECE_PANEL.getSelectedPieceIndex()));
        } else if (actionCommand.equals("Move")) {
            gameController.movePiece(PIECE_PANEL.getSelectedPieceIndex(), MOVEMENT_PANEL.getMovementCoord());
        }
    }

    public void initializeGameView(GameController gameController, Square[][] squareArray,
                                   List<Eagle> eagleList, List<Shark> sharkList,
                                   List<Flag> flagList, List<Island> islandList,
                                   int numberOfButtons,
                                   List<? extends MovablePiece> currentPieceList) {
        this.gameController = gameController;
        BOARD_PANEL.setBoard(squareArray, eagleList, sharkList, flagList, islandList);
        PIECE_PANEL.updateTurnText();
        PIECE_PANEL.createButtons(currentPieceList);
        PIECE_PANEL.setButtonText(currentPieceList);
        ABILITY_PANEL.setAbilityButtonText(currentPieceList);
        TIME_PANEL.setGameController(gameController);
    }

    public void updateViewAfterPieceMove(List<? extends MovablePiece> currentPieceList, MovablePiece movablePiece) {
        repaint();
        PIECE_PANEL.disableAllPieceButton();
        PIECE_PANEL.updateTurnText();
        PIECE_PANEL.setButtonText(currentPieceList);
        MOVEMENT_PANEL.hideMovementUI();
        ABILITY_PANEL.hideUnusableAbility(movablePiece);
    }

    public void updateNextTurn(List<? extends MovablePiece> currentPieceList) {
        PIECE_PANEL.updateTurnText();
        PIECE_PANEL.createButtons(currentPieceList);
        MOVEMENT_PANEL.hideMovementUI();
        ABILITY_PANEL.getPIECE_JLIST().setVisible(false);
        ABILITY_PANEL.resetUseAbilityButtonText();
        ABILITY_PANEL.setUseAbilityButton(false);
        ABILITY_PANEL.enableAbilityUI(currentPieceList);
        TIME_PANEL.resetTimer();
    }

    public void setCurrentPlayer(boolean isEagleTurn) {
        PIECE_PANEL.setIsEaglePlayer(isEagleTurn);
    }

    public void setAfterUseText(MovablePiece movablePiece) {
        ABILITY_PANEL.setAfterUseText(movablePiece);
    }

    public void hideMovementUI() {
        MOVEMENT_PANEL.hideMovementUI();
    }

    public void hideUnmovablePiece(String abilityUsed, List<? extends MovablePiece> currentPieceList) {
        PIECE_PANEL.hideUnmovablePiece(abilityUsed, currentPieceList);
    }
}
