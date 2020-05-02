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
    private static final String USE_ABILITY_BUTTON_TEXT = "Use";
    private final JButton useAbilityButton;
    private String lastAbilityUsed;

    private final GameView GAMEVIEW;
    private final ActionListener ACTIONLISTENER;
    private boolean isEaglePlayerTurn;

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
        PIECE_JLIST.setVisible(false);
        add(PIECE_JLIST);

        useAbilityButton = new JButton(USE_ABILITY_BUTTON_TEXT);
        useAbilityButton.setPreferredSize(new Dimension(135, 30));
        useAbilityButton.addActionListener(this);
        useAbilityButton.setVisible(false);
        add(useAbilityButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void setAbilityButtonText() {

        for (int i = 0; i < ABILITIES_JBUTTON_LIST.size(); i++) {
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
            button.setEnabled(true);
        }
    }

    private void updatePieceJList(boolean listForEnemy) {

        List<? extends MovablePiece> movablePiecesList;

        if (isEaglePlayerTurn == listForEnemy) {
            movablePiecesList = GAMEVIEW.getSharkList();
        } else {
            movablePiecesList = GAMEVIEW.getEagleList();
        }

        String[] pieceCoordArray = new String[movablePiecesList.size()];

        for (int i = 0; i < movablePiecesList.size(); i++) {
            MovablePiece movablePiece = movablePiecesList.get(i);

            pieceCoordArray[i] = (movablePiece.getType() + " " + movablePiece.getClass().getSuperclass().getSimpleName()
                    + " " + (i + 1) + ": " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1));
        }

        PIECE_JLIST.setListData(pieceCoordArray);
        PIECE_JLIST.setVisible(true);
    }

    void selectedAbilityOnEnemy(String actionCommand) {
        updatePieceJList(true);
        setUseButtonText(actionCommand);
        setUseAbilityButton(true);

        lastAbilityUsed = actionCommand;
    }

    void selectedAbilityOnAlly(String actionCommand) {
        updatePieceJList(false);
        setUseButtonText(actionCommand);
        setUseAbilityButton(true);

        lastAbilityUsed = actionCommand;
    }

    void enableAbilityUI() {

        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            jButton.setEnabled(true);
        }

        setAbilityButtonText();

    }

    void disableAbilityUI() {

        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            jButton.setEnabled(false);
        }

        PIECE_JLIST.setVisible(false);
        setUseAbilityButton(false);
    }

    void setAfterUseText(MovablePiece movablePiece) {
        for (JButton jButton : ABILITIES_JBUTTON_LIST) {
            if (jButton.getText().equals(lastAbilityUsed)) {
                jButton.setText(jButton.getText() + ": "
                        + movablePiece.getClass().getSuperclass().getSimpleName()
                        + " " + (movablePiece.getRow() + 1) + " " + (movablePiece.getColumn() + 1));
            }
        }
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
        useAbilityButton.setText(USE_ABILITY_BUTTON_TEXT + ": " + actionCommand);
    }

    void resetUseAbilityButtonText() {
        useAbilityButton.setText(USE_ABILITY_BUTTON_TEXT);
    }

    void setUseAbilityButton(boolean status) {
        useAbilityButton.setVisible(status);
    }
}
