package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import controller.GameController;

public class TemplateFrame extends JFrame {
    public TemplateFrame() {
        this.setSize(500, 500);
        this.setTitle("Shark And Eagle Board Game");
    }

    public void showStartView(GameController gameController) {
        Container con = this.getContentPane();
        con.setLayout(null);

        StartPanel startPanel = new StartPanel();
        startPanel.init(this, gameController);
        startPanel.setLocation(100, 80);
        startPanel.setSize(300, 300);
        startPanel.setBorder(new LineBorder(Color.BLACK));

        con.add(startPanel);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void showEndView(GameController gameController, String winnerName) {
        Container con = this.getContentPane();
        con.setLayout(null);

        EndPanel endPanel = new EndPanel();
        endPanel.init(this, gameController, winnerName);
        endPanel.setLocation(100, 80);
        endPanel.setSize(300, 300);

        con.add(endPanel);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
