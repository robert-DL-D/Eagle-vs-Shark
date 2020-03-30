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

public class AbilityPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> pieceJList;

    private final GameView gameView;
    private final ActionListener listener;
    private final List<JButton> jButtonsList = new ArrayList<>();
    private boolean isEaglePlayer;

    AbilityPanel(GameView gameView, ActionListener listener, Color background) {
        this.gameView = gameView;
        this.listener = listener;

        setSize(160, 500);

        JLabel abilityLabel = new JLabel("Abilities");
        abilityLabel.setPreferredSize(new Dimension(150, 20));
        abilityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        abilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(abilityLabel);

        pieceJList = new JList<>();
        pieceJList.setBackground(background);
        pieceJList.setBorder(new LineBorder(Color.BLACK));
        pieceJList.setFont(new Font("Arial", Font.PLAIN, 18));
        pieceJList.setLocation(10, 50);
        add(pieceJList);

        //for (int i = 0; i < 3; i++) {

        JButton jButton = new JButton("Stun");
        jButtonsList.add(jButton);
        jButton.setPreferredSize(new Dimension(135, 30));
        jButton.addActionListener(this);
        add(jButton);
        //}

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }

    public void updatePieceJList() {

        if (isEaglePlayer) {

            List<Shark> sharkList = gameView.getSharkList();
            int sharkListSize = sharkList.size();
            String[] pieceCoordArray = new String[sharkListSize];

            for (int i = 0; i < sharkListSize; i++) {
                Shark shark = sharkList.get(i);
                pieceCoordArray[i] = ("Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1));
            }

            pieceJList.setListData(pieceCoordArray);
        } else {

            List<Eagle> eagleList = gameView.getEagleList();
            int eagleListSize = eagleList.size();
            String[] pieceCoordArray = new String[eagleListSize];

            for (int i = 0; i < eagleListSize; i++) {
                Eagle eagle = eagleList.get(i);
                pieceCoordArray[i] = ("Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1));
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
