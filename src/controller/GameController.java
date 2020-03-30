package controller;

import model.Eagle;
import model.Flag;
import model.GameModel;
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

        gameModel.setIsEagleTurn(true);
        setCurrentPlayer();

        addEagle(38);
        addEagle(41);
        addEagle(44);
        addEagle(77);
        addEagle(4);

        addShark(47);
        addShark(50);
        addShark(53);
        addShark(14);
        addShark(85);

        addFlag(5, gameModel.getEaglePlayer());
        addFlag(86, gameModel.getSharkPlayer());

        gameView.setSharkList(gameModel.getSharkPlayer().getPieceList());
        gameView.setEagleList(gameModel.getEaglePlayer().getPieceList());
        gameView.setFlagList(gameModel.getFlagList());
        gameView.getTurnPanel().updatePieceJList();

        //printArray();
    }

    private void initSquare() {

        int increment = 1;

        int row = GameModel.getROW();
        int column = GameModel.getCOLUMN();
        Square[][] squares = gameModel.getSquares();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //System.out.print(increment + " ");

                squares[i][j] = new Square(increment);
                increment++;
            }
            // System.out.print("\n");
        }
    }

    private void setCurrentPlayer() {
        gameView.getTurnPanel().setIsEaglePlayer(gameModel.isEagleTurn());

    }

    private void addShark(int position) {

        Shark shark = new Shark(position);
        gameModel.getSharkPlayer().addPiece(shark);

        Square square = gameModel.getSquares()[shark.getRow()][shark.getColumn()];
        square.addPiece(shark);

    }

    private void addEagle(int position) {

        Eagle eagle = new Eagle(position);
        gameModel.getEaglePlayer().addPiece(eagle);

        Square square = gameModel.getSquares()[eagle.getRow()][eagle.getColumn()];
        square.addPiece(eagle);

    }

    private void addFlag(int position, Player owner) {

        Flag flag = new Flag(position, owner);

        gameModel.getFlagList().add(flag);

        Square square = gameModel.getSquares()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    public void movePiece(int index, String direction) {

        boolean moved;

        if (index != -1) {

            Player<Eagle> eaglePlayer = gameModel.getEaglePlayer();
            Player<Shark> sharkPlayer = gameModel.getSharkPlayer();

            if (gameModel.isEagleTurn()) {
                moved = eaglePlayer.getPiece(index).moveDirection(gameModel, gameModel.getSquares(), direction);
            } else {
                moved = sharkPlayer.getPiece(index).moveDirection(gameModel, gameModel.getSquares(), direction);
            }

            if (moved) {

                gameView.setEagleList(eaglePlayer.getPieceList());
                gameView.setSharkList(sharkPlayer.getPieceList());
                gameView.getTurnPanel().setButtonStatus(false);
                gameView.getTurnPanel().updatePieceJList();
            }
        }

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

        victoryCondition();

        if (gameModel.isEagleTurn()) {
            gameModel.setIsEagleTurn(false);
        } else {
            gameModel.setIsEagleTurn(true);
        }

        setCurrentPlayer();

        gameView.getTurnPanel().updatePieceJList();
        gameView.getTurnPanel().setButtonStatus(true);

    }

    private void victoryCondition() {

        for (Square[] squareArray : gameModel.getSquares()) {
            for (Square square : squareArray) {
                if (square.getPieceList().size() == 2) {
                    if (square.getPieceList().get(1) instanceof Eagle) {
                        System.out.println("Eagle Won");
                    } else {
                        System.out.println("Shark Won");
                    }
                }
            }
        }
    }

}
