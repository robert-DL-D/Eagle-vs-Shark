package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.MovablePiece;
import model.StringText;

public class AbilityPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> PIECE_JLIST = new JList<>();
    private final List<JButton> ABILITIES_JBUTTON_LIST = new ArrayList<>();

    private final JButton USE_ABILITY_BUTTON = new JButton(StringText.USE);
    private final Color BACKGROUND;
    private final JCheckBox SUPER_ABILITY;

    private String lastAbilityUsed;
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

        SUPER_ABILITY = new JCheckBox("Super Ability");
        SUPER_ABILITY.setSize(new Dimension(100, 25));
        SUPER_ABILITY.setFont(new Font("Arial", Font.PLAIN, 14));
        SUPER_ABILITY.setHorizontalAlignment(SwingConstants.CENTER);
        add(SUPER_ABILITY);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    private void displayTargetList() {

        PIECE_JLIST.setBackground(BACKGROUND);
        PIECE_JLIST.setBorder(new LineBorder(Color.BLACK));
        PIECE_JLIST.setFont(new Font("Arial", Font.PLAIN, 18));
        PIECE_JLIST.setLocation(10, 50);
        PIECE_JLIST.setVisible(true);
        add(PIECE_JLIST);

        USE_ABILITY_BUTTON.setPreferredSize(new Dimension(135, 30));
        USE_ABILITY_BUTTON.addActionListener(this);
        USE_ABILITY_BUTTON.setVisible(true);
        add(USE_ABILITY_BUTTON);

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

        for (MovablePiece movablePiece : currentPieceList) {
            boolean matchingButton = false;

            if (!ABILITIES_JBUTTON_LIST.isEmpty()) {
                for (JButton jButton : ABILITIES_JBUTTON_LIST) {
                    if (jButton.getText().contains(movablePiece.getAbility().toString())) {
                        matchingButton = true;
                    }
                }
            }

            if (!matchingButton) {
                JButton abilityButton = new JButton();
                ABILITIES_JBUTTON_LIST.add(abilityButton);
                abilityButton.setPreferredSize(new Dimension(190, 30));
                abilityButton.setEnabled(true);
                abilityButton.addActionListener(this);

                setAbilityButtonText(movablePiece, abilityButton);

                add(abilityButton);
            }

        }

        enableAbilityButtons(currentPieceList);

    }

    void setAbilityButtonText(MovablePiece movablePiece, JButton abilityButton) {
        abilityButton.setText(movablePiece.getAbility().toString());
    }

    void enableAbilityButtons(List<? extends MovablePiece> currentPieceList) {
        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            for (MovablePiece movablePiece : currentPieceList) {
                if (jButton.getText().equals(movablePiece.getAbility().toString())) {
                    jButton.setEnabled(!movablePiece.isMovingMode());

                }
            }
        }
    }

    private void updatePieceJList(List<? extends MovablePiece> listForEnemy) {

        String[] pieceCoordArray = new String[listForEnemy.size()];

        for (int i = 0; i < listForEnemy.size(); i++) {
            MovablePiece movablePiece = listForEnemy.get(i);
            pieceCoordArray[i] = (StringAppend(movablePiece, movablePiece.getType().toString()).toString());
        }

        PIECE_JLIST.setListData(pieceCoordArray);
        PIECE_JLIST.setVisible(true);
    }

    void selectedAbility(String actionCommand, List<? extends MovablePiece> pieceList) {
        updatePieceJList(pieceList);
        USE_ABILITY_BUTTON.setText(StringText.USE + ": " + actionCommand);
        setUSE_ABILITY_BUTTON(true);
        displayTargetList();

        lastAbilityUsed = actionCommand;

        for (int i = 0; i < ABILITIES_JBUTTON_LIST.size(); i++) {
            if (ABILITIES_JBUTTON_LIST.get(i).getText().equals(lastAbilityUsed)) {
                lastAbilityUsedIndex = i;
            }
        }
    }

    void updateAbilityPanelAfterAbilityUse() {
        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            jButton.setEnabled(false);
        }

        PIECE_JLIST.setVisible(false);
        setUSE_ABILITY_BUTTON(false);
    }

    void updateButtonText(MovablePiece movablePiece) {
        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            if (jButton.getText().contains(":")) {
                jButton.setText(StringAppend(movablePiece, lastAbilityUsed).toString());
            }
        }
    }

    private StringBuilder StringAppend(MovablePiece movablePiece, String text) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(text)
                .append(": ")
                .append(movablePiece.getClass().getSuperclass().getSimpleName())
                .append(" ")
                .append(movablePiece.getRow() + 1)
                .append(" ")
                .append(movablePiece.getColumn() + 1);

        return stringBuilder;
    }

    void setSuperCheckBox(boolean b) {
        SUPER_ABILITY.setEnabled(b);
    }

    void setChecked(boolean b) {
        SUPER_ABILITY.setSelected(!b);
    }

    void removeLastAbilityUsed() {
        lastAbilityUsed = null;
    }

    void resetUseAbilityButtonText() {
        USE_ABILITY_BUTTON.setText(StringText.USE);
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

    void setUSE_ABILITY_BUTTON(boolean status) {
        USE_ABILITY_BUTTON.setVisible(status);
    }

    void setLastAbilityUsed(String lastAbilityUsed) {
        this.lastAbilityUsed = lastAbilityUsed;
    }

    void setAbilityButtonStatus(int pieceModeToggledIndex) {
        ABILITIES_JBUTTON_LIST.get(pieceModeToggledIndex).setEnabled(false);
    }

    boolean isSuperAbilityCheck() {
        return SUPER_ABILITY.isSelected();
    }

}
