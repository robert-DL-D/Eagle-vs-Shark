package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.GameModel;

public class TurnPanel
        extends JPanel
        implements ActionListener {

    private final String[] wideSnakeButtons = {"Up", "Down"};
    private final String[] longSnakeButtons = {"Left", "Right"};

    private final List<JButton> sButtons = new ArrayList<>();

    private ActionListener listener;

    TurnPanel(ActionListener listener, GameModel gameModel, Color background) {
        this.listener = listener;

        setSize(160, 150);

        //showSnakeButtons();

        String moveUp = wideSnakeButtons[0];
        JButton moveUpButton = new JButton(moveUp);
        sButtons.add(moveUpButton);
        moveUpButton.setPreferredSize(new Dimension(140, 30));
        moveUpButton.addActionListener(this);
        add(moveUpButton);

        for (String longSnakeString : longSnakeButtons) {
            JButton longSnakeButton = new JButton(longSnakeString);
            sButtons.add(longSnakeButton);
            longSnakeButton.setPreferredSize(new Dimension(65, 50));
            longSnakeButton.addActionListener(this);
            add(longSnakeButton);
        }

        String moveDown = wideSnakeButtons[1];
        JButton moveDownButton = new JButton(moveDown);
        sButtons.add(moveDownButton);
        moveDownButton.setPreferredSize(new Dimension(140, 30));
        moveDownButton.addActionListener(this);
        add(moveDownButton);

        revalidate();
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }

    private void showSnakeButtons() {

        String moveUp = wideSnakeButtons[0];
        JButton moveUpButton = new JButton(moveUp);
        sButtons.add(moveUpButton);
        moveUpButton.setPreferredSize(new Dimension(140, 30));
        moveUpButton.addActionListener(this);
        add(moveUpButton);

        for (String longSnakeString : longSnakeButtons) {
            JButton longSnakeButton = new JButton(longSnakeString);
            sButtons.add(longSnakeButton);
            longSnakeButton.setPreferredSize(new Dimension(65, 50));
            longSnakeButton.addActionListener(this);
            add(longSnakeButton);
        }

        String moveDown = wideSnakeButtons[1];
        JButton moveDownButton = new JButton(moveDown);
        sButtons.add(moveDownButton);
        moveDownButton.setPreferredSize(new Dimension(140, 30));
        moveDownButton.addActionListener(this);
        add(moveDownButton);

        revalidate();
        repaint();
    }

}
