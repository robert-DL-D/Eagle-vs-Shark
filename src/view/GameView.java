package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Eagle;
import model.Flag;
import model.Island;
import model.MovablePiece;
import model.Player;
import model.Shark;
import model.Square;
import model.StringText;

public class GameView
        extends JFrame {

    private final BoardPanel BOARD_PANEL;
    private final PiecePanel PIECE_PANEL;
    private final MovementPanel MOVEMENT_PANEL;
    private final AbilityPanel ABILITY_PANEL;
    private final TimePanel TIME_PANEL;

    public GameView(ActionListener actionListener) {
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
        JButton nextTurnButton = new JButton(StringText.NEXT_TURN);
        nextTurnButton.setLocation(nexTurnButtonX, BoardPanel.getBoardMargin());
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(actionListener);
        contentPane.add(nextTurnButton);

        int saveGameButtonX = nexTurnButtonX + nextTurnButton.getWidth() + panelMargin;
        JButton saveGameButton = new JButton(StringText.SAVE_GAME);
        saveGameButton.setLocation(saveGameButtonX, BoardPanel.getBoardMargin());
        saveGameButton.setSize(130, 30);
        saveGameButton.addActionListener(actionListener);
        contentPane.add(saveGameButton);

        int loadGameButtonX = saveGameButtonX + saveGameButton.getWidth() + panelMargin;
        JButton loadGameButton = new JButton(StringText.LOAD_GAME);
        loadGameButton.setLocation(loadGameButtonX, BoardPanel.getBoardMargin());
        loadGameButton.setSize(130, 30);
        loadGameButton.addActionListener(actionListener);
        contentPane.add(loadGameButton);

        int turnPanelX = boardViewX + panelMargin;
        int turnPanelY = BoardPanel.getBoardMargin() + TIME_PANEL.getHeight() + panelMargin;
        PIECE_PANEL = new PiecePanel(actionListener);
        contentPane.add(addPanel(PIECE_PANEL, turnPanelX, turnPanelY, 280, 660));

        int movementPanelX = turnPanelX + PIECE_PANEL.getWidth() + panelMargin;
        MOVEMENT_PANEL = new MovementPanel(actionListener, getBackground());
        contentPane.add(addPanel(MOVEMENT_PANEL, movementPanelX, turnPanelY, 140, PIECE_PANEL.getHeight()));

        int abilityPanelX = movementPanelX + MOVEMENT_PANEL.getWidth() + panelMargin;
        ABILITY_PANEL = new AbilityPanel(actionListener, getBackground());
        contentPane.add(addPanel(ABILITY_PANEL, abilityPanelX, turnPanelY, 180, PIECE_PANEL.getHeight()));

        int rulesTextAreaX = abilityPanelX + ABILITY_PANEL.getWidth() + panelMargin;
        TextArea rulesTextArea = new TextArea("\n" +
        		"Pieces Movement\n" + 
        		"\n" + 
        		"Red Shark \n" + 
        		"- move ↖↙↗↘ by 1, \n" + 
        		"and either ↑↓←→ by 1\n" + 
        		"\n" + 
        		"Green Shark \n" + 
        		"- move ↖↙↗↘ by 1 or 2 \n" + 
        		"\n" + 
        		"\n" + 
        		"Blue Shark \n" + 
        		"- move ↑↓←→ by 1 or 2, \n" + 
        		"but ↖↙↗↘ by 1\n" + 
        		"\n" + 
        		"\n" + 
        		"Red Eagle \n" + 
        		"- move ↑↓←→ by 1 or 2\n" + 
        		"\n" + 
        		"\n" + 
        		"Green Eagle \n" + 
        		"- move ↖↙↗↘ by 2, \n" + 
        		"or move ↑↓←→ by 1 \n" + 
        		"\n" + 
        		"\n" + 
        		"Blue Shark \n" + 
        		"- move 1 space \n" + 
        		"to any direction\n" + 
        		"\n" + 
        		"\n" + 
        		"Dont’t forget \n" + 
        		"your clock is ticking! ;)", 1, 1);
        rulesTextArea.setSize(220, PIECE_PANEL.getHeight());
        rulesTextArea.setLocation(rulesTextAreaX, turnPanelY);
        rulesTextArea.setEditable(false);
        rulesTextArea.setForeground(Color.white);
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
                                   boolean eagleTurn, String turnLimit) {
        BOARD_PANEL.setBoard(squareArray, eagleList, sharkList, flagList, islandList);

        PIECE_PANEL.updateTurnText(eagleTurn);
        PIECE_PANEL.createButtons(currentPieceList);
        PIECE_PANEL.setButtonText(currentPieceList);

        ABILITY_PANEL.createButtons(currentPieceList);

        TIME_PANEL.setEagleTurn(eagleTurn);
        TIME_PANEL.setTurnLimit(turnLimit);

        revalidate();
        repaint();
    }

    public void updateViewAfterPieceMove(List<? extends MovablePiece> currentPieceList, int selectedButtonIndex) {
        BOARD_PANEL.removeMovablePiece();
        PIECE_PANEL.disableAllButton();
        PIECE_PANEL.setButtonText(currentPieceList);
        MOVEMENT_PANEL.hideMovementUI();
        ABILITY_PANEL.updateButtonText(currentPieceList.get(selectedButtonIndex));

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
        ABILITY_PANEL.setUSE_ABILITY_BUTTON(false);

        TIME_PANEL.setEagleTurn(eagleTurn);
        TIME_PANEL.resetTimer();

        revalidate();
        repaint();
    }

    public void updateViewAfterAbilityUse(MovablePiece movablePiece, String abilityUsed, List<? extends MovablePiece> allyPieceList) {
        PIECE_PANEL.hideUnmovablePiece(abilityUsed, allyPieceList, ABILITY_PANEL.getLastAbilityUsedIndex());
        ABILITY_PANEL.updateAbilityPanelAfterAbilityUse(movablePiece);
    }

    public void hideMovementUI() {
        MOVEMENT_PANEL.hideMovementUI();

        revalidate();
        repaint();
    }

    public void selectedAbility(String actionCommand, List<? extends MovablePiece> movablePieceList) {
        ABILITY_PANEL.selectedAbility(actionCommand, movablePieceList);
    }

    public void loadGame(Player<? extends MovablePiece> player, int turnTime) {
        if (player.isPieceMoved()) {
            PIECE_PANEL.disableAllButton();
        }

        if (player.getAbilityUsed() != null) {
            for (MovablePiece movablePiece : player.getMOVABLEPIECE_LIST()) {
                if (player.getAbilityUsed().equals(movablePiece.getAbility().toString())) {
                    ABILITY_PANEL.setLastAbilityUsed(player.getAbilityUsed());
                    updateViewAfterAbilityUse(movablePiece, player.getAbilityUsed(), player.getMOVABLEPIECE_LIST());
                    break;
                }
            }

        }

        int pieceModeToggledIndex = player.getPieceModeToggledIndex();

        if (pieceModeToggledIndex != -1) {
            PIECE_PANEL.setSelectedButtonIndex(pieceModeToggledIndex);
            PIECE_PANEL.changeButtonMode(player.getMOVABLEPIECE_LIST());
            ABILITY_PANEL.setAbilityButtonStatus(pieceModeToggledIndex);
        }

        TIME_PANEL.setTurnTime(turnTime);
    }

    public void movePiece(MovablePiece movablePiece) {
        MOVEMENT_PANEL.updateMoveJList(movablePiece);
        BOARD_PANEL.showValidSquares(movablePiece);
    }

    public int getSelectedButtonIndex() {
        return PIECE_PANEL.getSelectedButtonIndex();
    }

    public void removeMoveablePiece() {
        BOARD_PANEL.removeMovablePiece();
    }

    public void togglePieceMode(int selectedButtonIndex, List<? extends MovablePiece> movablePieceList) {
        PIECE_PANEL.changeButtonMode(movablePieceList);
        ABILITY_PANEL.setAbilityButtonStatus(selectedButtonIndex);
    }

    public int getPieceJListSelectedItem() {
        return ABILITY_PANEL.getPieceJListSelectedItem();
    }

    public int[] getMovementCoord() {
        return MOVEMENT_PANEL.getMovementCoord();
    }

    public int getTurnTime() {
        return TIME_PANEL.getTurnTime();
    }

    public String getTURN_LIMIT() {
        return TIME_PANEL.getTurnLimit();
    }
}
