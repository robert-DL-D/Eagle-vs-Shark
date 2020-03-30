package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JPanel;

import model.Eagle;
import model.Flag;
import model.GameModel;
import model.Shark;
import model.Square;

public class BoardView
        extends JPanel {

    private static final int BOARD_MARGIN = 25;
    private static final int WIDTH = 590;
    private static final int HEIGHT = 650;
    private static final int SQUARE_MARGIN = 2;
    private static final int SQUARE_SIZE = 60;
    private static final int PIC_SIZE = 58;
    private static final int AXIS_NUMBER_MARGIN = 40;
    private static final Color SKY_COLOR = new Color(255, 255, 255);
    private static final Color OCEAN_COLOR = new Color(0, 119, 190);
    private static final String FOLDER_PATH = "src/images";
    private final Toolkit t = Toolkit.getDefaultToolkit();
    private final Image FLAG_IMAGE = t.getImage(FOLDER_PATH + "/flag.png");
    private final Image EAGLE_IMAGE = t.getImage(FOLDER_PATH + "/eagle.png");
    private final Image sharkImage = t.getImage(FOLDER_PATH + "/shark.png");
    private final GameView gameView;
    private Square[][] squares;
    private int row;
    private int column;

    BoardView(GameView gameView) {
        this.gameView = gameView;

        setSize(WIDTH, HEIGHT);
    }

    private static int gridCoord(int i) {
        return i * SQUARE_SIZE + BOARD_MARGIN;
    }

    private static int picGridCoord(int i) {
        return BOARD_MARGIN + SQUARE_SIZE * i + SQUARE_MARGIN;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSquare(g, row, column);
        drawLines(g);
        drawEagle(g, gameView.getEagleList());
        drawShark(g, gameView.getSharkList());
        drawFlag(g, gameView.getFlagList());
        drawNumber(g, row, column);
    }

    private void drawSquare(Graphics g, int numberOfRows, int numberOfColumns) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                if (squares[i][j].getSquareNo() - 1 < GameModel.getROW() / 2 * GameModel.getCOLUMN()) {
                    g.setColor(SKY_COLOR);
                } else {
                    g.setColor(OCEAN_COLOR);
                }

                g.fillRect(gridCoord(j), gridCoord(i), SQUARE_SIZE, SQUARE_SIZE);

            }
        }
    }

    private void drawEagle(Graphics g, List<Eagle> eagleList) {

        for (Eagle eagle : eagleList) {
            g.drawImage(EAGLE_IMAGE, picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    private void drawShark(Graphics g, List<Shark> sharkList) {

        for (Shark shark : sharkList) {
            g.drawImage(sharkImage, picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);

        }
    }

    private void drawFlag(Graphics g, List<Flag> flagList) {
        int numberOfFlag = gameView.getFlagList().size();

        for (int i = 0; i < numberOfFlag; i++) {
            g.drawImage(FLAG_IMAGE, picGridCoord(flagList.get(i).getColumn()), picGridCoord(flagList.get(i).getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    private void drawLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        // draw the rows
        for (int i = 0; i < row + 1; i++) {
            g.drawLine(BOARD_MARGIN, gridCoord(i), WIDTH - BOARD_MARGIN, gridCoord(i));
        }

        // draw the columns
        for (int i = 0; i < column + 1; i++) {
            g.drawLine(gridCoord(i), BOARD_MARGIN, gridCoord(i), HEIGHT - BOARD_MARGIN);
        }
    }

    private void drawNumber(Graphics g, int numberOfRows, int numberOfColumns) {
        // Draw the square number
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                g.drawString(Integer.toString(squares[i][j].getSquareNo()), j * SQUARE_SIZE + 30, i * SQUARE_SIZE + 40);
            }
        }

        // Draw the Y-axis number
        for (int i = 0; i < numberOfRows; i++) {
            g.drawString(Integer.toString(i + 1), 5, i * SQUARE_SIZE + AXIS_NUMBER_MARGIN);

        }

        // Draw the X-axis number

        for (int i = 0; i < numberOfColumns; i++) {
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(i + 1), i * SQUARE_SIZE + AXIS_NUMBER_MARGIN, 18);
        }
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public void setBoardSize(int row, int column) {
        this.row = row;
        this.column = column;
    }

}
