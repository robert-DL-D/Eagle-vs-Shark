package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.GameModel;

public class TurnPanel
        extends JPanel
        implements ActionListener {

    private final String[] upDownButtons = {"Up", "Down"};
    private final String[] leftRightButtons = {"Left", "Right"};

    private final List<JButton> JButtons = new ArrayList<>();

    private JList<String> sharkJList;

    private ActionListener listener;

    TurnPanel(ActionListener listener, GameModel gameModel, Color background) {
        this.listener = listener;

        setSize(160, 300);

        sharkJList = new JList<>();
        sharkJList.setBackground(background);
        sharkJList.setBorder(new LineBorder(Color.BLACK));

        final int sharkListSize = gameModel.getSharkList().size();
        String[] snakePosArray = new String[sharkListSize];
        for (int i = 0; i < sharkListSize; i++) {
            snakePosArray[i] = ("Shark " + (i + 1) + ": " + gameModel.getSharkList().get(i).getX() + " " + gameModel.getSharkList().get(i).getY());
        }

        sharkJList.setListData(snakePosArray);
        add(sharkJList);

        String moveUp = upDownButtons[0];
        JButton moveUpButton = new JButton(moveUp);
        JButtons.add(moveUpButton);
        moveUpButton.setPreferredSize(new Dimension(140, 30));
        moveUpButton.addActionListener(this);
        add(moveUpButton);

        for (String longSnakeString : leftRightButtons) {
            JButton longSnakeButton = new JButton(longSnakeString);
            JButtons.add(longSnakeButton);
            longSnakeButton.setPreferredSize(new Dimension(65, 50));
            longSnakeButton.addActionListener(this);
            add(longSnakeButton);
        }

        String moveDown = upDownButtons[1];
        JButton moveDownButton = new JButton(moveDown);
        JButtons.add(moveDownButton);
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

    public int getSharkJListSelectedItem() {
        return sharkJList.getSelectedIndex();

    }
}
