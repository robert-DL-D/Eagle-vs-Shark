package view;

import com.sun.istack.internal.NotNull;

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

    private final JList<String> PIECE_JLIST;
    private final List<JButton> ABILITIES_JBUTTON_LIST;

    private final GameView GAMEVIEW;
    private final ActionListener ACTIONLISTENER;
    private boolean isEaglePlayerTurn;

    AbilityPanel(GameView GAMEVIEW, ActionListener ACTIONLISTENER, Color background) {
        this.GAMEVIEW = GAMEVIEW;
        this.ACTIONLISTENER = ACTIONLISTENER;

        JLabel abilityLabel = new JLabel("Abilities");
        abilityLabel.setPreferredSize(new Dimension(150, 20));
        abilityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        abilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(abilityLabel);

        ABILITIES_JBUTTON_LIST = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            JButton jButton = new JButton();
            ABILITIES_JBUTTON_LIST.add(jButton);
            jButton.setPreferredSize(new Dimension(135, 30));
            jButton.addActionListener(this);
            add(jButton);
        }

        PIECE_JLIST = new JList<>();
        PIECE_JLIST.setBackground(background);
        PIECE_JLIST.setBorder(new LineBorder(Color.BLACK));
        PIECE_JLIST.setFont(new Font("Arial", Font.PLAIN, 18));
        PIECE_JLIST.setLocation(10, 50);
        //PIECE_JLIST.setVisible(false);
        PIECE_JLIST.setVisible(true);
        add(PIECE_JLIST);

        JButton jButton = new JButton("Use Ability");
        jButton.setPreferredSize(new Dimension(135, 30));
        jButton.addActionListener(this);
        add(jButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ACTIONLISTENER.actionPerformed(e);
    }

    public void setButtonText() {

        int size = ABILITIES_JBUTTON_LIST.size();
        for (int i = 0; i < size; i++) {
            JButton button = ABILITIES_JBUTTON_LIST.get(i);

            if (isEaglePlayerTurn) {
                List<Eagle> eagleList = GAMEVIEW.getEagleList();
                Eagle eagle = eagleList.get(i * 2);

                String s = eagle.getAbility().toString();

                button.setText(s);

            } else {
                List<Shark> sharkList = GAMEVIEW.getSharkList();
                Shark shark = sharkList.get(i * 2);

                @NotNull
                String s = shark.getAbility().toString();

                button.setText(s);
            }
        }
    }

    public void updatePieceJList() {

        if (isEaglePlayerTurn) {

            List<Shark> sharkList = GAMEVIEW.getSharkList();
            int sharkListSize = sharkList.size();
            String[] pieceCoordArray = new String[sharkListSize];

            for (int i = 0; i < sharkListSize; i++) {
                Shark shark = sharkList.get(i);
                pieceCoordArray[i] = ("Shark " + (i + 1) + ": " + (shark.getRow() + 1) + " " + (shark.getColumn() + 1));
            }

            PIECE_JLIST.setListData(pieceCoordArray);
        } else {

            List<Eagle> eagleList = GAMEVIEW.getEagleList();
            int eagleListSize = eagleList.size();
            String[] pieceCoordArray = new String[eagleListSize];

            for (int i = 0; i < eagleListSize; i++) {
                Eagle eagle = eagleList.get(i);
                pieceCoordArray[i] = ("Eagle " + (i + 1) + ": " + (eagle.getRow() + 1) + " " + (eagle.getColumn() + 1));
            }

            PIECE_JLIST.setListData(pieceCoordArray);

        }

    }

    int getPieceJListSelectedItem() {
        return PIECE_JLIST.getSelectedIndex();
    }

    public void setIsEaglePlayer(boolean isEaglePlayer) {
        this.isEaglePlayerTurn = isEaglePlayer;
    }

}
