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
    private final JComboBox<Integer> MOVE_COMBO_BOX = new JComboBox<>();
    private final JButton UNDO_BUTTON = new JButton(StringText.UNDO);

    TopPanel(GameView gameview, ActionListener actionListener) {

        TIME_PANEL = new TimePanel(gameview);
        TIME_PANEL.setSize(150, 30);
        add(TIME_PANEL);

        JButton nextTurnButton = new JButton(StringText.NEXT_TURN);
        nextTurnButton.setSize(130, 30);
        nextTurnButton.addActionListener(actionListener);
        add(nextTurnButton);

        MOVE_COMBO_BOX.addItem(1);
        MOVE_COMBO_BOX.addItem(2);
        MOVE_COMBO_BOX.addItem(3);
        MOVE_COMBO_BOX.setSize(100, 30);
        add(MOVE_COMBO_BOX);

        UNDO_BUTTON.setSize(130, 30);
        UNDO_BUTTON.addActionListener(actionListener);
        add(UNDO_BUTTON);

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
        return (int) MOVE_COMBO_BOX.getSelectedItem();
    }

    void setTurnLimit() {
        TIME_PANEL.setTurnTime(BoardConfig.TURN_LIMIT);
    }

    void createNewTimer(int turnTime) {
        TIME_PANEL.createNewTimer(turnTime);
    }

    JComboBox<Integer> getMOVE_COMBO_BOX() {
        return MOVE_COMBO_BOX;
    }

    JButton getUNDO_BUTTON() {
        return UNDO_BUTTON;
    }

    void setUndoUI(boolean undoAvailable) {
        MOVE_COMBO_BOX.setEnabled(undoAvailable);
        UNDO_BUTTON.setEnabled(undoAvailable);
    }
}
