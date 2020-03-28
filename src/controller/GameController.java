package controller;

import model.Eagle;
import model.Flag;
import model.GameModel;
import model.Piece;
import model.Player;
import model.Shark;
import model.Square;
import view.GameView;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;

    public GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.setGameController(this);
        gameView.getBoardView().setBoardSize(GameModel.getROW(), GameModel.getCOLUMN());

        initSquare();
        gameView.getBoardView().setSquares(gameModel.getSquares());

        initPlayers();
        setCurrentPlayer();

        autoAddEagle(38);
        autoAddEagle(41);
        autoAddEagle(44);
        autoAddEagle(77);
        autoAddEagle(4);

        autoAddShark(47);
        autoAddShark(50);
        autoAddShark(53);
        autoAddShark(14);
        autoAddShark(85);

        autoAddEagleFlag(5);
        autoAddSharkFlag(86);

        gameView.setSharkList(gameModel.getSharkPlayer().getPieceList());
        gameView.setEagleList(gameModel.getEaglePlayer().getPieceList());
        gameView.setFlagList(gameModel.getFlagList());
        gameView.getTurnPanel().updatePieceJList();

        //printArray();
    }

    private void setCurrentPlayer() {
        gameView.getTurnPanel().setCurrentPlayer(gameModel.isEaglePlayerTurn());

    }

    private void initSquare() {

        int increment = 1;

        for (int i = 0; i < GameModel.getROW(); i++) {
            for (int j = 0; j < GameModel.getCOLUMN(); j++) {
                //System.out.print(increment + " ");

                gameModel.getSquares()[i][j] = new Square(increment, i, j);
                increment++;

            }

            // System.out.print("\n");
        }
    }

    public void initPlayers() {
        gameModel.setEaglePlayerTurn(new Player<>());
        gameModel.setSharkPlayer(new Player<>());
        gameModel.setIsEaglePlayerTurn(true);

    }

    public void autoAddShark(int position) {

        Shark shark = new Shark(position);
        gameModel.getSharkPlayer().addPiece(shark);

        Square square = gameModel.getSquares()[shark.getRow()][shark.getColumn()];
        square.addPiece(shark);

    }

    public void autoAddEagle(int position) {

        Eagle eagle = new Eagle(position);
        gameModel.getEaglePlayer().addPiece(eagle);

        Square square = gameModel.getSquares()[eagle.getRow()][eagle.getColumn()];
        square.addPiece(eagle);

    }

    public void autoAddEagleFlag(int position) {

        Flag flag = new Flag(position);
        gameModel.getFlagList().add(flag);

        gameModel.getEaglePlayer().setFlag(flag);

        Square square = gameModel.getSquares()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    public void autoAddSharkFlag(int position) {

        Flag flag = new Flag(position);
        gameModel.getFlagList().add(flag);

        gameModel.getSharkPlayer().setFlag(flag);

        Square square = gameModel.getSquares()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    /*public void addShark() {

        Shark shark = new Shark(71);

        gameModel.getSharkPlayer().addPiece(shark);

        Square square = gameModel.getSquares()[shark.getRow()][shark.getColumn()];
        square.addPiece(shark);

    }*/

    public void movePiece(int index, int steps) {

        if (gameModel.isEaglePlayerTurn()) {

            gameModel.getEaglePlayer().getPiece(index).moveDirection(gameModel, gameModel.getSquares(), steps, index);

        } else {

            gameModel.getSharkPlayer().getPiece(index).moveDirection(gameModel, gameModel.getSquares(), steps, index);
        }

        gameView.setEagleList(gameModel.getEaglePlayer().getPieceList());
        gameView.setSharkList(gameModel.getSharkPlayer().getPieceList());

        gameView.getTurnPanel().updatePieceJList();

        //printArray();

    }

    private void printArray() {
        for (int i = 0; i < GameModel.getROW(); i++) {
            for (int j = 0; j < GameModel.getCOLUMN(); j++) {
                if (gameModel.getSquares()[i][j].getPiece() == null) {
                    System.out.print("0");
                } else {
                    /*System.out.println("i: "+i+" j: "+j
                            +" x: "+gameModel.getSquares()[i][j].getPiece().getX()
                            +" y: "+gameModel.getSquares()[i][j].getPiece().getY());*/
                    System.out.print("1");
                }
            }
            System.out.print("\n");
        }
    }

    public void nextTurn() {

        for (Square[] square : gameModel.getSquares()) {
            for (Square square1 : square) {
                if (square1.getPieceList().size() == 2) {
                    for (Piece piece : square1.getPieceList()) {
                        if (piece instanceof Eagle) {
                            System.out.println("Eagle Won");
                        }
                    }
                }
            }
        }

        if (gameModel.isEaglePlayerTurn()) {
            gameModel.setIsEaglePlayerTurn(false);
        } else {
            gameModel.setIsEaglePlayerTurn(true);
        }

        setCurrentPlayer();

        gameView.getTurnPanel().updatePieceJList();

    }

}
