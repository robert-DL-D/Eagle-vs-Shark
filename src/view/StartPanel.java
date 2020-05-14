package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GameController;

public class StartPanel extends JPanel {

    public StartPanel(JFrame jFrame) {

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
        rowLabel.setBounds(60, 100, 80, 30);
        add(rowLabel);

        JTextField rowText = new JTextField();
        rowText.setText("10");
        rowText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowText.setBounds(120, 100, 100, 30);
        add(rowText);

        JLabel columnLabel = new JLabel("Column：");
        columnLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnLabel.setBounds(60, 150, 80, 30);
        add(columnLabel);

        JTextField columnText = new JTextField();
        columnText.setText("9");
        columnText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnText.setBounds(120, 150, 100, 30);
        add(columnText);

        JButton button = new JButton("Start");
        button.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        button.setBounds(120, 200, 100, 30);
        add(button);

        button.addActionListener(arg0 -> {
            int rowValue;
            int columnValue;
            try {
                rowValue = Integer.valueOf(rowText.getText());
                columnValue = Integer.valueOf(columnText.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Numbers only!");
                return;
            }

            if (4 <= rowValue && rowValue <= 10
                    && 4 <= columnValue && columnValue <= 10) {
                jFrame.dispose();
                // open GameBoard
                //new BoardSize(rowValue, columnValue);
                new GameController();
            } else {
                JOptionPane.showMessageDialog(null, "Numbers between 4 and 10!");
            }
        });
    }

}
