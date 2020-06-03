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
        setSize(1600, 950);

        Container contentPane = getContentPane();

        BOARD_PANEL = new BoardPanel(mouseListener);
        BOARD_PANEL.setLocation(0, 0);
        BOARD_PANEL.setSize(720, 900);
        contentPane.add(BOARD_PANEL);

        int boardViewX = BOARD_PANEL.getWidth();

        int topPanelX = boardViewX + panelMargin;
        TOP_PANEL = new TopPanel(this, actionListener);
        contentPane.add(addPanel(TOP_PANEL, topPanelX, BoardPanel.getBoardMargin(), 450, 40));

        int piecePanelX = boardViewX + panelMargin;
        int piecePanelY = BoardPanel.getBoardMargin() + TOP_PANEL.getHeight() + panelMargin;
        PIECE_PANEL = new PiecePanel(actionListener);
        contentPane.add(addPanel(PIECE_PANEL, piecePanelX, piecePanelY, 240, 500));

        int abilityPanelX = piecePanelX + PIECE_PANEL.getWidth() + panelMargin;
        ABILITY_PANEL = new AbilityPanel(actionListener, getBackground());
        contentPane.add(addPanel(ABILITY_PANEL, abilityPanelX, piecePanelY, 200, PIECE_PANEL.getHeight()));

        int rulesTextAreaX = abilityPanelX + ABILITY_PANEL.getWidth() + panelMargin;
        TextArea rulesTextArea = new TextArea(
                "How to play:\n \n" +
                        "Each turn a player move one piece and use one ability\n \n" +
                        "A piece must in the corresponding mode to perform that action \n \n" +
                        "Pieces can only be captured according to their colors:\n" +
                        "Red > Green > Blue > Red\n \n" +
                        "Eagle Pieces:\n" +
                        "Red Eagle \n" +
                        "Move: horizontally or vertically up to 2 spaces \n" +
                        "Ability: Stuns an enemy piece, it cannot move\n \n" +
                        "Green Eagle \n" +
                        "Move: horizontally or vertically 1 space and diagonally by 2 spaces\n" +
                        "Ability: Speeds up an ally piece, it can move more spaces \n \n" +
                        "Blue Eagle \n" +
                        "Move: all directions by 1 space \n" +
                        "Ability: Jumps an ally piece to a random empty square\n \n" +
                        "Shark Pieces:\n" +
                        "Red Shark \n" +
                        "Move: like a Knight in chess \n" +
                        "Ability: Slows an enemy piece, it can move less spaces \n \n" +
                        "Green Shark \n" +
                        "Move: diagonally up to 2 spaces\n" +
                        "Ability: Cleanse an ally piece, remove Stun effect \n \n" +
                        "Blue Shark \n" +
                        "Move: horizontally or vertically by 2 spaces and diagonally 1 space \n" +
                        "Ability: Shield an ally piece, protecting it from being captured \n"
                , 1, 1);
        rulesTextArea.setSize(400, 550);
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
        PIECE_PANEL.disableAllButton();
        PIECE_PANEL.setLabelText(currentPieceList);
        ABILITY_PANEL.updateButtonText(movablePiece);

        revalidate();
        repaint();

    }

    public void updateNextTurn(List<? extends MovablePiece> currentPieceList, boolean eagleTurn) {
        BOARD_PANEL.removeMovablePiece();
        BOARD_PANEL.updateMovablePieceCoord();

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
