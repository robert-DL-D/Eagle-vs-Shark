package file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

import model.GameModel;

public class LoadGame
        extends FileGame
        implements Serializable {

    private boolean saveFileExist;

    public GameModel loadGame() {
        GameModel gameModel = null;
        if (file.exists()) {
            try {
                saveFileExist = true;
                ObjectInput objectInputStream = new ObjectInputStream(new FileInputStream(file));
                gameModel = (GameModel) objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
            }
        }
        return gameModel;
    }

    public boolean isSaveFileExist() {
        return saveFileExist;
    }
}
