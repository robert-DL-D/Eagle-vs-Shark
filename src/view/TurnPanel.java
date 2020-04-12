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

import model.Eagle;
import model.Shark;

public class TurnPanel
        extends JPanel
        implements ActionListener {

    private final JLabel turnLabel;

    private final GameView GAMEVIEW;
    private final ActionListener ACTIONLISTENER;
    private boolean isEaglePlayerTurn;
    private final List<JButton> PIECE_BUTTON_LIST = new ArrayList<>();
    private int pressedButton;

    TurnPanel(GameView gameView, ActionListener actionListener) {
        GAMEVIEW = gameView;
        ACTIONLISTENER = actionListener;

        turnLabel = new JLabel();
        turnLabel.setPreferredSize(new Dimension(200, 20));
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel);

        for (int i = 0; i < 6; i++) {
            JButton pieceButton = new JButton();
            PIECE_BUTTON_LIST.add(pieceButton);
            //pieceButton.setPreferredSize(new Dimension(135, 30));
            pieceButton.setSize(80, 180);
            pieceButton.addActionListener(this);
            add(pieceButton);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressedButton = PIECE_BUTTON_LIST.indexOf(e.getSource());

        ACTIONLISTENER.actionPerformed(e);
    }

    public void updateTurnText() {

        turnLabel.setText((isEaglePlayerTurn ? "Eagle" : "Shark") + "'s turn");
    }

    int getSelectedPieceIndex() {
        return pressedButton;

    }

    public void disableAllPieceButton() {
        for (JButton button : PIECE_BUTTON_LIST) {
            button.setEnabled(false);
        }
    }

    public void setEnabledButton() {
        int size = PIECE_BUTTON_LIST.size();

        for (int i = 0; i < size; i++) {

            if (isEaglePlayerTurn) {
                List<Eagle> eagleList = GAMEVIEW.getEagleList();
                Eagle eagle = eagleList.get(i);

                if (eagle.isStunned()) {
                    PIECE_BUTTON_LIST.get(i).setEnabled(false);
                }

            } else {
                List<Shark> sharkList = GAMEVIEW.getSharkList();
                Shark shark = sharkList.get(i);

                if (shark.isStunned()) {
                    PIECE_BUTTON_LIST.get(i).setEnabled(false);
                }
            }
        }
    }

    public void setButtonText() {

        int size = PIECE_BUTTON_LIST.size();
        for (int i = 0; i < size; i++) {
            JButton button = PIECE_BUTTON_LIST.get(i);

            if (isEaglePlayerTurn) {
                List<Eagle> eagleList = GAMEVIEW.getEagleList();
                Eagle eagle = eagleList.get(i);

                String s = eagle.getType() + " Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1);

                if (eagle.isStunned()) {
                    s += " STUNNED";
                }

                button.setText(s);

            } else {
                List<Shark> sharkList = GAMEVIEW.getSharkList();
                Shark shark = sharkList.get(i);

                String s = shark.getType() + " Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1);

                if (shark.isStunned()) {
                    s += " STUNNED";
                }

                button.setText(s);
            }
        }
    }

    public void setIsEaglePlayer(boolean isEaglePlayer) {
        this.isEaglePlayerTurn = isEaglePlayer;
    }

}
