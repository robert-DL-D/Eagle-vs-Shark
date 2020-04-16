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

import model.MovablePiece;

public class AbilityPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> PIECE_JLIST;
    private final List<JButton> ABILITIES_JBUTTON_LIST;
    private static final String ABILITY_BUTTON_TEXT = "Use Ability";
    private final GameView GAMEVIEW;
    private final ActionListener ACTIONLISTENER;
    private boolean isEaglePlayerTurn;
    private final JButton useAbilityButton;

    AbilityPanel(GameView gameView, ActionListener actionListener, Color background) {
        GAMEVIEW = gameView;
        ACTIONLISTENER = actionListener;

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
        PIECE_JLIST.setVisible(true);
        add(PIECE_JLIST);

        useAbilityButton = new JButton(ABILITY_BUTTON_TEXT);
        useAbilityButton.setPreferredSize(new Dimension(135, 30));
        useAbilityButton.addActionListener(this);
        useAbilityButton.setEnabled(false);
        add(useAbilityButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void setAbilityButtonText() {

        int size = ABILITIES_JBUTTON_LIST.size();
        for (int i = 0; i < size; i++) {
            JButton button = ABILITIES_JBUTTON_LIST.get(i);
            List<? extends MovablePiece> movablePiecesList;

            if (isEaglePlayerTurn) {
                movablePiecesList = GAMEVIEW.getEagleList();
            } else {
                movablePiecesList = GAMEVIEW.getSharkList();
            }

            MovablePiece movablePiece = movablePiecesList.get(i * 2);

            String s = movablePiece.getAbility().toString();
            button.setText(s);
        }
    }

    private void updatePieceJList() {

        List<? extends MovablePiece> movablePiecesList;

        movablePiecesList = isEaglePlayerTurn ? GAMEVIEW.getSharkList() : GAMEVIEW.getEagleList();

        String[] pieceCoordArray = new String[movablePiecesList.size()];

        for (int i = 0; i < movablePiecesList.size(); i++) {
            MovablePiece movablePiece = movablePiecesList.get(i);

            pieceCoordArray[i] = (movablePiece.getType() + " " + movablePiece.getClass().getSuperclass().getSimpleName()
                    + " " + (i + 1) + ": " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1));
        }

        PIECE_JLIST.setListData(pieceCoordArray);
        PIECE_JLIST.setVisible(true);
    }

    void resetAbilityButtonText() {
        useAbilityButton.setText(ABILITY_BUTTON_TEXT);
    }

    void selectedStun(String actionCommand) {
        updatePieceJList();
        setUseButtonText(actionCommand);
        setUseAbilityButton(true);
    }

    JList<String> getPIECE_JLIST() {
        return PIECE_JLIST;
    }

    int getPieceJListSelectedItem() {
        return PIECE_JLIST.getSelectedIndex();
    }

    void setIsEaglePlayer(boolean isEaglePlayerTurn) {
        this.isEaglePlayerTurn = isEaglePlayerTurn;
    }

    private void setUseButtonText(String actionCommand) {
        useAbilityButton.setText(ABILITY_BUTTON_TEXT + ": " + actionCommand);
    }

    void setUseAbilityButton(boolean status) {
        useAbilityButton.setEnabled(status);
    }
}
