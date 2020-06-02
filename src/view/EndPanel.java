package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class EndPanel extends JPanel {

    void init(JFrame jFrame, String winnerName) {
        setLayout(null);
        String text = winnerName + "  WON!";
        JLabel label = new JLabel(text);
        label.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        label.setBounds(60, 40, 200, 40);
        add(label);

        JButton startButton = new JButton("Start a new game");
        startButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        startButton.setBounds(35, 150, 150, 30);
        add(startButton);

        startButton.addActionListener(arg0 -> {
            jFrame.dispose();
            // open GameBoard
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        exitButton.setBounds(200, 150, 80, 30);
        add(exitButton);
        exitButton.addActionListener(arg0 -> System.exit(0));
    }
}
