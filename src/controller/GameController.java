package controller;

import java.util.List;

import model.Eagle;
import model.Flag;
import model.GameModel;
import model.MovablePiece;
import model.Player;
import model.Shark;
import model.Square;
import view.GameView;
import view.TemplateFrame;

public class GameController {

    private GameView gameView;
    private GameModel gameModel;

    public GameController(){
    }

    public void startGame(GameModel gameModel, GameView gameView){
        this.gameModel = gameModel;
        this.gameView = gameView;
        gameView.setCurrentPlayer(gameModel.isEagleTurn());
        gameView.initializeGameView(this, gameModel);
    }


    public void showStartView() {
        TemplateFrame templateFrame = new TemplateFrame();
        templateFrame.showStartView(this);
    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1 && movementCoord != null) {

            boolean moved;
            Player<Eagle> eaglePlayer = gameModel.getEAGLE_PLAYER();
            Player<Shark> sharkPlayer = gameModel.getSHARK_PLAYER();
            Player<? extends MovablePiece> player = gameModel.isEagleTurn() ? eaglePlayer : sharkPlayer;

            moved = player.getPiece(index).updatePieceRowColumn(gameModel, gameModel.getSQUARE_ARRAY(), movementCoord);

            if (moved) {
                gameView.updateViewAfterPieceMove(eaglePlayer, sharkPlayer);

                checkVictoryCondition();
            }
        }
    }

    public void useAbility(int index, String actionCommand) {

        if (index != -1) {

            MovablePiece movablePiece;

        /*switch (actionCommand) {
            case "Stun":*/
            movablePiece = stunPiece(index);
            //break;
            //}

            gameView.setAfterUseText(movablePiece);

        }
    }

    private MovablePiece stunPiece(int index) {

        List<? extends MovablePiece> movablePieceList;

        if (gameModel.isEagleTurn()) {
            movablePieceList = gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST();
        } else {
            movablePieceList = gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST();
        }

        movablePieceList.get(index).setStunned(true);

        return movablePieceList.get(index);
    }

    public void updateNextTurn() {

        // click the 'next turn' button to test the end panel
        // could be removed when test is finished
        gameView.dispose();
        TemplateFrame frame = new TemplateFrame();
        frame.showEndView(this, "Eagle");

        // gameModel.changePlayerTurn();
        // gameModel.updatePieceStatus();
        // gameView.setCurrentPlayer(gameModel.isEagleTurn());
        // gameView.updateNextTurn();
    }

    private void checkVictoryCondition() {

        for (Flag flag : gameModel.getFLAG_LIST()) {

            Square flagSquare = gameModel.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];

            if (flagSquare.getMovablePiece() != null) {

                MovablePiece movablepiece = gameModel.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()].getMovablePiece();

                if (movablepiece instanceof Eagle) {
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
