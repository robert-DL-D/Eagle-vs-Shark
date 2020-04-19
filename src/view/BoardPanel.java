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
import model.Island;
import model.Shark;
import model.Square;
import model.Types;

public class BoardPanel
        extends JPanel
        implements MouseListener {

    private static final int BOARD_MARGIN = 25;
    private static final int SQUARE_SIZE = 60;
    private static final int PIC_SIZE = 58;
    private static final int AXIS_NUMBER_MARGIN = 40;
    private static final String FOLDER_PATH = "src/images";
    private final GameView GAMEVIEW;
    private Square[][] squares;

    BoardPanel(GameView gameView) {
        GAMEVIEW = gameView;
    }

    private static int gridCoord(int i) {
        return i * SQUARE_SIZE + BOARD_MARGIN;
    }

    private static int picGridCoord(int i) {
        int picMargin = 2;
        return BOARD_MARGIN + SQUARE_SIZE * i + picMargin;
    }

    static int getBoardMargin() {
        return BOARD_MARGIN;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        drawSquare(g);
        drawLines(g);
        drawEagle(g, toolkit, GAMEVIEW.getEagleList());
        drawShark(g, toolkit, GAMEVIEW.getSharkList());
        drawFlag(g, toolkit, GAMEVIEW.getFlagList());
        drawIsland(g, toolkit, GAMEVIEW.getIslandList());
        drawNumber(g);
    }

    private void drawSquare(Graphics g) {
        Color skyBlue = new Color(135, 206, 235);
        Color oceanBlue = new Color(0, 105, 148);
        Color neutralColor = Color.WHITE;

        for (int i = 0; i < BoardSize.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardSize.BOARD_COLUMNS; j++) {

                if (i == BoardSize.BOARD_ROWS / 2 - 1 || i == BoardSize.BOARD_ROWS / 2) {
                    g.setColor(neutralColor);
                } else if (squares[i][j].getSQUARE_NUMBER() - 1 < BoardSize.BOARD_ROWS / 2 * BoardSize.BOARD_COLUMNS) {
                    g.setColor(skyBlue);
                } else {
                    g.setColor(oceanBlue);
                }

                g.fillRect(gridCoord(j), gridCoord(i), SQUARE_SIZE, SQUARE_SIZE);

            }
        }
    }

    private void drawLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        // draw the rows
        for (int i = 0; i < BoardSize.BOARD_ROWS + 1; i++) {
            g.drawLine(BOARD_MARGIN, gridCoord(i), getWidth() - BOARD_MARGIN, gridCoord(i));
        }

        // draw the columns
        for (int i = 0; i < BoardSize.BOARD_COLUMNS + 1; i++) {
            g.drawLine(gridCoord(i), BOARD_MARGIN, gridCoord(i), getHeight() - BOARD_MARGIN);
        }
    }

    private void drawEagle(Graphics g, Toolkit toolkit, List<Eagle> eagleList) {

        for (Eagle eagle : eagleList) {
            if (eagle.getType() == Types.RED) {
                g.drawImage(toolkit.getImage(FOLDER_PATH + "/red_eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (eagle.getType() == Types.GREEN) {
                g.drawImage(toolkit.getImage(FOLDER_PATH + "/green_eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (eagle.getType() == Types.BLUE) {
                g.drawImage(toolkit.getImage(FOLDER_PATH + "/blue_eagle.png"), picGridCoord(eagle.getColumn()), picGridCoord(eagle.getRow()), PIC_SIZE, PIC_SIZE, this);
            }
        }
    }

    private void drawShark(Graphics g, Toolkit toolkit, List<Shark> sharkList) {

        for (Shark shark : sharkList) {
            if (shark.getType() == Types.RED) {
                g.drawImage(toolkit.getImage(FOLDER_PATH + "/red_shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (shark.getType() == Types.GREEN) {
                g.drawImage(toolkit.getImage(FOLDER_PATH + "/green_shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);
            } else if (shark.getType() == Types.BLUE) {
                g.drawImage(toolkit.getImage(FOLDER_PATH + "/blue_shark.png"), picGridCoord(shark.getColumn()), picGridCoord(shark.getRow()), PIC_SIZE, PIC_SIZE, this);
            }
        }
    }

    private void drawFlag(Graphics g, Toolkit toolkit, List<Flag> flagList) {
        for (Flag flag : flagList) {
            g.drawImage(toolkit.getImage(FOLDER_PATH + "/flag.png"), picGridCoord(flag.getColumn()), picGridCoord(flag.getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    private void drawNumber(Graphics g) {
        // Draw the square number
        for (int i = 0; i < BoardSize.BOARD_ROWS; i++) {
            for (int j = 0; j < BoardSize.BOARD_COLUMNS; j++) {
                g.drawString(Integer.toString(squares[i][j].getSQUARE_NUMBER()), j * SQUARE_SIZE + 30, i * SQUARE_SIZE + 40);
            }
        }

        // Draw the Y-axis number
        for (int i = 0; i < BoardSize.BOARD_ROWS; i++) {
            g.drawString(Integer.toString(i + 1), 5, i * SQUARE_SIZE + AXIS_NUMBER_MARGIN);

        }

        // Draw the X-axis number

        for (int i = 0; i < BoardSize.BOARD_COLUMNS; i++) {
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(i + 1), i * SQUARE_SIZE + AXIS_NUMBER_MARGIN, 18);
        }
    }

    private void drawIsland(Graphics g, Toolkit toolkit, List<Island> islandList) {
        for (Island island : islandList) {
            g.drawImage(toolkit.getImage(FOLDER_PATH + "/island.png"), picGridCoord(island.getColumn()), picGridCoord(island.getRow()), PIC_SIZE, PIC_SIZE, this);
        }
    }

    void setSquares(Square[][] squares) {
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
