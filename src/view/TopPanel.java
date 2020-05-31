package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.BoardConfig;
import model.StringText;

class TopPanel
        extends JPanel {

    private final TimePanel TIME_PANEL;
    private final ActionListener ACTIONLISTENER;

    TopPanel(ActionListener actionListener) {
        ACTIONLISTENER = actionListener;

        TIME_PANEL = new TimePanel();
        TIME_PANEL.setSize(150, 30);
        add(TIME_PANEL);

        JButton nextTurnButton = new JButton(StringText.NEXT_TURN);
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(actionListener);
        add(nextTurnButton);

        JButton saveGameButton = new JButton(StringText.SAVE_GAME);
        saveGameButton.setSize(130, 30);
        saveGameButton.addActionListener(actionListener);
        add(saveGameButton);

        JButton loadGameButton = new JButton(StringText.LOAD_GAME);
        loadGameButton.setSize(130, 30);
        loadGameButton.addActionListener(actionListener);
        add(loadGameButton);
    }

    void setEagleTurn(boolean eagleTurn) {
        TIME_PANEL.setEagleTurn(eagleTurn);
    }

    void resetTimer() {
        TIME_PANEL.resetTimer();
    }

    int getTurnTime() {
        return TIME_PANEL.getTurnTime();
    }

    void setTurnTime(int turnTime) {
        TIME_PANEL.setTurnTime(turnTime);
    }

    String getTurnLimit() {
        return TIME_PANEL.getTurnLimit();
    }

    void setTurnLimit(String turnLimit) {
        TIME_PANEL.setTurnLimit(BoardConfig.TURN_LIMIT);
    }
}
