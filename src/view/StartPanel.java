package view;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.GameController;
import file.LoadGame;
import model.BoardConfig;
import model.GameModel;
import model.StringText;

class StartPanel extends JPanel {

    private static final int MIN_ROW = 8;
    private static final int MAX_ROW = 18;
    private static final int MIN_COLUMN = 5;
    private static final int MAX_COLUMN = 17;
    private static final String ROW_MESSAGE = "even number between " + MIN_ROW + " to " + MAX_ROW;
    private static final String COLUMN_MESSAGE = "odd number between " + MIN_COLUMN + " to " + MAX_COLUMN;
    private static final int TURN_LIMIT = 999;

    StartPanel(JFrame jFrame) {

        setLayout(null);

        JLabel label = new JLabel("Game setting");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        label.setBounds(135, 20, 200, 25);
        add(label);

        JLabel rowLabel = new JLabel("<html><body>" + "Board Rows:" + "<br>" + "(" + ROW_MESSAGE + ")" + "<body></html>");
        rowLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowLabel.setBounds(50, 70, 200, 60);
        add(rowLabel);

        JTextField rowText = new JTextField();
        rowText.setText("10");
        rowText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowText.setBounds(250, 70, 50, 30);
        add(rowText);

        JLabel columnLabel = new JLabel("<html><body>" + "Board Columns:" + "<br>" + "(" + COLUMN_MESSAGE + ")" + "<body></html>");
        columnLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnLabel.setBounds(50, 120, 200, 60);
        add(columnLabel);

        JTextField columnText = new JTextField();
        columnText.setText("9");
        columnText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnText.setBounds(250, 120, 50, 30);
        add(columnText);

        JLabel timerLabel = new JLabel("Turn Time (sec):");
        timerLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        timerLabel.setBounds(50, 170, 200, 30);
        add(timerLabel);

        JTextField timerText = new JTextField();
        timerText.setText("180");
        timerText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        timerText.setBounds(250, 170, 50, 30);
        add(timerText);

        JLabel pieceNumLabel = new JLabel("Number of each piece type:");
        pieceNumLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        pieceNumLabel.setBounds(50, 220, 200, 30);
        add(pieceNumLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton type1 = new JRadioButton("1");
        type1.setBounds(250, 220, 40, 30);
        JRadioButton type2 = new JRadioButton("2");
        type2.setBounds(290, 220, 40, 30);
        JRadioButton type3 = new JRadioButton("3", true);
        type3.setBounds(330, 220, 40, 30);
        buttonGroup.add(type1);
        buttonGroup.add(type2);
        buttonGroup.add(type3);
        add(type1);
        add(type2);
        add(type3);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        startButton.setBounds(150, 270, 80, 30);
        add(startButton);

        JButton resumeButton = new JButton(StringText.LOAD_GAME);
        resumeButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        resumeButton.setBounds(120, 310, 150, 30);
        add(resumeButton);

        startButton.addActionListener(arg0 -> {
            if (isGameSettingValid(rowText, columnText, timerText, buttonGroup)) {
                jFrame.dispose();
                new GameController().initGameView();
            }
        });

        resumeButton.addActionListener(value -> {

            LoadGame loadGame = new LoadGame();
            loadGame.loadGame();
            if (loadGame.isSaveFileExist()) {
                jFrame.dispose();

                GameModel gameModel = loadGame.getGameModel();
                BoardConfig.BOARD_ROWS = loadGame.getRowValue();
                BoardConfig.BOARD_COLUMNS = loadGame.getColumnValue();
                BoardConfig.PIECE_NUMBER = loadGame.getPieceNum();
                BoardConfig.TURN_LIMIT = loadGame.getTurnLimit();

                new GameController().loadGame(gameModel);
            }

        });
    }

    private boolean isGameSettingValid(JTextField rowText, JTextField columnText, JTextField timerText, ButtonGroup buttonGroup) {
        int rowValue;
        int columnValue;
        int turnLimit;

        try {
            rowValue = Integer.parseInt(rowText.getText());
            columnValue = Integer.parseInt(columnText.getText());
            turnLimit = Integer.parseInt(timerText.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numbers only!");
            return false;
        }

        if (rowValue % 2 != 0 || rowValue < MIN_ROW || rowValue > MAX_ROW) {
            JOptionPane.showMessageDialog(null, "Please check the row number is " + ROW_MESSAGE);

            return false;
        }

        if (columnValue % 2 == 0 || columnValue < MIN_COLUMN || columnValue > MAX_COLUMN) {
            JOptionPane.showMessageDialog(null, "Please check the column number is " + COLUMN_MESSAGE);
            return false;
        }

        if (turnLimit <= 0 || turnLimit > TURN_LIMIT) {
            JOptionPane.showMessageDialog(null, "The turn time limit should be greater than 0 and lower than " + TURN_LIMIT);
            return false;
        }

        int pieceNum = 0;
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                pieceNum = Integer.parseInt(button.getText());
            }
        }

        BoardConfig.BOARD_ROWS = rowValue;
        BoardConfig.BOARD_COLUMNS = columnValue;
        BoardConfig.PIECE_NUMBER = pieceNum;
        BoardConfig.TURN_LIMIT = turnLimit;

        return true;
    }

}
