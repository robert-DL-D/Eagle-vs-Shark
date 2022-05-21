package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class GameView extends JFrame {

    private final BoardPanel BOARD_PANEL;
    private final PiecePanel PIECE_PANEL;
    private static final int SQUARE_SIZE = 50;
    private final AbilityPanel ABILITY_PANEL;
    private final TopPanel TOP_PANEL;
    private final EnemyPanel Enemy_PANEL;

    public GameView(ActionListener actionListener, MouseListener mouseListener) {
        super("Eagle vs Shark");

        int panelMargin = 5;

        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();

        BOARD_PANEL = new BoardPanel(mouseListener, SQUARE_SIZE);
        BOARD_PANEL.setLocation(0, 0);
        BOARD_PANEL.setSize(SQUARE_SIZE * (BoardConfig.BOARD_COLUMNS + 1), SQUARE_SIZE * (BoardConfig.BOARD_ROWS + 1));
        contentPane.add(BOARD_PANEL);

        int topPanelX = BOARD_PANEL.getWidth();
        TOP_PANEL = new TopPanel(this, actionListener);
        contentPane.add(addPanel(TOP_PANEL, topPanelX, BoardPanel.getBoardMargin(), 700, 40));

        int piecePanelY = BoardPanel.getBoardMargin() + TOP_PANEL.getHeight() + panelMargin;
        PIECE_PANEL = new PiecePanel(actionListener);
        contentPane.add(addPanel(PIECE_PANEL, topPanelX, piecePanelY, 240, 500));

        int enemyPanelX = topPanelX + PIECE_PANEL.getWidth() + panelMargin;
        Enemy_PANEL = new EnemyPanel(actionListener);
        contentPane.add(addPanel(Enemy_PANEL, enemyPanelX, piecePanelY, 220, PIECE_PANEL.getHeight()));

        int abilityPanelX = enemyPanelX + Enemy_PANEL.getWidth() + panelMargin;
        ABILITY_PANEL = new AbilityPanel(actionListener, getBackground());
        contentPane.add(addPanel(ABILITY_PANEL, abilityPanelX, piecePanelY, 200, PIECE_PANEL.getHeight()));

        int rulesTextAreaX = abilityPanelX + ABILITY_PANEL.getWidth() + panelMargin;
        TextArea rulesTextArea = new TextArea(
                "How to play:\n \n" +
                        "Each turn a player move one piece and use one ability\n \n" +
                        "A piece must in the corresponding mode to perform that action\n \n" +
                        "To move a piece, click on the picture to select it\n" +
                        "You must deselect a piece first before selecting another piece\n" +
                        "Abilities's affect will only last one turn\n" +
                        "Pieces can only be captured according to their colors:\n" +
                        "Red > Green > Blue > Red\n \n" +
                        "Eagle Pieces:\n" +
                        "Red Eagle\n" +
                        "Move: horizontally or vertically up to 3 spaces\n" +
                        "Ability: Stuns an enemy piece, it cannot move\n \n" +
                        "Green Eagle\n" +
                        "Move: diagonally up to 3 spaces\n" +
                        "Ability: Speeds up an ally piece, it can move an \n" +
                        "additional space in each direction\n \n" +
                        "Blue Eagle \n" +
                        "Move: all directions up to 2 space \n" +
                        "Ability: Jumps an ally piece back to a random empty square\n" +
                        "on your side\n \n" +
                        "Shark Pieces:\n" +
                        "Red Shark\n" +
                        "Move: like a Knight in chess \n" +
                        "Ability: Slows an enemy piece, it can move less spaces\n \n" +
                        "Green Shark\n" +
                        "Move: horizontally or vertically by 2 spaces\n" +
                        "and diagonally up to 2 spaces\n" +
                        "Ability: Cleanse an ally piece, remove Stun effect\n \n" +
                        "Blue Shark\n" +
                        "Move: horizontally or vertically up to 2 spaces\n" +
                        "and diagonally by 2 spaces\n" +
                        "Ability: Shield an ally piece, protecting it from being captured\n \n" +
                        "A player can use a super version of any ability once\n" +
                        "Stun: all pieces of the same selected type are stunned\n" +
                        "Speed: piece can move 2 additional space in each direction\n" +
                        "Jump: a random enemy piece jumps back to their side\n" +
                        "Slow: piece will move 2 less spaces in each direction\n" +
                        "Cleanse: piece will not be affected by ability",
                1, 1);
        rulesTextArea.setSize(370, PIECE_PANEL.getHeight());
        rulesTextArea.setLocation(rulesTextAreaX, piecePanelY);
        rulesTextArea.setEditable(false);
        rulesTextArea.setBackground(getBackground());
        contentPane.add(rulesTextArea);

        int boardHeight = BOARD_PANEL.getHeight() + 10;
        int panelHeight = TOP_PANEL.getHeight() + rulesTextArea.getHeight() + 75;

        setSize(BOARD_PANEL.getWidth() + PIECE_PANEL.getWidth()
                        + Enemy_PANEL.getWidth() + ABILITY_PANEL.getWidth()
                        + rulesTextArea.getWidth() + 50,
                (Math.max(boardHeight, panelHeight)));
        setVisible(true);
    }

    private JPanel addPanel(JPanel panel, int panelX, int panelY, int width, int height) {
        panel.setLocation(panelX, panelY);
        panel.setSize(width, height);
        panel.setBorder(new LineBorder(Color.BLACK));

        return panel;
    }

    public void initGameView(Square[][] squareArray,
                             List<Eagle> eagleList, List<Shark> sharkList,
                             List<Flag> flagList, List<Island> islandList,
                             List<? extends MovablePiece> currentPieceList,
                             List<? extends MovablePiece> enemyPieceList,
                             boolean eagleTurn) {
        BOARD_PANEL.setBoard(squareArray, eagleList, sharkList, flagList, islandList);

        PIECE_PANEL.updateTurnText(eagleTurn);
        PIECE_PANEL.createUI(currentPieceList);
        PIECE_PANEL.setLabelText(currentPieceList);

        Enemy_PANEL.createLabels(enemyPieceList);

        ABILITY_PANEL.createButtons(currentPieceList);

        TOP_PANEL.setEagleTurn(eagleTurn);
        TOP_PANEL.setTurnLimit();

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

    public void updateNextTurn(List<? extends MovablePiece> currentPieceList,
                               List<? extends MovablePiece> enemyPieceList,
                               boolean eagleTurn, boolean superUsed, boolean undoAvailable) {
        BOARD_PANEL.removeMovablePiece();
        BOARD_PANEL.updateMovablePieceCoord();

        PIECE_PANEL.updateTurnText(eagleTurn);
        PIECE_PANEL.createUI(currentPieceList);

        Enemy_PANEL.createLabels(enemyPieceList);

        ABILITY_PANEL.removeLastAbilityUsed();
        ABILITY_PANEL.getPIECE_JLIST().setVisible(false);
        ABILITY_PANEL.createButtons(currentPieceList);
        ABILITY_PANEL.resetUseAbilityButtonText();
        ABILITY_PANEL.setUSE_ABILITY_BUTTON(false);
        ABILITY_PANEL.setSuperCheckBox(superUsed);
        ABILITY_PANEL.setChecked(superUsed);

        TOP_PANEL.setEagleTurn(eagleTurn);
        TOP_PANEL.createNewTimer(BoardConfig.TURN_LIMIT);
        TOP_PANEL.setUndoUI(undoAvailable);

        revalidate();
        repaint();
    }

    public void updateViewAfterAbilityUse(String abilityUsed, List<? extends MovablePiece> allyPieceList, List<? extends MovablePiece> enemyPieceList) {
        PIECE_PANEL.hideUnmovablePiece(abilityUsed, allyPieceList, ABILITY_PANEL.getLastAbilityUsedIndex());
        PIECE_PANEL.setLabelText(allyPieceList);
        Enemy_PANEL.setLabelText(enemyPieceList);
        ABILITY_PANEL.updateAbilityPanelAfterAbilityUse();
        ABILITY_PANEL.setSuperCheckBox(false);

        revalidate();
        repaint();
    }

    public void selectedAbility(String actionCommand, List<? extends MovablePiece> movablePieceList) {
        ABILITY_PANEL.selectedAbility(actionCommand, movablePieceList);
    }

    public void loadGame(Player<? extends MovablePiece> player, List<? extends MovablePiece> enemyPieceList, int turnTime) {

        if (player.isPieceMoved()) {
            PIECE_PANEL.disableAllButton();
        }

        if (player.getAbilityUsed() != null) {
            for (MovablePiece movablePiece : player.getMOVABLE_PIECE_LIST()) {
                if (player.getAbilityUsed().equals(movablePiece.getAbility().toString())) {
                    ABILITY_PANEL.setLastAbilityUsed(player.getAbilityUsed());
                    updateViewAfterAbilityUse(player.getAbilityUsed(), player.getMOVABLE_PIECE_LIST(), enemyPieceList);
                    break;
                }
            }

        }

        int pieceModeToggledIndex = player.getPieceModeToggledIndex();

        if (pieceModeToggledIndex != -1) {
            PIECE_PANEL.setSelectedButtonIndex(pieceModeToggledIndex);
            PIECE_PANEL.changeButtonMode(player.getMOVABLE_PIECE_LIST());
            ABILITY_PANEL.setAbilityButtonStatus(pieceModeToggledIndex);
        }

        TOP_PANEL.createNewTimer(turnTime);

        revalidate();
        repaint();
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

    public boolean isSuperAbility() {
        return ABILITY_PANEL.isSuperAbilityCheck();
    }

    public int getUndoTurn() {
        return TOP_PANEL.getUndoMove();
    }

    public JComboBox<Integer> getMOVE_COMBOBOX() {
        return TOP_PANEL.getMOVE_COMBO_BOX();
    }

    public JButton getUNDO_BUTTON() {
        return TOP_PANEL.getUNDO_BUTTON();
    }

    public void addMPieceCoord(MovablePiece capturedPiece) {
        BOARD_PANEL.addMovablePieceCoord(capturedPiece);

        revalidate();
        repaint();
    }
}
