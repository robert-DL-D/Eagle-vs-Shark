package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.BoardConfig;
import model.Eagle;
import model.Flag;
import model.Island;
import model.MovablePiece;
import model.Piece;
import model.Shark;
import model.Square;
import model.Types;

public class BoardPanel
        extends JPanel {

    private static final int BOARD_MARGIN = 25;
    private static final int SQUARE_SIZE = 60;
    private static final int PIC_MARGIN = 2;
    private static final int PIC_SIZE = SQUARE_SIZE - PIC_MARGIN;
    private static final int AXIS_NUMBER_MARGIN = 45;
    private static final String FOLDER_PATH = "src/images/";

    private Square[][] squares;
    private List<Eagle> eagleList;
    private List<Shark> sharkList;
    private List<Flag> flagList;
    private List<Island> islandList;
    private final List<MovablePiece> movablePieceList = new LinkedList<>();
    private final List<int[]> movablePieceCoord = new LinkedList<>();
    private final List<int[]> movableSquareCoord = new LinkedList<>();
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Graphics graphics;
    private MovablePiece selectedMovablePiece;

    BoardPanel(MouseListener mouseListener) {
        addMouseListener(mouseListener);
    }

    static int getBoardMargin() {
        return BOARD_MARGIN;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = g;

        drawSquare();
        drawLines();
        drawEagle(toolkit, eagleList);
        drawShark(toolkit, sharkList);
        drawFlag(toolkit, flagList);
        drawIsland(toolkit, islandList);
        drawNumber();
    }

    private void drawSquare() {

        for (int row = 0; row < BoardConfig.BOARD_ROWS; row++) {
            for (int column = 0; column < BoardConfig.BOARD_COLUMNS; column++) {

                if (row == BoardConfig.BOARD_ROWS / 2 - 1 || row == BoardConfig.BOARD_ROWS / 2) {
                    graphics.setColor(Color.WHITE);
                } else if (squares[row][column].getSQUARE_NUMBER() - 1 < BoardConfig.BOARD_ROWS / 2 * BoardConfig.BOARD_COLUMNS) {
                    graphics.setColor(new Color(135, 206, 235));
                } else {
                    graphics.setColor(new Color(0, 105, 148));
                }

                graphics.fillRect(gridCoord(column), gridCoord(row), SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        if (selectedMovablePiece != null) {
            graphics.setColor(Color.GREEN);

            for (int[] movableCoord : selectedMovablePiece.getMovableCoords()) {
                int row = selectedMovablePiece.getRow() + movableCoord[0];
                int column = selectedMovablePiece.getColumn() + movableCoord[1];

                if ((row >= 0 && column >= 0)
                        && (row < BoardConfig.BOARD_ROWS && column < BoardConfig.BOARD_COLUMNS)) {
                    graphics.fillRect(gridCoord(column), gridCoord(row), SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

    }

    private void drawLines() {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        // draw the rows
        for (int row = 0; row < BoardConfig.BOARD_ROWS + 1; row++) {
            graphics.drawLine(BOARD_MARGIN, gridCoord(row), BoardConfig.BOARD_COLUMNS * SQUARE_SIZE + BOARD_MARGIN, gridCoord(row));
        }

        // draw the columns
        for (int column = 0; column < BoardConfig.BOARD_COLUMNS + 1; column++) {
            graphics.drawLine(gridCoord(column), BOARD_MARGIN, gridCoord(column), BoardConfig.BOARD_ROWS * SQUARE_SIZE + BOARD_MARGIN);
        }

    }

    int gridCoord(int i) {
        return i * SQUARE_SIZE + BOARD_MARGIN;
    }

    private void drawEagle(Toolkit toolkit, List<Eagle> eagleList) {

        for (Eagle eagle : eagleList) {
            String folderPath = "";

            if (eagle.getType() == Types.RED) {
                folderPath = FOLDER_PATH + "red_eagle.png";
            } else if (eagle.getType() == Types.GREEN) {
                folderPath = FOLDER_PATH + "green_eagle.png";
            } else if (eagle.getType() == Types.BLUE) {
                folderPath = FOLDER_PATH + "blue_eagle.png";
            }

            drawImage(folderPath, eagle);
        }
    }

    private void drawShark(Toolkit toolkit, List<Shark> sharkList) {

        for (Shark shark : sharkList) {
            String folderPath = "";

            if (shark.getType() == Types.RED) {
                folderPath = FOLDER_PATH + "red_shark.png";
            } else if (shark.getType() == Types.GREEN) {
                folderPath = FOLDER_PATH + "green_shark.png";
            } else if (shark.getType() == Types.BLUE) {
                folderPath = FOLDER_PATH + "blue_shark.png";
            }

            drawImage(folderPath, shark);
        }
    }

    private void drawFlag(Toolkit toolkit, List<Flag> flagList) {
        for (Flag flag : flagList) {
            drawImage(FOLDER_PATH + "flag.png", flag);
        }
    }

    private void drawIsland(Toolkit toolkit, List<Island> islandList) {
        for (Island island : islandList) {
            drawImage(FOLDER_PATH + "island.png", island);
        }
    }

    private void drawImage(String folderPath, Piece piece) {
        graphics.drawImage(toolkit.getImage(folderPath), picGridCoord(piece.getColumn()), picGridCoord(piece.getRow()), PIC_SIZE, PIC_SIZE, this);

        if (!movablePieceList.contains(piece) && piece instanceof MovablePiece) {
            movablePieceList.add((MovablePiece) piece);
            addMovablePieceCoord((MovablePiece) piece);
        }

    }

    private void addMovablePieceCoord(MovablePiece movablePiece) {
        movablePieceCoord.add(new int[]{picGridCoord(movablePiece.getRow()), picGridCoord(movablePiece.getColumn())});
    }

    private int picGridCoord(int i) {
        return gridCoord(i) + PIC_MARGIN;
    }

    private void drawNumber() {
        graphics.setColor(Color.BLACK);

        // Draw the square number
        for (int i = 0; i < BoardConfig.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardConfig.BOARD_COLUMNS; j++) {
                graphics.drawString(Integer.toString(squares[i][j].getSQUARE_NUMBER()), j * SQUARE_SIZE + 30, i * SQUARE_SIZE + 40);
            }
        }

        // Draw the Y-axis number
        for (int i = 0; i < BoardConfig.BOARD_ROWS; i++) {
            graphics.drawString(Integer.toString(i + 1), 4, i * SQUARE_SIZE + AXIS_NUMBER_MARGIN);
        }

        // Draw the X-axis number
        for (int i = 0; i < BoardConfig.BOARD_COLUMNS; i++) {
            graphics.drawString(Integer.toString(i + 1), i * SQUARE_SIZE + AXIS_NUMBER_MARGIN, 20);
        }

    }

    void showValidSquares(MovablePiece selectedMovablePiece) {
        this.selectedMovablePiece = selectedMovablePiece;
        revalidate();
        repaint();
    }

    void setBoard(Square[][] squares, List<Eagle> eagleList, List<Shark> sharkList,
                  List<Flag> flagList, List<Island> islandList) {
        this.squares = squares;
        this.eagleList = eagleList;
        this.sharkList = sharkList;
        this.flagList = flagList;
        this.islandList = islandList;
    }

    void removeMovablePiece() {
        selectedMovablePiece = null;
        movableSquareCoord.clear();
        revalidate();
        repaint();
    }

    void updateMovablePieceCoord() {
        movablePieceCoord.clear();

        for (MovablePiece movablePiece : movablePieceList) {
            addMovablePieceCoord(movablePiece);
        }
    }

    List<int[]> getMovablePieceCoord() {
        return movablePieceCoord;
    }

    int getPicSize() {
        return PIC_SIZE;
    }

    int getSquareSize() {
        return SQUARE_SIZE;
    }

    List<MovablePiece> getMovablePieceList() {
        return movablePieceList;
    }

    MovablePiece getSelectedMovablePiece() {
        return selectedMovablePiece;
    }

    List<int[]> getMovableSquareCoord() {
        return movableSquareCoord;
    }

}
