package view;

import controller.GameController;
import model.BoardSize;
import model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    public StartPanel() {
    }

    public void init(JFrame jFrame, GameController gameController) {
        this.setLayout(null);

        JLabel label = new JLabel("Custom Your Game Board");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        label.setBounds(35, 40, 300, 20);
        add(label);

        JLabel tips = new JLabel("row and column: between 4 and 10");
        tips.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        tips.setBounds(65, 60, 300, 20);
        add(tips);

        JLabel rowLable = new JLabel("Row：");
        rowLable.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        JTextField rowText = new JTextField(100);
        rowText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowLable.setBounds(60, 100, 80, 30);
        rowText.setBounds(120, 100, 100, 30);
        add(rowLable);
        add(rowText);

        JLabel columnLable = new JLabel("Column：");
        columnLable.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        JTextField columnText = new JTextField(100);
        columnText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnLable.setBounds(60, 150, 80, 30);
        columnText.setBounds(120, 150, 100, 30);
        add(columnLable);
        add(columnText);

        JButton button = new JButton("Start");
        button.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        button.setBounds(120, 200, 60, 30);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int rowValue= 0;
                int columnValue =0;
                try{
                    rowValue = Integer.valueOf(rowText.getText());
                    columnValue = Integer.valueOf(columnText.getText());
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Numbers only");
                    return;
                }

                jFrame.dispose();
                //load board
                new BoardSize(rowValue, columnValue);
                gameController.startGame(new GameModel(), new GameView());
            }
        });//end of action listerner


    }
}