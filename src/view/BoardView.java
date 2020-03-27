package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.GameModel;
import model.Shark;
import model.Square;

public class BoardView
        extends JPanel
        implements Runnable {
    private static final int X_MARGIN = 20;
    private static final int Y_MARGIN = 20;
    private static final int WIDTH = 580;
    private static final int HEIGHT = 640;
    private static final int SQUARE_SIZE = 60;

    private static final Color boardColor1 = new Color(255, 255, 255);
    private static final Color boardColor2 = new Color(0, 119, 190);

    private GameModel gameModel;

    private Toolkit t = Toolkit.getDefaultToolkit();

    private double factor = 0.2;

    private ArrayList<Image> imageArrayList = new ArrayList<>();

    public BoardView(GameModel gameModel) {
        this.gameModel = gameModel;

        setSize(WIDTH, HEIGHT);

        new Thread(this).start();

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Square[][] localSquares = gameModel.getSquares();

        for (int i = 0; i < localSquares.length; i++) {
            for (int j = 0; j < localSquares[0].length; j++) {

                final Square square = localSquares[i][9 - j];

                if (square.getSquareNo() > GameModel.getROW() * GameModel.getCOLUMN() / 2) {
                    graphics.setColor(boardColor1);
                } else {
                    graphics.setColor(boardColor2);
                }

                graphics.fillRect(getGridCoord(i), getGridCoord(j), SQUARE_SIZE, SQUARE_SIZE);

            }
        }

        graphics.setColor(Color.BLACK);
        // draw the rows
        for (int i = 0; i < 11; i++) {
            graphics.drawLine(X_MARGIN, getGridCoord(i), WIDTH - X_MARGIN, getGridCoord(i));
        }

        // draw the columns
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(getGridCoord(i), Y_MARGIN, getGridCoord(i), HEIGHT - Y_MARGIN);
        }

        drawShark(graphics, gameModel.getSharkList());

        for (int i = 0; i < localSquares.length; i++) {
            for (int j = 0; j < localSquares[0].length; j++) {
                graphics.setColor(Color.BLACK);
                graphics.drawString(Integer.toString(localSquares[i][j].getSquareNo()), i * 60 + 25, j * 60 + 35);
            }
        }

    }

    private void drawShark(Graphics graphics, List<Shark> sharkList) {

        for (int i = 0; i < sharkList.size(); i++) {
            graphics.drawImage(t.getImage("src/images/shark.png"), 20 + 60 * sharkList.get(i).getX() + 2, 20 + 60 * sharkList.get(i).getY() + 2, 58, 58, this);

        }

    }

    public ArrayList<Image> getImageArrayList() {
        return imageArrayList;
    }

    private int getGridCoord(int i) {

        return i * 60 + 20;
    }

    private int getX(int pos) {
        pos--;
        if ((pos / 10) % 2 == 0) {
            return X_MARGIN + 10 + pos % 10 * 40;
        } else {
            return X_MARGIN + 370 - pos % 10 * 40;
        }
    }

    private int getY(int pos) {
        pos--;
        return Y_MARGIN - 30 + 400 - pos / 10 * 40;
    }

    public void run() {
        double inc = 0.05;
        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
            factor += inc;

            if (factor > 0.5 || factor < -0.5) {
                inc = -inc;
            }
            repaint();
        }
    }
}
