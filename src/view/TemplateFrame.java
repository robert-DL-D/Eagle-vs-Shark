package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import static model.StringText.WINDOW_TITLE;

public class TemplateFrame extends JFrame {

    public TemplateFrame() {
        setSize(500, 500);
        setTitle(WINDOW_TITLE);
    }

    public void showStartView() {
        Container con = getContentPane();
        con.setLayout(null);

        StartPanel startPanel = new StartPanel(this);
        startPanel.setLocation(50, 30);
        startPanel.setSize(400, 400);
        startPanel.setBorder(new LineBorder(Color.BLACK));

        con.add(startPanel);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void showEndView(String winnerName) {
        Container con = getContentPane();
        con.setLayout(null);

        EndPanel endPanel = new EndPanel();
        endPanel.init(this, winnerName);
        endPanel.setLocation(100, 80);
        endPanel.setSize(300, 300);

        con.add(endPanel);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
