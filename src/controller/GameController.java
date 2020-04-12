package controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.Eagle;
import model.EagleBlue;
import model.EagleGreen;
import model.EagleRed;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
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

    public GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.setGameController(this);

        gameView.getBOARDVIEW().setSquares(gameModel.getSQUARE_ARRAY());

        randomStartingPlayer();
        setCurrentPlayer();

        autoAddPieces();

        gameView.setSharkList(gameModel.getSHARK_PLAYER().getPIECE_LIST());
        gameView.setEagleList(gameModel.getEAGLE_PLAYER().getPIECE_LIST());
        gameView.setFlagList(gameModel.getFLAG_LIST());
        gameView.getTURN_PANEL().updateTurnText();
        gameView.getTURN_PANEL().setButtonText();
        gameView.getABILITY_PANEL().updatePieceJList();
        gameView.getTIME_PANEL().setGameController(this);
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

        addFlag(5, gameModel.getEAGLE_PLAYER(), Type.FLAG);
        addFlag(86, gameModel.getSHARK_PLAYER(), Type.FLAG);

    }

    private void randomStartingPlayer() {

        if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
            gameModel.setIsEagleTurn(true);
        } else {
            gameModel.setIsEagleTurn(false);

        }

    }

    private void setCurrentPlayer() {
        gameView.getTURN_PANEL().setIsEaglePlayer(gameModel.isEagleTurn());
        gameView.getABILITY_PANEL().setIsEaglePlayer(gameModel.isEagleTurn());

    }

    private void addShark(int position, Enum type) {

        if (type == Type.RED) {
            SharkRed shark = new SharkRed(position, Type.RED);
            gameModel.getSHARK_PLAYER().addPiece(shark);

            Square square = gameModel.getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        } else if (type == Type.GREEN) {
            SharkGreen shark = new SharkGreen(position, Type.GREEN);
            gameModel.getSHARK_PLAYER().addPiece(shark);

            Square square = gameModel.getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        } else if (type == Type.BLUE) {
            SharkBlue shark = new SharkBlue(position, Type.BLUE);
            gameModel.getSHARK_PLAYER().addPiece(shark);

            Square square = gameModel.getSQUARE_ARRAY()[shark.getRow()][shark.getColumn()];
            square.addPiece(shark);
        }
    }

    private void addEagle(int position, Enum type) {

        if (type == Type.RED) {
            EagleRed eagle = new EagleRed(position, Type.RED);
            gameModel.getEAGLE_PLAYER().addPiece(eagle);

            Square square = gameModel.getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        } else if (type == Type.GREEN) {
            EagleGreen eagle = new EagleGreen(position, Type.GREEN);
            gameModel.getEAGLE_PLAYER().addPiece(eagle);

            Square square = gameModel.getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        } else if (type == Type.BLUE) {
            Eagle eagle = new EagleBlue(position, Type.BLUE);
            gameModel.getEAGLE_PLAYER().addPiece(eagle);

            Square square = gameModel.getSQUARE_ARRAY()[eagle.getRow()][eagle.getColumn()];
            square.addPiece(eagle);
        }

    }

    private void addFlag(int position, Player owner, Enum type) {

        Flag flag = new Flag(position, owner, type);

        gameModel.getFLAG_LIST().add(flag);

        Square square = gameModel.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];
        square.addPiece(flag);
    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1) {

            Player<Eagle> eaglePlayer = gameModel.getEAGLE_PLAYER();
            Player<Shark> sharkPlayer = gameModel.getSHARK_PLAYER();
            boolean moved;

            if (gameModel.isEagleTurn()) {
                moved = eaglePlayer.getPiece(index).moveDirection(gameModel, gameModel.getSQUARE_ARRAY(), movementCoord);
            } else {
                moved = sharkPlayer.getPiece(index).moveDirection(gameModel, gameModel.getSQUARE_ARRAY(), movementCoord);
            }

            if (moved) {
                gameView.repaint();

                gameView.setEagleList(eaglePlayer.getPIECE_LIST());
                gameView.setSharkList(sharkPlayer.getPIECE_LIST());
                gameView.getTURN_PANEL().setEnabledButton(false);
                gameView.getTURN_PANEL().updateTurnText();
                gameView.getTURN_PANEL().setButtonText();

                checkVictoryCondition();
            }
        }
    }

    public void useAbility(int index, String actionCommand) {

        switch (actionCommand) {
            case "Stun":
                stunPiece(index);
                break;
        }

    }

    public void stunPiece(int index) {

        if (gameModel.isEagleTurn()) {
            gameModel.getSHARK_PLAYER().getPIECE_LIST().get(index).setStunned(true);
        } else {
            gameModel.getEAGLE_PLAYER().getPIECE_LIST().get(index).setStunned(true);
        }

        for (int i = 0; i < gameModel.getSHARK_PLAYER().getPIECE_LIST().size(); i++) {
            if (gameModel.getSHARK_PLAYER().getPIECE_LIST().get(i).isStunned()) {
                System.out.println("Shark " + i + " is stunned");
            }
        }
    }

    public void updateNextTurn() {

        if (gameModel.isEagleTurn()) {
            gameModel.setIsEagleTurn(false);
        } else {
            gameModel.setIsEagleTurn(true);
        }

        setCurrentPlayer();

        gameView.getTURN_PANEL().updateTurnText();
        gameView.getTURN_PANEL().setButtonText();
        gameView.getTURN_PANEL().setEnabledButton(true);
        gameView.getMOVEMENT_PANEL().getMOVE_JLIST().setVisible(false);
        gameView.getABILITY_PANEL().updatePieceJList();
        gameView.getTIME_PANEL().resetTimer();

    }

    private void checkVictoryCondition() {

        for (Flag flag : gameModel.getFLAG_LIST()) {

            List<Piece> pieceList = gameModel.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()].getPIECE_LIST();

            if (pieceList.size() == 2) {
                if (pieceList.get(1) instanceof Eagle) {
                    System.out.println("Eagle Won");
                } else {
                    System.out.println("Shark Won");
                }
            }
        }
    }

    public MovablePiece getEaglePiece(int selectedPieceIndex) {

        return gameModel.getEAGLE_PLAYER().getPiece(selectedPieceIndex);

    }

    public MovablePiece getSharkPiece(int selectedPieceIndex) {

        return gameModel.getSHARK_PLAYER().getPiece(selectedPieceIndex);

    }
}
