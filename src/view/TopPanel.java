package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.BoardConfig;
import model.StringText;

class TopPanel
        extends JPanel {

    private final TimePanel TIME_PANEL;
    private final JComboBox<Integer> numMove = new JComboBox<>();

    TopPanel(GameView gameview, ActionListener actionListener) {

        TIME_PANEL = new TimePanel(gameview);
        TIME_PANEL.setSize(150, 30);
        add(TIME_PANEL);

        JButton nextTurnButton = new JButton(StringText.NEXT_TURN);
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(actionListener);
        add(nextTurnButton);

        numMove.addItem(1);
        numMove.addItem(2);
        numMove.addItem(3);
        numMove.setSize(100, 30);
        add(numMove);

        JButton undoGameButton = new JButton(StringText.UNDO);
        undoGameButton.setSize(130, 30);
        undoGameButton.addActionListener(actionListener);
        add(undoGameButton);

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

    int getTurnTime() {
        return TIME_PANEL.getTurnTime();
    }

    int getUndoMove() {
        return (int) numMove.getSelectedItem();
    }

    void setTurnLimit(int turnLimit) {
        TIME_PANEL.setTurnLimit(BoardConfig.TURN_LIMIT);
    }

    void createNewTimer(int turnTime) {
        TIME_PANEL.createNewTimer(turnTime);
    }
}
