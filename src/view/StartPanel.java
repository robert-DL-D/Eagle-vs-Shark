package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GameController;
import model.BoardSize;

public class StartPanel extends JPanel {

    StartPanel(JFrame jFrame) {

        setLayout(null);

        JLabel label = new JLabel("Custom Your Game Board");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        label.setBounds(35, 40, 300, 20);
        add(label);

        JLabel tips = new JLabel("row and column: between 4 and 10");
        tips.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        tips.setBounds(65, 60, 300, 20);
        add(tips);

        JLabel rowLabel = new JLabel("Row：");
        rowLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowLabel.setBounds(80, 100, 80, 30);
        add(rowLabel);

        JTextField rowText = new JTextField();
        rowText.setText("10");
        rowText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowText.setBounds(120, 100, 100, 30);
        add(rowText);

        JLabel columnLabel = new JLabel("Column：");
        columnLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnLabel.setBounds(58, 150, 80, 30);
        add(columnLabel);

        JTextField columnText = new JTextField();
        columnText.setText("9");
        columnText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnText.setBounds(120, 150, 100, 30);
        add(columnText);

        JLabel timerLabel = new JLabel("Turn Time (sec)：");
        timerLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        timerLabel.setBounds(5, 200, 120, 30);
        add(timerLabel);

        JTextField timerText = new JTextField();
        timerText.setText("30");
        timerText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        timerText.setBounds(120, 200, 100, 30);
        add(timerText);

        JButton button = new JButton("Start");
        button.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        button.setBounds(120, 250, 100, 30);
        add(button);

        button.addActionListener(arg0 -> {
            int rowValue;
            int columnValue;
            int turnLimit;
            try {
                rowValue = Integer.parseInt(rowText.getText());
                columnValue = Integer.parseInt(columnText.getText());
                turnLimit = Integer.parseInt(timerText.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Numbers only!");
                return;
            }

            // TODO: Validations need to be updated
            if (4 <= rowValue && rowValue <= 10
                    && 4 <= columnValue && columnValue <= 10
                    && turnLimit > 0) {
                jFrame.dispose();
                // open GameBoard
                BoardSize.setBoardRows(rowValue);
                BoardSize.setBoardColumns(columnValue);
                new GameController(String.valueOf(turnLimit));
            } else {
                JOptionPane.showMessageDialog(null, "Numbers between 4 and 10!");
            }
        });
    }

}
