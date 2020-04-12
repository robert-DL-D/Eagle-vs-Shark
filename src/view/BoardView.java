package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;

import model.BoardSize;
import model.Eagle;
import model.Flag;
import model.Shark;
import model.Square;
import model.Types;

public class BoardView
        extends JPanel implements MouseListener {

    private static final int BOARD_MARGIN = 25;
    private static final int WIDTH = 590;
    private static final int HEIGHT = 650;
    private static final int SQUARE_SIZE = 60;
    private static final int PIC_SIZE = 58;
    private static final int PIC_MARGIN = 2;
    private static final int AXIS_NUMBER_MARGIN = 40;
    private static final Color SKY_COLOR = new Color(255, 255, 255);
    private static final Color OCEAN_COLOR = new Color(0, 119, 190);
    private static final String FOLDER_PATH = "src/images";
    private final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    private final GameView GAMEVIEW;
    private Square[][] squares;

    BoardView(GameView GAMEVIEW) {
        this.GAMEVIEW = GAMEVIEW;
    }

    private static int gridCoord(int i) {
        return i * SQUARE_SIZE + BOARD_MARGIN;
    }

    private static int picGridCoord(int i) {
        return BOARD_MARGIN + SQUARE_SIZE * i + PIC_MARGIN;
    }

    static int getBoardMargin() {
        return BOARD_MARGIN;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSquare(g, BoardSize.BOARD_ROWS, BoardSize.BOARD_COLUMNS);
        drawLines(g);
        drawEagle(g, GAMEVIEW.getEagleList());
        drawShark(g, GAMEVIEW.getSharkList());
        drawFlag(g, GAMEVIEW.getFlagList());
        drawNumber(g, BoardSize.BOARD_ROWS, BoardSize.BOARD_COLUMNS);
    }

    private void drawSquare(Graphics g, int numberOfRows, int numberOfColumns) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                if (squares[i][j].getSQUARE_NUMBER() - 1 < BoardSize.BOARD_ROWS / 2 * BoardSize.BOARD_COLUMNS) {
                    g.setColor(SKY_COLOR);
                } else {
                    g.setColor(OCEAN_COLOR);
                }

                g.fillRect(gridCoord(j), gridCoord(i), SQUARE_SIZE, SQUARE_SIZE);

            }
        }
    }

    private void drawFlag(Graphics g, List<Flag> flagList) {
        int numberOfFlag = GAMEVIEW.getFlagList().size();

        for (int i = 0; i < numberOfFlag; i++) {
            g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/flag.png"), picGridCoord(flagList.get(i).getColumn()), picGridCoord(flagList.get(i).getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    private void drawEagle(Graphics g, List<Eagle> eagleList) {

        for (Eagle eagle : eagleList) {
            if (eagle.getType() == Types.RED) {
                g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/red_eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (eagle.getType() == Types.GREEN) {
                g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/green_eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (eagle.getType() == Types.BLUE) {
                g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/blue_eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
            }
        }
    }

    private void drawShark(Graphics g, List<Shark> sharkList) {

        for (Shark shark : sharkList) {
            if (shark.getType() == Types.RED) {
                g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/red_shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (shark.getType() == Types.GREEN) {
                g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/green_shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (shark.getType() == Types.BLUE) {
                g.drawImage(TOOLKIT.getImage(FOLDER_PATH + "/blue_shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);
            }
        }
    }

    private void drawLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        // draw the rows
        for (int i = 0; i < BoardSize.BOARD_ROWS + 1; i++) {
            g.drawLine(BOARD_MARGIN, gridCoord(i), WIDTH - BOARD_MARGIN, gridCoord(i));
        }

        // draw the columns
        for (int i = 0; i < BoardSize.BOARD_COLUMNS + 1; i++) {
            g.drawLine(gridCoord(i), BOARD_MARGIN, gridCoord(i), HEIGHT - BOARD_MARGIN);
        }
    }

    private void drawNumber(Graphics g, int numberOfRows, int numberOfColumns) {
        // Draw the square number
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                g.drawString(Integer.toString(squares[i][j].getSQUARE_NUMBER()), j * SQUARE_SIZE + 30, i * SQUARE_SIZE + 40);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
