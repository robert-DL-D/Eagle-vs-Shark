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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Eagle;
import model.Shark;

public class TurnPanel
        extends JPanel
        implements ActionListener {

    private JLabel playerTurnJLabel;

    private JList<String> pieceJList;

    private boolean isEaglePlayer;

    private GameView gameView;
    private ActionListener listener;

    TurnPanel(GameView gameView, ActionListener listener, Color background) {
        this.gameView = gameView;
        this.listener = listener;

        setSize(160, 500);

        playerTurnJLabel = new JLabel();
        playerTurnJLabel.setPreferredSize(new Dimension(150, 20));
        playerTurnJLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        playerTurnJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerTurnJLabel);

        pieceJList = new JList<>();
        pieceJList.setBackground(background);
        pieceJList.setBorder(new LineBorder(Color.BLACK));
        pieceJList.setFont(new Font("Arial", Font.PLAIN, 18));
        pieceJList.setLocation(10, 50);
        add(pieceJList);

        String[] upDownButtons = {"Up", "Down"};
        String moveUp = upDownButtons[0];
        JButton moveUpButton = new JButton(moveUp);
        List<JButton> JButtonsList = new ArrayList<>();
        JButtonsList.add(moveUpButton);
        moveUpButton.setPreferredSize(new Dimension(135, 30));
        moveUpButton.addActionListener(this);
        add(moveUpButton);

        String[] leftRightButtons = {"Left", "Right"};
        for (String longSnakeString : leftRightButtons) {
            JButton longSnakeButton = new JButton(longSnakeString);
            JButtonsList.add(longSnakeButton);
            longSnakeButton.setPreferredSize(new Dimension(65, 50));
            longSnakeButton.addActionListener(this);
            add(longSnakeButton);
        }

        String moveDown = upDownButtons[1];
        JButton moveDownButton = new JButton(moveDown);
        JButtonsList.add(moveDownButton);
        moveDownButton.setPreferredSize(new Dimension(135, 30));
        moveDownButton.addActionListener(this);
        add(moveDownButton);

        JButton nextTurnButton = new JButton("Next Turn");
        JButtonsList.add(nextTurnButton);
        nextTurnButton.setPreferredSize(new Dimension(135, 50));
        nextTurnButton.addActionListener(this);
        add(nextTurnButton);

        revalidate();
        repaint();

    }

    private String currentPlayerName() {

        if (isEaglePlayer) {
            return "Eagle";
        } else {
            return "Shark";
        }
    }

    public void updatePieceJList() {
        playerTurnJLabel.setText(currentPlayerName() + "'s turn");

        if (isEaglePlayer) {
            final List<Eagle> eagleList = gameView.getEagleList();
            final int eagleListSize = eagleList.size();
            String[] pieceCoordArray = new String[eagleListSize];
            for (int i = 0; i < eagleListSize; i++) {
                final Eagle eagle = eagleList.get(i);
                pieceCoordArray[i] = ("Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1));
            }

            pieceJList.setListData(pieceCoordArray);

        } else {

            final List<Shark> sharkList = gameView.getSharkList();
            final int sharkListSize = sharkList.size();
            String[] pieceCoordArray = new String[sharkListSize];
            for (int i = 0; i < sharkListSize; i++) {
                final Shark shark = sharkList.get(i);
                pieceCoordArray[i] = ("Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1));
            }

            pieceJList.setListData(pieceCoordArray);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }

    public int getPieceJListSelectedItem() {
        return pieceJList.getSelectedIndex();

    }

    public void setCurrentPlayer(boolean isEaglePlayer) {
        this.isEaglePlayer = isEaglePlayer;
    }
}
