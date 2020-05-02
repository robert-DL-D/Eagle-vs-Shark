package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.BoardSize;
import model.MovablePiece;

public class MovementPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> MOVE_JLIST;
    private final ActionListener ACTIONLISTENER;
    private final List<int[]> MOVEMENT_COORD_LIST = new LinkedList<>();
    private final JButton MOVE_BUTTON;

    MovementPanel(ActionListener actionListener, Color background) {
        ACTIONLISTENER = actionListener;

        JLabel instructionLabel = new JLabel("<html>" + "Select a coordinate to move to" + "</html>");
        instructionLabel.setPreferredSize(new Dimension(130, 70));
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //instructionLabel.setBorder(new LineBorder(Color.BLACK));
        add(instructionLabel);

        MOVE_JLIST = new JList<>();
        MOVE_JLIST.setBackground(background);
        MOVE_JLIST.setBorder(new LineBorder(Color.BLACK));
        MOVE_JLIST.setFont(new Font("Arial", Font.PLAIN, 18));
        MOVE_JLIST.setLocation(10, 50);
        add(MOVE_JLIST);

        MOVE_BUTTON = new JButton("Move");
        MOVE_BUTTON.setSize(80, 180);
        MOVE_BUTTON.addActionListener(this);
        MOVE_BUTTON.setVisible(false);
        add(MOVE_BUTTON);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ACTIONLISTENER.actionPerformed(actionEvent);
    }

    void updateMoveJList(MovablePiece movablePiece) {

        if (!MOVEMENT_COORD_LIST.isEmpty()) {
            MOVEMENT_COORD_LIST.clear();
        }

        List<String> pieceCoordList = new LinkedList<>();

        for (int[] movablecoord : movablePiece.getMovableCoords()) {

            int[] validCoord = new int[2];
            validCoord[0] = movablePiece.getRow() + movablecoord[0] + 1;
            validCoord[1] = movablePiece.getColumn() + movablecoord[1] + 1;

            if (!(validCoord[0] < 1 || validCoord[0] > BoardSize.BOARD_ROWS || validCoord[1] < 1 || validCoord[1] > BoardSize.BOARD_COLUMNS)) {
                pieceCoordList.add(validCoord[0] + " " + validCoord[1]);
                MOVEMENT_COORD_LIST.add(movablecoord);
            }
        }

        // Must use variable here
        String[] pieceCoordArray = pieceCoordList.toArray(new String[pieceCoordList.size()]);

        MOVE_JLIST.setListData(pieceCoordArray);
        MOVE_JLIST.setVisible(true);
        MOVE_BUTTON.setVisible(true);
    }

    int[] getMovementCoord() {

        int selectedIndex = MOVE_JLIST.getSelectedIndex();
        return selectedIndex == -1 ? null : MOVEMENT_COORD_LIST.get(selectedIndex);

    }

    void method() {

        String[] empty = new String[0];

        MOVE_JLIST.setListData(empty);

        hideMovementUI();
    }

    void hideMovementUI() {
        MOVE_JLIST.setVisible(false);
        MOVE_BUTTON.setVisible(false);
    }

}
