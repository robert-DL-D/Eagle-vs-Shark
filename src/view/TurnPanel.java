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
    private boolean isEaglePlayer;
    private final List<JButton> JBUTTON_LIST = new ArrayList<>();
    private final List<JButton> PIECE_BUTTON_LIST = new ArrayList<>();
    private int pressedButton;

    TurnPanel(GameView GAMEVIEW, ActionListener ACTIONLISTENER) {
        this.GAMEVIEW = GAMEVIEW;
        this.ACTIONLISTENER = ACTIONLISTENER;

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

        turnLabel.setText((isEaglePlayer ? "Eagle" : "Shark") + "'s turn");
    }

    int getSelectedPieceIndex() {
        return pressedButton;

    }

    public void setEnabledButton(boolean enabled) {
        for (JButton button : JBUTTON_LIST) {
            button.setEnabled(enabled);
        }
    }

    public void setButtonText() {

        int size = PIECE_BUTTON_LIST.size();
        for (int i = 0; i < size; i++) {
            JButton button = PIECE_BUTTON_LIST.get(i);

            if (isEaglePlayer) {

                List<Eagle> eagleList = GAMEVIEW.getEagleList();

                Eagle eagle = eagleList.get(i);
                button.setText(eagle.getType() + " Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1));

            } else {

                List<Shark> sharkList = GAMEVIEW.getSharkList();

                Shark shark = sharkList.get(i);
                button.setText(shark.getType() + " Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1));

            }
        }
    }

    public void setIsEaglePlayer(boolean isEaglePlayer) {
        this.isEaglePlayer = isEaglePlayer;
    }

}
