package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import model.MovablePiece;
import model.StringText;

public class PiecePanel
        extends JPanel
        implements ActionListener {

    private final JLabel turnLabel;
    private final List<JButton> PIECE_BUTTON_LIST = new ArrayList<>();
    private final List<JToggleButton> MODE_BUTTON_LIST = new ArrayList<>();

    private final ActionListener ACTIONLISTENER;
    private int selectedButtonIndex;

    PiecePanel(ActionListener actionListener) {
        ACTIONLISTENER = actionListener;

        turnLabel = new JLabel();
        turnLabel.setSize(new Dimension(200, 25));
        turnLabel.setFont(new Font("Arial", Font.BOLD, 19));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel);

        JLabel instructionLabel = new JLabel("Select a piece to move");
        instructionLabel.setPreferredSize(new Dimension(250, 20));
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(instructionLabel);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource().getClass().getSimpleName().equals("JButton")) {
            selectedButtonIndex = PIECE_BUTTON_LIST.indexOf(actionEvent.getSource());
        } else {
            selectedButtonIndex = MODE_BUTTON_LIST.indexOf(actionEvent.getSource());
        }

        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void createButtons(List<? extends MovablePiece> currentPieceList) {

        if (!PIECE_BUTTON_LIST.isEmpty()) {
            for (JButton jButton : PIECE_BUTTON_LIST) {
                remove(jButton);
            }
            PIECE_BUTTON_LIST.clear();
        }

        if (!MODE_BUTTON_LIST.isEmpty()) {
            for (JToggleButton jToggleButton : MODE_BUTTON_LIST) {
                remove(jToggleButton);
            }
            MODE_BUTTON_LIST.clear();
        }

        int buttonCounter = 0;

        for (int i = 0; i < currentPieceList.size(); i++) {

            if (buttonCounter == 0) {
                JToggleButton toggleModeButton = new JToggleButton();
                MODE_BUTTON_LIST.add(toggleModeButton);
                toggleModeButton.setText(new StringBuilder()
                        .append(currentPieceList.get(i).getType())
                        .append(" ")
                        .append(currentPieceList.get(i).getClass().getSuperclass().getSimpleName())
                        .append(": ")
                        .append((currentPieceList.get(i).isMovingMode() ? StringText.MOVING_MODE : StringText.ABILITY_MODE)).toString());
                toggleModeButton.addActionListener(this);
                toggleModeButton.setPreferredSize(new Dimension(200, 30));
                toggleModeButton.setLocation(10, 30 * i);
                add(toggleModeButton);

                buttonCounter++;
            } else {
                buttonCounter = 0;
            }

            JButton pieceButton = new JButton();
            PIECE_BUTTON_LIST.add(pieceButton);
            pieceButton.setPreferredSize(new Dimension(220, 30));
            pieceButton.setLocation(70, 30 * i);
            pieceButton.setEnabled(true);
            pieceButton.addActionListener(this);
            add(pieceButton);
        }

        setButtonText(currentPieceList);
        setEnabledButton(currentPieceList);
    }

    void changeButtonMode(List<? extends MovablePiece> currentPieceList) {
        MovablePiece movablePiece = currentPieceList.get(selectedButtonIndex * 2);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(movablePiece.getType())
                .append(" ")
                .append(movablePiece.getClass().getSuperclass().getSimpleName())
                .append(": ");

        JToggleButton jToggleButton = MODE_BUTTON_LIST.get(selectedButtonIndex);

        if (jToggleButton.getText().contains(StringText.MOVING_MODE)) {
            jToggleButton.setText(stringBuilder.append(StringText.ABILITY_MODE).toString());
        } else {
            jToggleButton.setText(stringBuilder.append(StringText.MOVING_MODE).toString());
        }

        jToggleButton.setEnabled(false);
        PIECE_BUTTON_LIST.get(selectedButtonIndex * 2).setEnabled(false);
        PIECE_BUTTON_LIST.get(selectedButtonIndex * 2 + 1).setEnabled(false);

    }

    void updateTurnText(boolean isEagleTurn) {
        turnLabel.setText((isEagleTurn ? StringText.EAGLE : StringText.SHARK) + "'s turn");
    }

    void disableAllButton() {
        for (JButton jButton : PIECE_BUTTON_LIST) {
            jButton.setEnabled(false);
        }

        for (JToggleButton jToggleButton : MODE_BUTTON_LIST) {
            jToggleButton.setEnabled(false);
        }
    }

    private void setEnabledButton(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < PIECE_BUTTON_LIST.size(); i++) {
            JButton jButton = PIECE_BUTTON_LIST.get(i);
            jButton.setEnabled(!currentPieceList.get(i).isStunned());

            if (i % 2 == 0) {
                jButton.setEnabled(MODE_BUTTON_LIST.get(i / 2).getText().contains(StringText.MOVING_MODE));
            } else {
                jButton.setEnabled(MODE_BUTTON_LIST.get(i - ((i + 1) / 2)).getText().contains(StringText.MOVING_MODE));

            }
        }
    }

    void hideUnmovablePiece(String abilityUsed, List<? extends MovablePiece> currentPieceList, int lastAbilityUsedIndex) {

        MODE_BUTTON_LIST.get(lastAbilityUsedIndex).setEnabled(false);

        for (int i = 0; i < currentPieceList.size(); i++) {
            if (currentPieceList.get(i).getAbility().toString().equals(abilityUsed)) {
                PIECE_BUTTON_LIST.get(i).setEnabled(false);
            }
        }
    }

    void setButtonText(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < PIECE_BUTTON_LIST.size(); i++) {
            MovablePiece movablePiece = currentPieceList.get(i);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(movablePiece.getType())
                    .append(" ")
                    .append(movablePiece.getClass().getSuperclass().getSimpleName())
                    .append(": ")
                    .append(movablePiece.getRow() + 1)
                    .append(" ")
                    .append(movablePiece.getColumn() + 1)
                    .append(" ");

            if (movablePiece.isStunned()) {
                stringBuilder.append("STUNNED");
            } else if (movablePiece.isSlowed()) {
                stringBuilder.append("SLOWED");
            } else if (movablePiece.isShielded()) {
                stringBuilder.append("SHIELDED");
            }

            PIECE_BUTTON_LIST.get(i).setText(stringBuilder.toString());

        }
    }

    public int getSelectedButtonIndex() {
        return selectedButtonIndex;
    }

    void setSelectedButtonIndex(int selectedButtonIndex) {
        this.selectedButtonIndex = selectedButtonIndex;
    }
}
