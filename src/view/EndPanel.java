package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;

public class EndPanel extends JPanel {

    public void init(JFrame jFrame, GameController gameController, String winnerName) {
        this.setLayout(null);
        String text = winnerName + "  WON!";
        JLabel label = new JLabel(text);
        label.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        label.setBounds(60, 40, 200, 40);
        add(label);

        JButton startButton = new JButton("Start a new game");
        startButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        startButton.setBounds(35, 150, 150, 30);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jFrame.dispose();
                // open GameBoard
                gameController.showStartView();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        exitButton.setBounds(200, 150, 80, 30);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
    }
}
