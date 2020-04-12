package controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.Eagle;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
import model.Piece;
import model.Player;
import model.Shark;
import view.GameView;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;

    public GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        chooseRandomStartingPlayer();
        setCurrentPlayer();

        gameView.setGameController(this);
        gameView.getBOARDVIEW().setSquares(gameModel.getSQUARE_ARRAY());
        gameView.setSharkList(gameModel.getSHARK_PLAYER().getPIECE_LIST());
        gameView.setEagleList(gameModel.getEAGLE_PLAYER().getPIECE_LIST());
        gameView.setFlagList(gameModel.getFLAG_LIST());
        gameView.getTURN_PANEL().updateTurnText();
        gameView.getTURN_PANEL().setButtonText();
        //gameView.getABILITY_PANEL().updatePieceJList();
        gameView.getABILITY_PANEL().setButtonText();
        gameView.getTIME_PANEL().setGameController(this);
    }

    private void chooseRandomStartingPlayer() {

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
                gameView.getTURN_PANEL().disableAllPieceButton();
                gameView.getTURN_PANEL().updateTurnText();
                gameView.getTURN_PANEL().setButtonText();

                checkVictoryCondition();
            }
        }
    }

    public void useAbility(int index, String actionCommand) {

        /*switch (actionCommand) {
            case "Stun":*/
        stunPiece(index);
        //break;
        //}

    }

    public void stunPiece(int index) {

        if (gameModel.isEagleTurn()) {
            List<Shark> piece_list = gameModel.getSHARK_PLAYER().getPIECE_LIST();

            piece_list.get(index).setStunned(true);

            int size = piece_list.size();
            for (int i = 0; i < size; i++) {
                if (piece_list.get(i).isStunned()) {
                    System.out.println("Shark " + (i + 1) + " is stunned");
                }
            }
        } else {
            List<Eagle> piece_list = gameModel.getEAGLE_PLAYER().getPIECE_LIST();

            piece_list.get(index).setStunned(true);

            int size = piece_list.size();
            for (int i = 0; i < size; i++) {
                if (piece_list.get(i).isStunned()) {
                    System.out.println("Eagle " + (i + 1) + " is stunned");
                }
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
        gameView.getTURN_PANEL().setEnabledButton();
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
