package view;

import controller.GameController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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

//    public void showEndView(GameController gameController, String winnerName) {}

}