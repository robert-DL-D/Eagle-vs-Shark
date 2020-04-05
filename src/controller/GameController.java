package controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.Eagle;
import model.EagleBlue;
import model.EagleGreen;
import model.EagleRed;
import model.Flag;
import model.GameModel;
import model.Movable;
import model.Piece;
import model.Player;
import model.Shark;
import model.SharkBlue;
import model.SharkGreen;
import model.SharkRed;
import model.Square;
import model.Type;
import view.GameView;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;
    private Player player;

    public GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.setGameController(this);
        gameView.getBoardView().setBoardSize(GameModel.getROW(), GameModel.getCOLUMN());

        gameView.getBoardView().setSquares(gameModel.getSquares());

        randomStartingPlayer();
        setCurrentPlayer();

        autoAddPieces();

        gameView.setSharkList(gameModel.getSharkPlayer().getPieceList());
        gameView.setEagleList(gameModel.getEaglePlayer().getPieceList());
        gameView.setFlagList(gameModel.getFlagList());
        gameView.getTurnPanel().updateTurnText();
        gameView.getTurnPanel().setButtonText();
        gameView.getAbilityPanel().updatePieceJList();
        gameView.getTimePanel().setGameController(this);

    }

    private void autoAddPieces() {

        addEagle(38, Type.RED);
        addEagle(41, Type.RED);
        addEagle(44, Type.GREEN);
        addEagle(77, Type.GREEN);
        addEagle(4, Type.BLUE);
        addEagle(21, Type.BLUE);

        addShark(47, Type.RED);
        addShark(50, Type.RED);
        addShark(53, Type.GREEN);
        addShark(14, Type.GREEN);
        addShark(85, Type.BLUE);
        addShark(61, Type.BLUE);

        addFlag(5, gameModel.getEaglePlayer(), Type.FLAG);
        addFlag(86, gameModel.getSharkPlayer(), Type.FLAG);
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

    private void addShark(int position, Enum type) {

        if (type == Type.RED) {
            SharkRed shark = new SharkRed(position, Type.RED);
            gameModel.getSharkPlayer().addPiece(shark);

            Square square = gameModel.getSquares()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        } else if (type == Type.GREEN) {
            SharkGreen shark = new SharkGreen(position, Type.GREEN);
            gameModel.getSharkPlayer().addPiece(shark);

            Square square = gameModel.getSquares()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        } else if (type == Type.BLUE) {
            SharkBlue shark = new SharkBlue(position, Type.BLUE);
            gameModel.getSharkPlayer().addPiece(shark);

            Square square = gameModel.getSquares()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        }
    }

    private void addEagle(int position, Enum type) {

        if (type == Type.RED) {
            EagleRed eagle = new EagleRed(position, Type.RED);
            gameModel.getEaglePlayer().addPiece(eagle);

            Square square = gameModel.getSquares()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        } else if (type == Type.GREEN) {
            EagleGreen eagle = new EagleGreen(position, Type.GREEN);
            gameModel.getEaglePlayer().addPiece(eagle);

            Square square = gameModel.getSquares()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        } else if (type == Type.BLUE) {
            Eagle eagle = new EagleBlue(position, Type.BLUE);
            gameModel.getEaglePlayer().addPiece(eagle);

            Square square = gameModel.getSquares()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        }

    }

    private void addFlag(int position, Player owner, Enum type) {

        Flag flag = new Flag(position, owner, type);

        gameModel.getFlagList().add(flag);

        Square square = gameModel.getSquares()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1) {

            Player<Eagle> eaglePlayer = gameModel.getEaglePlayer();
            Player<Shark> sharkPlayer = gameModel.getSharkPlayer();
            boolean moved;

            if (gameModel.isEagleTurn()) {
                moved = eaglePlayer.getPiece(index).moveDirection(gameModel, gameModel.getSquares(), movementCoord);
            } else {
                moved = sharkPlayer.getPiece(index).moveDirection(gameModel, gameModel.getSquares(), movementCoord);
            }

            if (moved) {
                gameView.repaint();

                gameView.setEagleList(eaglePlayer.getPieceList());
                gameView.setSharkList(sharkPlayer.getPieceList());
                gameView.getTurnPanel().setEnabledButton(false);
                gameView.getTurnPanel().updateTurnText();
                gameView.getTurnPanel().setButtonText();

                victoryCondition();
            }
        }
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

    public void nextTurn() {

        if (gameModel.isEagleTurn()) {
            gameModel.setIsEagleTurn(false);
        } else {
            gameModel.setIsEagleTurn(true);
        }

        setCurrentPlayer();

        gameView.getTurnPanel().updateTurnText();
        gameView.getTurnPanel().setButtonText();
        gameView.getTurnPanel().setEnabledButton(true);
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

    public Movable getEaglePiece(int selectedPieceIndex) {

        return gameModel.getEaglePlayer().getPiece(selectedPieceIndex);

    }

    public Movable getSharkPiece(int selectedPieceIndex) {

        return gameModel.getSharkPlayer().getPiece(selectedPieceIndex);

    }
}
