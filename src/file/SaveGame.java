package file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.GameModel;

public class SaveGame
        extends FileGame
        implements Serializable {

    public void saveGame(GameModel gameModel) {
        try {
            ObjectOutput objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(gameModel);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
