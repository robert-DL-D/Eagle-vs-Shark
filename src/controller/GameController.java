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

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        gameView.setCurrentPlayer(gameModel.isEagleTurn());
        gameView.initializeGameView(this, gameModel);
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
                gameView.updateViewAfterPieceMove(eaglePlayer, sharkPlayer);

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

            for (int i = 0; i < piece_list.size(); i++) {
                if (piece_list.get(i).isStunned()) {
                    System.out.println("Shark " + (i + 1) + " is stunned");
                }
            }
        } else {
            List<Eagle> piece_list = gameModel.getEAGLE_PLAYER().getPIECE_LIST();

            piece_list.get(index).setStunned(true);

            for (int i = 0; i < piece_list.size(); i++) {
                if (piece_list.get(i).isStunned()) {
                    System.out.println("Eagle " + (i + 1) + " is stunned");
                }
            }
        }
    }

    public void updateNextTurn() {
        gameModel.changePlayerTurn();
        gameModel.updatePieceStatus();
        gameView.setCurrentPlayer(gameModel.isEagleTurn());
        gameView.updateNextTurn();
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
