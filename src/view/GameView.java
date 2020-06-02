package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.BoardConfig;
import model.Eagle;
import model.Flag;
import model.Island;
import model.MovablePiece;
import model.Player;
import model.Shark;
import model.Square;

public class GameView
        extends JFrame {

    private final BoardPanel BOARD_PANEL;
    private final PiecePanel PIECE_PANEL;
    private final AbilityPanel ABILITY_PANEL;
    private final TopPanel TOP_PANEL;

    public GameView(ActionListener actionListener, MouseListener mouseListener) {
        super("Eagle vs Shark");

        int panelMargin = 5;

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 900);

        Container contentPane = getContentPane();

        BOARD_PANEL = new BoardPanel(mouseListener);
        BOARD_PANEL.setLocation(0, 0);
        BOARD_PANEL.setSize(720, 900);
        contentPane.add(BOARD_PANEL);

        int boardViewX = BOARD_PANEL.getWidth();

        int topPanelX = boardViewX + panelMargin;
        TOP_PANEL = new TopPanel(this,actionListener);
        contentPane.add(addPanel(TOP_PANEL, topPanelX, BoardPanel.getBoardMargin(), 450, 40));

        int piecePanelX = boardViewX + panelMargin;
        int piecePanelY = BoardPanel.getBoardMargin() + TOP_PANEL.getHeight() + panelMargin;
        PIECE_PANEL = new PiecePanel(actionListener);
        contentPane.add(addPanel(PIECE_PANEL, piecePanelX, piecePanelY, 240, 500));

        int abilityPanelX = piecePanelX + PIECE_PANEL.getWidth() + panelMargin;
        ABILITY_PANEL = new AbilityPanel(actionListener, getBackground());
        contentPane.add(addPanel(ABILITY_PANEL, abilityPanelX, piecePanelY, 180, PIECE_PANEL.getHeight()));

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
        rulesTextArea.setLocation(rulesTextAreaX, piecePanelY);
        rulesTextArea.setEditable(false);
        rulesTextArea.setBackground(getBackground());
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
        PIECE_PANEL.setLabelText(currentPieceList);

        ABILITY_PANEL.createButtons(currentPieceList);

        TOP_PANEL.setEagleTurn(eagleTurn);
        TOP_PANEL.setTurnLimit(BoardConfig.TURN_LIMIT);

        revalidate();
        repaint();
    }

    public void updateViewAfterPieceMove(List<? extends MovablePiece> currentPieceList, MovablePiece movablePiece) {

        BOARD_PANEL.removeMovablePiece();
        BOARD_PANEL.updateMovablePieceCoord();
        PIECE_PANEL.disableAllButton();
        PIECE_PANEL.setLabelText(currentPieceList);
        ABILITY_PANEL.updateButtonText(movablePiece);

        revalidate();
        repaint();

    }

    public void updateNextTurn(List<? extends MovablePiece> currentPieceList, boolean eagleTurn) {
        BOARD_PANEL.removeMovablePiece();

        PIECE_PANEL.updateTurnText(eagleTurn);
        PIECE_PANEL.createButtons(currentPieceList);

        ABILITY_PANEL.setAFFECTED_PIECE();
        ABILITY_PANEL.removeLastAbilityUsed();
        ABILITY_PANEL.getPIECE_JLIST().setVisible(false);
        ABILITY_PANEL.createButtons(currentPieceList);
        ABILITY_PANEL.resetUseAbilityButtonText();
        ABILITY_PANEL.setUSE_ABILITY_BUTTON(false);

        TOP_PANEL.setEagleTurn(eagleTurn);
        TOP_PANEL.resetTimer();

        revalidate();
        repaint();
    }

    public void updateViewAfterAbilityUse(MovablePiece movablePiece, String abilityUsed, List<? extends MovablePiece> allyPieceList) {
        PIECE_PANEL.hideUnmovablePiece(abilityUsed, allyPieceList, ABILITY_PANEL.getLastAbilityUsedIndex());
        PIECE_PANEL.setLabelText(allyPieceList);
        ABILITY_PANEL.updateAbilityPanelAfterAbilityUse(movablePiece);
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

        TOP_PANEL.setTurnTime(turnTime);
    }

    public void movePiece(MovablePiece movablePiece) {
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

    public int getTurnTime() {
        return TOP_PANEL.getTurnTime();
    }

    public String getTURN_LIMIT() {
        return TOP_PANEL.getTurnLimit();
    }

    public List<int[]> getMovablePieceCoord() {
        return BOARD_PANEL.getMovablePieceCoord();
    }

    public int getPicSize() {
        return BOARD_PANEL.getPicSize();
    }

    public int getSquareSize() {
        return BOARD_PANEL.getSquareSize();
    }

    public List<MovablePiece> getMovablePieceList() {
        return BOARD_PANEL.getMovablePieceList();
    }

    public MovablePiece getSelectedMovablePiece() {
        return BOARD_PANEL.getSelectedMovablePiece();
    }

    public List<int[]> getMovableSquareCoord() {
        return BOARD_PANEL.getMovableSquareCoord();
    }

    public void removeMovablePiece() {
        BOARD_PANEL.removeMovablePiece();
    }

    public void showValidSquares(MovablePiece movablePiece) {
        BOARD_PANEL.showValidSquares(movablePiece);
    }

    public int gridCoord(int i) {
        return BOARD_PANEL.gridCoord(i);
    }
}
