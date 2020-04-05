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
import model.Movable;
import model.Shark;

public class MovementPanel
        extends JPanel
        implements ActionListener {

    private final JList<String> moveJList;
    private final GameView gameView;
    private final ActionListener listener;
    private List<int[]> movementCoordArray = new LinkedList<>();
    private int[] movementCoord;

    MovementPanel(GameView gameView, ActionListener listener, Color background) {
        this.gameView = gameView;
        this.listener = listener;

        moveJList = new JList<>();
        moveJList.setBackground(background);
        moveJList.setBorder(new LineBorder(Color.BLACK));
        moveJList.setFont(new Font("Arial", Font.PLAIN, 18));
        moveJList.setLocation(10, 50);
        add(moveJList);

        JButton moveButton = new JButton("Move");
        moveButton.setSize(80, 180);
        moveButton.addActionListener(this);
        add(moveButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //movementCoord = movementType[moveJList.getSelectedIndex()];

        listener.actionPerformed(e);
    }

    public void updateMoveJList(int index, Movable piece) {

        List<String> pieceCoordList = new LinkedList<>();

        if (piece instanceof Eagle) {
            Eagle eagle = gameView.getEagleList().get(index);

            for (int[] movablecoord : piece.getMovableCoords()) {

                int[] validCoord = new int[2];
                validCoord[0] = eagle.getRow() + movablecoord[0] + 1;
                validCoord[1] = eagle.getColumn() + movablecoord[1] + 1;

                if (!(validCoord[0] < 1 || validCoord[0] > BoardSize.BOARD_ROWS || validCoord[1] < 1 || validCoord[1] > BoardSize.BOARD_COLUMNS)) {
                    pieceCoordList.add(validCoord[0] + " " + validCoord[1]);
                    movementCoordArray.add(movablecoord);
                }

            }
        } else {
            Shark shark = gameView.getSharkList().get(index);

            for (int[] movablecoord : piece.getMovableCoords()) {

                int[] validCoord = new int[2];
                validCoord[0] = shark.getRow() + movablecoord[0] + 1;
                validCoord[1] = shark.getColumn() + movablecoord[1] + 1;

                if (!(validCoord[0] < 1 || validCoord[0] > BoardSize.BOARD_ROWS || validCoord[1] < 1 || validCoord[1] > BoardSize.BOARD_COLUMNS)) {
                    pieceCoordList.add(validCoord[0] + " " + validCoord[1]);
                    movementCoordArray.add(movablecoord);
                }
            }

        }

        // Must use variable here
        String[] pieceCoordArray = pieceCoordList.toArray(new String[pieceCoordList.size()]);

        moveJList.setListData(pieceCoordArray);
        moveJList.setVisible(true);
    }

    int getMoveJListSelectedItem() {
        return moveJList.getSelectedIndex();

    }

    int[] getMovementCoord() {

        return movementCoordArray.get(moveJList.getSelectedIndex());
    }

}
