package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private final List<JLabel> PIECE_LABEL_LIST = new ArrayList<>();
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
        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void createButtons(List<? extends MovablePiece> currentPieceList) {

        if (!PIECE_LABEL_LIST.isEmpty()) {
            for (JLabel jLabel : PIECE_LABEL_LIST) {
                remove(jLabel);
            }
            PIECE_LABEL_LIST.clear();
        }

        if (!MODE_BUTTON_LIST.isEmpty()) {
            for (JToggleButton jToggleButton : MODE_BUTTON_LIST) {
                remove(jToggleButton);
            }
            MODE_BUTTON_LIST.clear();
        }

        for (int i = 0; i < currentPieceList.size(); i++) {
            boolean matchingButton = false;
            MovablePiece movablePiece = currentPieceList.get(i);

            if (!MODE_BUTTON_LIST.isEmpty()) {
                for (JToggleButton jToggleButton : MODE_BUTTON_LIST) {
                    if (jToggleButton.getText().contains(movablePiece.getType().toString())) {
                        matchingButton = true;
                    }
                }

            }

            if (!matchingButton) {
                JToggleButton toggleModeButton = new JToggleButton();
                MODE_BUTTON_LIST.add(toggleModeButton);
                toggleModeButton.setText(new StringBuilder()
                        .append(movablePiece.getType())
                        .append(" ")
                        .append(movablePiece.getClass().getSuperclass().getSimpleName())
                        .append(": ")
                        .append((movablePiece.isMovingMode() ? StringText.MOVING_MODE : StringText.ABILITY_MODE)).toString());
                toggleModeButton.addActionListener(this);
                toggleModeButton.setPreferredSize(new Dimension(200, 30));
                toggleModeButton.setLocation(10, 30 * i);
                add(toggleModeButton);
            }

            JLabel pieceLabel = new JLabel();
            PIECE_LABEL_LIST.add(pieceLabel);
            pieceLabel.setPreferredSize(new Dimension(220, 30));
            pieceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            pieceLabel.setLocation(70, 30 * i);
            add(pieceLabel);
        }

        setLabelText(currentPieceList);
    }

    void setLabelText(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < PIECE_LABEL_LIST.size(); i++) {
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

            PIECE_LABEL_LIST.get(i).setText(stringBuilder.toString());

        }
    }

    void changeButtonMode(List<? extends MovablePiece> currentPieceList) {
        MovablePiece movablePiece = null;
        JToggleButton jToggleButton = MODE_BUTTON_LIST.get(selectedButtonIndex);

        for (MovablePiece movablePiece1 : currentPieceList) {
            if (jToggleButton.getText().contains(movablePiece1.getType().toString())) {
                movablePiece = movablePiece1;
                break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(movablePiece.getType())
                .append(" ")
                .append(movablePiece.getClass().getSuperclass().getSimpleName())
                .append(": ");

        if (jToggleButton.getText().contains(StringText.MOVING_MODE)) {
            jToggleButton.setText(stringBuilder.append(StringText.ABILITY_MODE).toString());
        } else {
            jToggleButton.setText(stringBuilder.append(StringText.MOVING_MODE).toString());
        }

        jToggleButton.setEnabled(false);

    }

    void updateTurnText(boolean isEagleTurn) {
        turnLabel.setText((isEagleTurn ? StringText.EAGLE : StringText.SHARK) + "'s turn");
    }

    void disableAllButton() {
        for (JToggleButton jToggleButton : MODE_BUTTON_LIST) {
            jToggleButton.setEnabled(false);
        }
    }

    void hideUnmovablePiece(String abilityUsed, List<? extends MovablePiece> currentPieceList, int lastAbilityUsedIndex) {
        MODE_BUTTON_LIST.get(lastAbilityUsedIndex).setEnabled(false);
    }

    int getSelectedButtonIndex() {
        return selectedButtonIndex;
    }

    void setSelectedButtonIndex(int selectedButtonIndex) {
        this.selectedButtonIndex = selectedButtonIndex;
    }
}
