package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.MovablePiece;

public class AbilityPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> PIECE_JLIST = new JList<>();
    private final List<JButton> ABILITIES_JBUTTON_LIST = new ArrayList<>();
    private static final String USE_ABILITY_BUTTON_TEXT = "Use";
    private final JButton useAbilityButton = new JButton(USE_ABILITY_BUTTON_TEXT);
    private final Color BACKGROUND;
    private String lastAbilityUsed;
    private JLabel AFFECTED_PIECE;
    private int lastAbilityUsedIndex;

    private final ActionListener ACTIONLISTENER;

    AbilityPanel(ActionListener actionListener, Color background) {
        ACTIONLISTENER = actionListener;
        BACKGROUND = background;

        JLabel abilityLabel = new JLabel("Abilities");
        abilityLabel.setPreferredSize(new Dimension(150, 20));
        abilityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        abilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(abilityLabel);

        AFFECTED_PIECE = new JLabel();
        AFFECTED_PIECE.setSize(new Dimension(200, 25));
        AFFECTED_PIECE.setFont(new Font("Arial", Font.BOLD, 16));
        AFFECTED_PIECE.setHorizontalAlignment(SwingConstants.CENTER);
        add(AFFECTED_PIECE);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void displayTargetList() {

        PIECE_JLIST.setBackground(BACKGROUND);
        PIECE_JLIST.setBorder(new LineBorder(Color.BLACK));
        PIECE_JLIST.setFont(new Font("Arial", Font.PLAIN, 18));
        PIECE_JLIST.setLocation(10, 50);
        PIECE_JLIST.setVisible(true);
        add(PIECE_JLIST);

        useAbilityButton.setPreferredSize(new Dimension(135, 30));
        useAbilityButton.addActionListener(this);
        useAbilityButton.setVisible(true);
        add(useAbilityButton);

        revalidate();
        repaint();
    }

    void createButtons(List<? extends MovablePiece> currentPieceList) {

        if (!ABILITIES_JBUTTON_LIST.isEmpty()) {
            for (JButton jButton : ABILITIES_JBUTTON_LIST) {
                remove(jButton);
            }
            ABILITIES_JBUTTON_LIST.clear();
        }

        for (int i = 0; i < currentPieceList.size(); i += 2) {

            JButton pieceButton = new JButton();
            ABILITIES_JBUTTON_LIST.add(pieceButton);
            pieceButton.setPreferredSize(new Dimension(135, 30));
            pieceButton.setEnabled(true);
            pieceButton.addActionListener(this);
            add(pieceButton);
        }

    }

    void setAbilityButtonText(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < ABILITIES_JBUTTON_LIST.size(); i++) {
            MovablePiece movablePiece = currentPieceList.get(i * 2);
            String s = movablePiece.getAbility().toString();

            JButton button = ABILITIES_JBUTTON_LIST.get(i);
            button.setText(s);
            button.setEnabled(!movablePiece.isMovingMode());
        }
    }

    private void updatePieceJList(List<? extends MovablePiece> listForEnemy) {

        String[] pieceCoordArray = new String[listForEnemy.size()];

        for (int i = 0; i < listForEnemy.size(); i++) {
            MovablePiece movablePiece = listForEnemy.get(i);

            pieceCoordArray[i] = (movablePiece.getType() + " " + movablePiece.getClass().getSuperclass().getSimpleName()
                    + " " + (i + 1) + ": " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1));
        }

        PIECE_JLIST.setListData(pieceCoordArray);
        PIECE_JLIST.setVisible(true);
    }

    void selectedAbility(String actionCommand, List<? extends MovablePiece> pieceList) {
        updatePieceJList(pieceList);
        setUseButtonText(actionCommand);
        setUseAbilityButton(true);
        displayTargetList();

        lastAbilityUsed = actionCommand;

        for (int i = 0; i < ABILITIES_JBUTTON_LIST.size(); i++) {
            if (ABILITIES_JBUTTON_LIST.get(i).getText().equals(lastAbilityUsed)) {
                lastAbilityUsedIndex = i;
            }
        }
    }

    void enableAbilityUI(List<? extends MovablePiece> currentPieceList) {

        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            jButton.setEnabled(true);
        }

        setAbilityButtonText(currentPieceList);

    }

    void disableAbilityUI() {

        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            jButton.setEnabled(false);
        }

        PIECE_JLIST.setVisible(false);
        setUseAbilityButton(false);
    }

    void setAfterAbilityUseText(MovablePiece movablePiece) {
        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            if (jButton.getText().equals(lastAbilityUsed)) {
                jButton.setText(jButton.getText() + ": "
                        + movablePiece.getClass().getSuperclass().getSimpleName()
                        + " " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1));
            }
        }
    }

    void changeAbilityButtonStatus(int selectedModeIndex) {
        ABILITIES_JBUTTON_LIST.get(selectedModeIndex / 2).setEnabled(false);
    }

    JList<String> getPIECE_JLIST() {
        return PIECE_JLIST;
    }

    int getPieceJListSelectedItem() {
        return PIECE_JLIST.getSelectedIndex();
    }

    int getLastAbilityUsedIndex() {
        return lastAbilityUsedIndex;
    }

    private void setUseButtonText(String actionCommand) {
        useAbilityButton.setText(USE_ABILITY_BUTTON_TEXT + ": " + actionCommand);
    }

    void resetUseAbilityButtonText() {
        useAbilityButton.setText(USE_ABILITY_BUTTON_TEXT);
    }

    void setUseAbilityButton(boolean status) {
        useAbilityButton.setVisible(status);
    }

    void setAFFECTED_PIECE() {

        if (lastAbilityUsed != null) {
            if (lastAbilityUsed.equals("SHIELD")) {
                for (JButton jButton : ABILITIES_JBUTTON_LIST) {
                    if (jButton.getText().contains("SHIELD")) {
                        AFFECTED_PIECE.setText(jButton.getText());
                    }
                }
            }

        } else {
            AFFECTED_PIECE.setText("");
        }

    }

    void updateButtonText(MovablePiece movablePiece) {
        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            if (jButton.getText().contains(":")) {

                jButton.setText(lastAbilityUsed + ": "
                        + movablePiece.getClass().getSuperclass().getSimpleName()
                        + " " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1));

            }
        }
    }

    void removeLastAbilityUsed() {
        lastAbilityUsed = null;
    }
}
