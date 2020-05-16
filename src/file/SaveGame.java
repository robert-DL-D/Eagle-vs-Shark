package file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.GameModel;

public class SaveGame
        extends FileGame
        implements Serializable {

    public void saveGame(GameModel gameModel, int turnTime) {
        try {
            ObjectOutput objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(gameModel);
            objectOutputStream.writeObject(turnTime);
            objectOutputStream.close();

            JOptionPane.showMessageDialog(new JFrame(), "Game Saved",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.toString(),
                    "IOException", JOptionPane.ERROR_MESSAGE);
        }
    }

}
