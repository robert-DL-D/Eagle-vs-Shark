package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.GameModel;

public class LoadGame extends FileGame implements Serializable {

    private boolean saveFileExist;
    private GameModel gameModel;
    private int rowValue;
    private int columnValue;
    private int pieceNum;
    private int turnLimit;
    private int turnTime;

    public void loadGame() {

        if (file.exists()) {
            try {
                saveFileExist = true;
                ObjectInput objectInputStream = new ObjectInputStream(new FileInputStream(file));
                gameModel = (GameModel) objectInputStream.readObject();
                rowValue = (int) objectInputStream.readObject();
                columnValue = (int) objectInputStream.readObject();
                pieceNum = (int) objectInputStream.readObject();
                turnLimit = (int) objectInputStream.readObject();
                turnTime = (int) objectInputStream.readObject();
                objectInputStream.close();

                JOptionPane.showMessageDialog(new JFrame(), "Game Loaded",
                        "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.toString(),
                        "IOException", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Save File not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isSaveFileExist() {
        return saveFileExist;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public int getRowValue() {
        return rowValue;
    }

    public int getColumnValue() {
        return columnValue;
    }

    public int getPieceNum() {
        return pieceNum;
    }

    public int getTurnLimit() {
        return turnLimit;
    }

    public int getTurnTime() {
        return turnTime;
    }
}
