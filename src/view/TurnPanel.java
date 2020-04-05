package view;

import java.awt.Color;
import java.awt.Container;
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

import model.Eagle;
import model.Shark;

public class TurnPanel
        extends JPanel
        implements ActionListener {

    private final JLabel turnLabel;

    private final GameView gameView;
    private final ActionListener listener;
    private boolean isEaglePlayer;
    private final List<JButton> jButtonsList = new ArrayList<>();
    private final List<JButton> pieceButtonsList = new ArrayList<>();
    private int pressedButton;

    TurnPanel(GameView gameView, ActionListener listener, Color background) {
        this.gameView = gameView;
        this.listener = listener;

        turnLabel = new JLabel();
        turnLabel.setPreferredSize(new Dimension(200, 20));
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel);

        for (int i = 0; i < 6; i++) {
            JButton pieceButton = new JButton();
            pieceButtonsList.add(pieceButton);
            //pieceButton.setPreferredSize(new Dimension(135, 30));
            pieceButton.setSize(80, 180);
            pieceButton.addActionListener(this);
            add(pieceButton);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressedButton = pieceButtonsList.indexOf(e.getSource());

        listener.actionPerformed(e);
    }

    public void updateTurnText() {

        turnLabel.setText((isEaglePlayer ? "Eagle" : "Shark") + "'s turn");
    }

    int getSelectedPieceIndex() {
        return pressedButton;

    }

    public void setEnabledButton(boolean enabled) {
        for (JButton button : jButtonsList) {
            button.setEnabled(enabled);
        }
    }

    public void setButtonText() {

        int size = pieceButtonsList.size();
        for (int i = 0; i < size; i++) {
            JButton button = pieceButtonsList.get(i);

            if (isEaglePlayer) {

                List<Eagle> eagleList = gameView.getEagleList();

                Eagle eagle = eagleList.get(i);
                button.setText(eagle.getType() + " Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1));

            } else {

                List<Shark> sharkList = gameView.getSharkList();

                Shark shark = sharkList.get(i);
                button.setText(shark.getType() + " Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1));

            }
        }
    }

    public void setIsEaglePlayer(boolean isEaglePlayer) {
        this.isEaglePlayer = isEaglePlayer;
    }

}
