package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.BoardSize;
import model.Eagle;
import model.MovablePiece;
import model.Shark;

public class MovementPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> MOVE_JLIST;
    private final GameView GAMEVIEW;
    private final ActionListener ACTIONLISTENER;
    private List<int[]> MOVEMENT_COORD_LIST = new LinkedList<>();

    MovementPanel(GameView GAMEVIEW, ActionListener ACTIONLISTENER, Color background) {
        this.GAMEVIEW = GAMEVIEW;
        this.ACTIONLISTENER = ACTIONLISTENER;

        MOVE_JLIST = new JList<>();
        MOVE_JLIST.setBackground(background);
        MOVE_JLIST.setBorder(new LineBorder(Color.BLACK));
        MOVE_JLIST.setFont(new Font("Arial", Font.PLAIN, 18));
        MOVE_JLIST.setLocation(10, 50);
        add(MOVE_JLIST);

        JButton moveButton = new JButton("Move");
        moveButton.setSize(80, 180);
        moveButton.addActionListener(this);
        add(moveButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ACTIONLISTENER.actionPerformed(e);
    }

    void updateMoveJList(int index, MovablePiece piece) {

        if (!MOVEMENT_COORD_LIST.isEmpty()) {
            MOVEMENT_COORD_LIST.clear();
        }

        List<String> pieceCoordList = new LinkedList<>();

        if (piece instanceof Eagle) {
            Eagle eagle = GAMEVIEW.getEagleList().get(index);

            for (int[] movablecoord : piece.getMovableCoords()) {

                int[] validCoord = new int[2];
                validCoord[0] = eagle.getRow() + movablecoord[0] + 1;
                validCoord[1] = eagle.getColumn() + movablecoord[1] + 1;

                if (!(validCoord[0] < 1 || validCoord[0] > BoardSize.BOARD_ROWS
                        || validCoord[1] < 1 || validCoord[1] > BoardSize.BOARD_COLUMNS)) {
                    pieceCoordList.add(validCoord[0] + " " + validCoord[1]);
                    MOVEMENT_COORD_LIST.add(movablecoord);
                }

            }
        } else {
            Shark shark = GAMEVIEW.getSharkList().get(index);

            for (int[] movablecoord : piece.getMovableCoords()) {

                int[] validCoord = new int[2];
                validCoord[0] = shark.getRow() + movablecoord[0] + 1;
                validCoord[1] = shark.getColumn() + movablecoord[1] + 1;

                if (!(validCoord[0] < 1 || validCoord[0] > BoardSize.BOARD_ROWS || validCoord[1] < 1 || validCoord[1] > BoardSize.BOARD_COLUMNS)) {
                    pieceCoordList.add(validCoord[0] + " " + validCoord[1]);
                    MOVEMENT_COORD_LIST.add(movablecoord);
                }
            }

        }

        // Must use variable here
        String[] pieceCoordArray = pieceCoordList.toArray(new String[pieceCoordList.size()]);

        MOVE_JLIST.setListData(pieceCoordArray);
        MOVE_JLIST.setVisible(true);
    }

    int[] getMovementCoord() {

        return MOVEMENT_COORD_LIST.get(MOVE_JLIST.getSelectedIndex());
    }

    public JList<String> getMOVE_JLIST() {
        return MOVE_JLIST;
    }
}
