package controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

        gameView.getBoardView().setSquares(gameModel.getSquares());

        randomStartingPlayer();
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
        gameView.getAbilityPanel().updatePieceJList();
        gameView.getTimePanel().setGameController(this);

        //printArray();
    }

    private void randomStartingPlayer() {

        if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
            gameModel.setIsEagleTurn(true);
        } else {
            gameModel.setIsEagleTurn(false);

        }

    }

    private void setCurrentPlayer() {
        gameView.getTurnPanel().setIsEaglePlayer(gameModel.isEagleTurn());
        gameView.getAbilityPanel().setIsEaglePlayer(gameModel.isEagleTurn());

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

    public void useAbility(int index, String actionCommand) {

        switch (actionCommand) {
            case "Stun":
                stun(index);
                break;
        }

    }

    public void stun(int index) {

        if (gameModel.isEagleTurn()) {
            gameModel.getSharkPlayer().getPieceList().get(index).setStunned(true);
        } else {
            gameModel.getEaglePlayer().getPieceList().get(index).setStunned(true);
        }

        for (int i = 0; i < gameModel.getSharkPlayer().getPieceList().size(); i++) {
            if (gameModel.getSharkPlayer().getPieceList().get(i).isStunned()) {
                System.out.println("Shark " + i + " is stunned");
            }
        }
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
        gameView.getAbilityPanel().updatePieceJList();
        gameView.getTimePanel().resetTimer();

    }

    private void victoryCondition() {

        for (Flag flag : gameModel.getFlagList()) {

            List<Piece> pieceList = gameModel.getSquares()[flag.getRow()][flag.getColumn()].getPieceList();

            if (pieceList.size() == 2) {
                if (pieceList.get(1) instanceof Eagle) {
                    System.out.println("Eagle Won");
                } else {
                    System.out.println("Shark Won");
                }
            }
        }
    }

}
