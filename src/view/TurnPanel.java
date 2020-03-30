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
    private final JList<String> pieceJList;

    private final GameView gameView;
    private final ActionListener listener;
    private boolean isEaglePlayer;
    private final List<JButton> jButtonsList = new ArrayList<>();

    TurnPanel(GameView gameView, ActionListener listener, Color background) {
        this.gameView = gameView;
        this.listener = listener;

        setSize(160, 500);

        turnLabel = new JLabel();
        turnLabel.setPreferredSize(new Dimension(150, 20));
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel);

        pieceJList = new JList<>();
        pieceJList.setBackground(background);
        pieceJList.setBorder(new LineBorder(Color.BLACK));
        pieceJList.setFont(new Font("Arial", Font.PLAIN, 18));
        pieceJList.setLocation(10, 50);
        add(pieceJList);

        JButton moveUpButton = new JButton("Up");
        jButtonsList.add(moveUpButton);
        moveUpButton.setPreferredSize(new Dimension(135, 30));
        moveUpButton.addActionListener(this);
        add(moveUpButton);

        String[] leftRightButtons = {"Left", "Right"};
        for (String longSnakeString : leftRightButtons) {
            JButton jButton = new JButton(longSnakeString);
            jButtonsList.add(jButton);
            jButton.setPreferredSize(new Dimension(65, 50));
            jButton.addActionListener(this);
            add(jButton);
        }

        JButton moveDownButton = new JButton("Down");
        jButtonsList.add(moveDownButton);
        moveDownButton.setPreferredSize(new Dimension(135, 30));
        moveDownButton.addActionListener(this);
        add(moveDownButton);

        /*JLabel abilityLabel = new JLabel("Abilities");
        abilityLabel.setPreferredSize(new Dimension(150, 20));
        abilityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        abilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(abilityLabel);*/

        //for (int i = 0; i < 3; i++) {

           /* JButton jButton = new JButton("Stun");
            jButtonsList.add(jButton);
            jButton.setPreferredSize(new Dimension(135, 30));
            jButton.addActionListener(this);
            add(jButton);*/
        //}

        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.setPreferredSize(new Dimension(135, 50));
        nextTurnButton.addActionListener(this);
        add(nextTurnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }

    public void updatePieceJList() {

        turnLabel.setText((isEaglePlayer ? "Eagle" : "Shark") + "'s turn");

        if (isEaglePlayer) {

            List<Eagle> eagleList = gameView.getEagleList();
            int eagleListSize = eagleList.size();
            String[] pieceCoordArray = new String[eagleListSize];

            for (int i = 0; i < eagleListSize; i++) {
                Eagle eagle = eagleList.get(i);
                pieceCoordArray[i] = ("Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1));
            }

            pieceJList.setListData(pieceCoordArray);

        } else {

            List<Shark> sharkList = gameView.getSharkList();
            int sharkListSize = sharkList.size();
            String[] pieceCoordArray = new String[sharkListSize];

            for (int i = 0; i < sharkListSize; i++) {
                Shark shark = sharkList.get(i);
                pieceCoordArray[i] = ("Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1));
            }

            pieceJList.setListData(pieceCoordArray);
        }

    }

    int getPieceJListSelectedItem() {
        return pieceJList.getSelectedIndex();

    }

    public void setButtonStatus(boolean status) {
        for (JButton button : jButtonsList) {
            button.setEnabled(status);
        }
    }

    public void setIsEaglePlayer(boolean isEaglePlayer) {
        this.isEaglePlayer = isEaglePlayer;
    }

}
