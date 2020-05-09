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
import model.Player;
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

        int saveGameButtonX = nexTurnButtonX + nextTurnButton.getWidth() + panelMargin;
        JButton saveGameButton = new JButton("Save Game");
        saveGameButton.setLocation(saveGameButtonX, BoardPanel.getBoardMargin());
        saveGameButton.setSize(130, 30);
        saveGameButton.addActionListener(this);
        contentPane.add(saveGameButton);

        int loadGameButtonX = saveGameButtonX + saveGameButton.getWidth() + panelMargin;
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setLocation(loadGameButtonX, BoardPanel.getBoardMargin());
        loadGameButton.setSize(130, 30);
        loadGameButton.addActionListener(this);
        contentPane.add(loadGameButton);

        int turnPanelX = boardViewX + panelMargin;
        int turnPanelY = BoardPanel.getBoardMargin() + TIME_PANEL.getHeight() + panelMargin;
        PIECE_PANEL = new PiecePanel(this);
        contentPane.add(addPanel(PIECE_PANEL, turnPanelX, turnPanelY, 280, 660));

        int movementPanelX = turnPanelX + PIECE_PANEL.getWidth() + panelMargin;
        MOVEMENT_PANEL = new MovementPanel(this, getBackground());
        contentPane.add(addPanel(MOVEMENT_PANEL, movementPanelX, turnPanelY, 140, PIECE_PANEL.getHeight()));

        int abilityPanelX = movementPanelX + MOVEMENT_PANEL.getWidth() + panelMargin;
        ABILITY_PANEL = new AbilityPanel(this, getBackground());
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

    public void initializeGameView(Square[][] squareArray,
                                   List<Eagle> eagleList, List<Shark> sharkList,
                                   List<Flag> flagList, List<Island> islandList,
                                   List<? extends MovablePiece> currentPieceList,
                                   boolean eagleTurn) {
        BOARD_PANEL.setBoard(squareArray, eagleList, sharkList, flagList, islandList);

        PIECE_PANEL.updateTurnText(eagleTurn);
        PIECE_PANEL.createButtons(currentPieceList);
        PIECE_PANEL.setButtonText(currentPieceList);

        ABILITY_PANEL.createButtons(currentPieceList);
        ABILITY_PANEL.setAbilityButtonText(currentPieceList);

        TIME_PANEL.setGameController(gameController);

        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        if ("Next Turn".equals(actionCommand)) {
            gameController.updateNextTurn();
        } else if ("STUN".equals(actionCommand) || "SLOW".equals(actionCommand)) {
            ABILITY_PANEL.selectedAbility(actionCommand, gameController.getEnemyPieceList());
        } else if ("SPEED".equals(actionCommand) || "SHIELD".equals(actionCommand)) {
            ABILITY_PANEL.selectedAbility(actionCommand, gameController.getAllyPieceList());
        } else if (actionCommand.contains("Use")) {
            gameController.useAbility(ABILITY_PANEL.getPieceJListSelectedItem(), actionCommand);
        } else if (actionCommand.contains("Moving Mode") || actionCommand.contains("Ability Mode")) {
            int selectedButtonIndex = PIECE_PANEL.getSelectedButtonIndex();
            PIECE_PANEL.changeButtonMode(gameController.getAllyPieceList());
            ABILITY_PANEL.changeAbilityButtonStatus(selectedButtonIndex);
            gameController.updateMovingMode(actionCommand, selectedButtonIndex);
        } else if (actionCommand.contains("Eagle") || actionCommand.contains("Shark")) {
            int selectedButtonIndex = PIECE_PANEL.getSelectedButtonIndex();
            MovablePiece movablePiece = gameController.getMovablePiece(actionCommand, selectedButtonIndex);
            MOVEMENT_PANEL.updateMoveJList(movablePiece);
            BOARD_PANEL.showValidSquares(movablePiece);
        } else if ("Move".equals(actionCommand)) {
            gameController.movePiece(PIECE_PANEL.getSelectedButtonIndex(), MOVEMENT_PANEL.getMovementCoord());
        } else if ("Save Game".equals(actionCommand)) {
            gameController.saveGame();
        } else if ("Load Game".equals(actionCommand)) {
            gameController.loadGame();
        }
    }

    public void updateViewAfterPieceMove(List<? extends MovablePiece> currentPieceList, MovablePiece movablePiece) {
        BOARD_PANEL.removeMovablePiece();
        PIECE_PANEL.disableAllButton();

        PIECE_PANEL.setButtonText(currentPieceList);
        MOVEMENT_PANEL.hideMovementUI();
        ABILITY_PANEL.updateButtonText(movablePiece);

        revalidate();
        repaint();

    }

    public void updateNextTurn(List<? extends MovablePiece> currentPieceList, boolean eagleTurn) {
        BOARD_PANEL.removeMovablePiece();

        PIECE_PANEL.updateTurnText(eagleTurn);
        PIECE_PANEL.createButtons(currentPieceList);

        MOVEMENT_PANEL.hideMovementUI();

        ABILITY_PANEL.setAFFECTED_PIECE();
        ABILITY_PANEL.removeLastAbilityUsed();
        ABILITY_PANEL.getPIECE_JLIST().setVisible(false);
        ABILITY_PANEL.createButtons(currentPieceList);
        ABILITY_PANEL.resetUseAbilityButtonText();
        ABILITY_PANEL.setUseAbilityButton(false);
        ABILITY_PANEL.enableAbilityUI(currentPieceList);

        TIME_PANEL.resetTimer();

        revalidate();
        repaint();
    }

    public void setAfterAbilityUseText(MovablePiece movablePiece) {
        ABILITY_PANEL.setAfterAbilityUseText(movablePiece);
    }

    public void disableUIAfterAbilityUse(String abilityUsed, List<? extends MovablePiece> currentPieceList) {
        PIECE_PANEL.hideUnmovablePiece(abilityUsed, currentPieceList, ABILITY_PANEL.getLastAbilityUsedIndex());
        ABILITY_PANEL.disableAbilityUI();
    }

    public void hideMovementUI() {
        MOVEMENT_PANEL.hideMovementUI();

        revalidate();
        repaint();
    }

    public void loadGame(Player player) {
        if (player.isPieceMoved()) {
            PIECE_PANEL.disableAllButton();
        }

        if (player.getAbilityUsed() != null) {
            ABILITY_PANEL.disableAbilityUI();
        }

        if (player.getPieceModeToggledIndex() != -1) {
            PIECE_PANEL.setSelectedButtonIndex(player.getPieceModeToggledIndex());
            PIECE_PANEL.changeButtonMode(player.getMOVABLEPIECE_LIST());
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void removeMoveablePiece() {
        BOARD_PANEL.removeMovablePiece();
    }

}
