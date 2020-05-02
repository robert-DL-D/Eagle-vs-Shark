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
import javax.swing.SwingConstants;

import model.MovablePiece;

public class PiecePanel
        extends JPanel
        implements ActionListener {

    private final JLabel turnLabel;

    private final GameView GAMEVIEW;
    private final ActionListener ACTIONLISTENER;
    private boolean isEaglePlayerTurn;
    private final List<JButton> PIECE_BUTTON_LIST = new ArrayList<>();
    private int pressedButton;

    PiecePanel(GameView gameView, ActionListener actionListener) {
        GAMEVIEW = gameView;
        ACTIONLISTENER = actionListener;

        turnLabel = new JLabel();
        turnLabel.setPreferredSize(new Dimension(200, 25));
        turnLabel.setFont(new Font("Arial", Font.BOLD, 19));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel);

        JLabel instructionLabel = new JLabel("Select a piece to move");
        instructionLabel.setPreferredSize(new Dimension(200, 20));
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(instructionLabel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        pressedButton = PIECE_BUTTON_LIST.indexOf(actionEvent.getSource());

        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void createButtons(List<? extends MovablePiece> currentPieceList) {

        if (!PIECE_BUTTON_LIST.isEmpty()) {
            for (JButton jButton : PIECE_BUTTON_LIST) {
                remove(jButton);
            }
            PIECE_BUTTON_LIST.clear();
        }

        for (int i = 0; i < currentPieceList.size(); i++) {
            JButton pieceButton = new JButton();
            PIECE_BUTTON_LIST.add(pieceButton);
            pieceButton.setSize(80, 180);
            pieceButton.setEnabled(true);
            pieceButton.addActionListener(this);
            add(pieceButton);
        }

        revalidate();
        repaint();
        setButtonText(currentPieceList);
        setEnabledButton(currentPieceList);
    }

    void updateTurnText() {
        turnLabel.setText((isEaglePlayerTurn ? "Eagle" : "Shark") + "'s turn");
    }

    int getSelectedPieceIndex() {
        return pressedButton;
    }

    void disableAllPieceButton() {
        for (JButton button : PIECE_BUTTON_LIST) {
            button.setEnabled(false);
        }
    }

    private void setEnabledButton(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < PIECE_BUTTON_LIST.size(); i++) {

            JButton button = PIECE_BUTTON_LIST.get(i);

            if (currentPieceList.get(i).isStunned()) {
                button.setEnabled(false);
            } else {
                button.setEnabled(true);
            }
        }
    }

    void hideUnmovablePiece(String abilityUsed, List<? extends MovablePiece> currentPieceList) {
        for (int i = 0; i < currentPieceList.size(); i++) {
            if (currentPieceList.get(i).getAbility().toString().equals(abilityUsed)) {
                PIECE_BUTTON_LIST.get(i).setEnabled(false);
            }
        }
    }

    void setButtonText(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < PIECE_BUTTON_LIST.size(); i++) {

            MovablePiece movablePiece = currentPieceList.get(i);

            String s = movablePiece.getType() + " " + movablePiece.getClass().getSuperclass().getSimpleName()
                    + " " + (i + 1) + ": " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1);

            if (movablePiece.isStunned()) {
                s += " STUNNED";
            } else if (movablePiece.isSlowed()) {
                s += " SLOWED";
            }

            PIECE_BUTTON_LIST.get(i).setText(s);

        }
    }

    void setIsEaglePlayer(boolean isEaglePlayerTurn) {
        this.isEaglePlayerTurn = isEaglePlayerTurn;
    }

}
