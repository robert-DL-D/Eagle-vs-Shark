package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
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
    private static final Color boardColor1 = new Color(255, 255, 255);
    private static final Color boardColor2 = new Color(0, 119, 190);
    private Toolkit t = Toolkit.getDefaultToolkit();
    private GameView gameView;
    private Square[][] squares;
    private int row;
    private int column;

    public BoardView(GameView gameView) {
        this.gameView = gameView;

        setSize(WIDTH, HEIGHT);

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {

                final Square square = squares[i][j];

                if (square.getSquareNo() - 1 < GameModel.getROW() / 2 * GameModel.getCOLUMN()) {
                    graphics.setColor(boardColor1);
                } else {
                    graphics.setColor(boardColor2);
                }

                graphics.fillRect(gridCoord(j), gridCoord(i), SQUARE_SIZE, SQUARE_SIZE);

            }
        }

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        // draw the rows
        for (int i = 0; i < row + 1; i++) {
            graphics.drawLine(BOARD_MARGIN, gridCoord(i), WIDTH - BOARD_MARGIN, gridCoord(i));
        }

        // draw the columns
        for (int i = 0; i < column + 1; i++) {
            graphics.drawLine(gridCoord(i), BOARD_MARGIN, gridCoord(i), HEIGHT - BOARD_MARGIN);
        }

        drawEagle(graphics, gameView.getEagleList());
        drawShark(graphics, gameView.getSharkList());
        drawFlag(graphics, gameView.getFlagList());

        // Draw the square number
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                graphics.drawString(Integer.toString(squares[i][j].getSquareNo()), j * SQUARE_SIZE + 30, i * SQUARE_SIZE + 40);
            }
        }

        // Draw the Y-axis number
        for (int i = 0; i < squares.length; i++) {
            graphics.drawString(Integer.toString(i + 1), 5, i * SQUARE_SIZE + 40);

        }

        // Draw the X-axis number

        for (int i = 0; i < squares[0].length; i++) {
            graphics.setColor(Color.BLACK);
            graphics.drawString(Integer.toString(i + 1), i * SQUARE_SIZE + 40, 18);
        }

        repaint();

    }

    private void drawEagle(Graphics graphics, List<Eagle> eagleList) {

        for (Eagle eagle : eagleList) {
            graphics.drawImage(t.getImage("src/images/eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    private void drawShark(Graphics graphics, List<Shark> sharkList) {

        for (Shark shark : sharkList) {
            graphics.drawImage(t.getImage("src/images/shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);

        }
    }

    private void drawFlag(Graphics graphics, List<Flag> flagList) {
        for (int i = 0; i < gameView.getFlagList().size(); i++) {

            graphics.drawImage(t.getImage("src/images/flag.png"), picGridCoord(flagList.get(i).getColumn()), picGridCoord(flagList.get(i).getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    private int gridCoord(int i) {

        return i * SQUARE_SIZE + BOARD_MARGIN;
    }

    private int picGridCoord(int i) {
        return BOARD_MARGIN + SQUARE_SIZE * i + SQUARE_MARGIN;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public void setBoardSize(int row, int column) {
        this.row = row;
        this.column = column;
    }

}
