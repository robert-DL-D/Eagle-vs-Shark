package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.MovablePiece;

class SaveLoadGame implements Serializable {

    transient MovablePiece movablePiece;

    public void saveGame(MovablePiece movablePiece) throws IOException {
        // Create 2 streams to serialize the object and save it to a file
        FileOutputStream outputStream = new FileOutputStream("src/save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        this.movablePiece = movablePiece;

        // Save the game to a file
        objectOutputStream.writeObject(this.movablePiece);

        // Close the stream and release resources
        objectOutputStream.close();
    }

    public void loadGame() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("src/save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        System.out.println(objectInputStream.readObject());
    }
}
