package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.MovablePiece;

class EnemyPanel
        extends JPanel {

    private final List<JLabel> PIECE_LABEL_LIST = new ArrayList<>();

    EnemyPanel(ActionListener actionListener) {

        JLabel instructionLabel = new JLabel("Enemy Pieces");
        instructionLabel.setPreferredSize(new Dimension(250, 20));
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(instructionLabel);

    }

    void createLabels(List<? extends MovablePiece> currentPieceList) {

        if (!PIECE_LABEL_LIST.isEmpty()) {
            for (JLabel jLabel : PIECE_LABEL_LIST) {
                remove(jLabel);
            }

            PIECE_LABEL_LIST.clear();
        }

        for (int i = 0; i < currentPieceList.size(); i++) {

            JLabel pieceLabel = new JLabel();
            PIECE_LABEL_LIST.add(pieceLabel);
            pieceLabel.setPreferredSize(new Dimension(200, 30));
            pieceLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            pieceLabel.setLocation(70, 30 * i);
            add(pieceLabel);
        }

        setLabelText(currentPieceList);

    }

    void setLabelText(List<? extends MovablePiece> currentPieceList) {

        for (int i = 0; i < PIECE_LABEL_LIST.size(); i++) {
            MovablePiece movablePiece = currentPieceList.get(i);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(movablePiece.getType())
                    .append(" ")
                    .append(movablePiece.getClass().getSuperclass().getSimpleName())
                    .append(": ")
                    .append(movablePiece.getRow() + 1)
                    .append(" ")
                    .append(movablePiece.getColumn() + 1)
                    .append(" ");

            if (movablePiece.isStunned()) {
                stringBuilder.append("STUNNED");
            } else if (movablePiece.isSlowed()) {
                stringBuilder.append("SLOWED");
            } else if (movablePiece.isShielded()) {
                stringBuilder.append("SHIELDED");
            } else if (movablePiece.isImmune()) {
                stringBuilder.append("IMMUNED");
            }

            PIECE_LABEL_LIST.get(i).setText(stringBuilder.toString());

        }
    }

}

