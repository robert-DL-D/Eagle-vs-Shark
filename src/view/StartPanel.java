package view;

import java.awt.Font;

import javax.swing.*;

import controller.GameController;
import file.LoadGame;
import model.BoardConfig;
import model.GameModel;

public class StartPanel extends JPanel {

    StartPanel(JFrame jFrame) {

        setLayout(null);

        JLabel label = new JLabel("Game setting");
        label.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        label.setBounds(150, 50, 200, 20);
        add(label);

        JLabel rowLabel = new JLabel("<html><body>" + "Board Rows:" + "<br>" + "(even number not less than 8)" + "<body></html>");
        rowLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowLabel.setBounds(50, 100, 200, 30);
        add(rowLabel);

        JTextField rowText = new JTextField();
        rowText.setText("10");
        rowText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        rowText.setBounds(250, 100, 100, 30);
        add(rowText);

        JLabel columnLabel = new JLabel("<html><body>" + "Board Columns:" + "<br>" + "(odd number not less than 5)" + "<body></html>");
        columnLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnLabel.setBounds(50, 150, 200, 30);
        add(columnLabel);

        JTextField columnText = new JTextField();
        columnText.setText("9");
        columnText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        columnText.setBounds(250, 150, 100, 30);
        add(columnText);

        JLabel timerLabel = new JLabel("Turn Time (sec):");
        timerLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        timerLabel.setBounds(50, 200, 200, 30);
        add(timerLabel);

        JTextField timerText = new JTextField();
        timerText.setText("30");
        timerText.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        timerText.setBounds(250, 200, 100, 30);
        add(timerText);

        JLabel pieceNumLabel = new JLabel("Number of pieces of each type:");
        pieceNumLabel.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        pieceNumLabel.setBounds(50, 250, 200, 30);
        add(pieceNumLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton type1 = new JRadioButton("1", true);
        type1.setBounds(250, 250, 40, 30);
        JRadioButton type2 = new JRadioButton("2");
        type2.setBounds(300, 250, 40, 30);
        buttonGroup.add(type1);
        buttonGroup.add(type2);
        add(type1);
        add(type2);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        startButton.setBounds(80, 300, 80, 30);
        add(startButton);

        JButton resumeButton = new JButton("Resume");
        resumeButton.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        resumeButton.setBounds(240, 300, 80, 30);
        add(resumeButton);

        startButton.addActionListener(arg0 -> {
            if (validationAndSetValue(jFrame, rowText, columnText, timerText, type1, type2)) {
                jFrame.dispose();
                new GameController();
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

                GameController gameController = new GameController();
                GameView gameView = gameController.getGAME_VIEW();

                gameView.initializeGameView(gameModel.getSQUARE_ARRAY(),
                        gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                        gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                        gameModel.getFLAG_LIST(),
                        gameModel.getISLAND_LIST(),
                        gameModel.getAllyPieceList(),
                        gameModel.isEagleTurn());

                gameView.loadGame(gameModel.getCurrentPlayer(), Integer.valueOf(BoardConfig.TURN_LIMIT));
            }

        });
    }

    private boolean validationAndSetValue(JFrame jFrame, JTextField rowText, JTextField columnText, JTextField timerText, JRadioButton type1, JRadioButton type2) {
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

        if (rowValue % 2 != 0 || rowValue < 8) {
            JOptionPane.showMessageDialog(null, "Please check the row number!Even number not less than 8!");
            return false;
        }

        if (columnValue % 2 == 0 || columnValue < 5) {
            JOptionPane.showMessageDialog(null, "Please check the column number!Odd number not less than 5!");
            return false;
        }

        if (turnLimit <= 0) {
            JOptionPane.showMessageDialog(null, "The number of turn time should be greater than 0!");
            return false;
        }

        // default value : 1
        int pieceNum = 1;
        if (type1.isSelected()) {
            pieceNum = 1;
        } else if (type2.isSelected()) {
            pieceNum = 2;
        }

        BoardConfig.BOARD_ROWS = rowValue;
        BoardConfig.BOARD_COLUMNS = columnValue;
        BoardConfig.PIECE_NUMBER = pieceNum;
        BoardConfig.TURN_LIMIT = String.valueOf(turnLimit);
        return true;
    }

}
