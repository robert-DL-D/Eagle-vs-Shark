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
//import view.TemplateFrame;

public class GameController {

    private final GameView GAME_VIEW;
    private final GameModel GAME_MODEL;

    public GameController(GameModel GAME_MODEL, GameView GAME_VIEW) {
        this.GAME_MODEL = GAME_MODEL;
        this.GAME_VIEW = GAME_VIEW;
        GAME_VIEW.setCurrentPlayer(GAME_MODEL.isEagleTurn());
        GAME_VIEW.initializeGameView(this, GAME_MODEL);

        /*SaveLoadGame saveLoadGame = new SaveLoadGame();

        try {
            saveLoadGame.saveGame(GAME_MODEL.getEAGLE_PLAYER().getMovablePiece(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            saveLoadGame.loadGame();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void movePiece(int index, int[] movementCoord) {

        // -1 index means nothing is selected
        if (index != -1 && movementCoord != null) {

            boolean moved;
            Player<Eagle> eaglePlayer = GAME_MODEL.getEAGLE_PLAYER();
            Player<Shark> sharkPlayer = GAME_MODEL.getSHARK_PLAYER();
            Player<? extends MovablePiece> player = GAME_MODEL.isEagleTurn() ? eaglePlayer : sharkPlayer;

            // TODO simplify GAME_MODEL to player
            moved = player.getMovablePiece(index).updatePieceRowColumn(GAME_MODEL, GAME_MODEL.getSQUARE_ARRAY(), movementCoord);

            if (moved) {
                GAME_VIEW.updateViewAfterPieceMove(eaglePlayer, sharkPlayer);

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

            GAME_VIEW.setAfterUseText(movablePiece);

        }
    }

    private MovablePiece stunPiece(int index) {

        List<? extends MovablePiece> movablePieceList = GAME_MODEL.isEagleTurn() ? GAME_MODEL.getSHARK_PLAYER().getMOVABLEPIECE_LIST() : GAME_MODEL.getEAGLE_PLAYER().getMOVABLEPIECE_LIST();

        MovablePiece movablePiece = movablePieceList.get(index);
        movablePiece.setStunned(true);

        return movablePiece;
    }

    public void updateNextTurn() {

        // click the 'next turn' button to test the end panel
        // could be removed when test is finished
       /* GAME_VIEW.dispose();
        new TemplateFrame().showEndView("Eagle");*/

        GAME_MODEL.changePlayerTurn();
        GAME_MODEL.updatePieceStatus();
        GAME_VIEW.setCurrentPlayer(GAME_MODEL.isEagleTurn());
        GAME_VIEW.updateNextTurn(GAME_MODEL.isEagleTurn());
    }

    private void checkVictoryCondition() {

        for (Flag flag : GAME_MODEL.getFLAG_LIST()) {

            Square flagSquare = GAME_MODEL.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];

            if (flagSquare.getMovablePiece() != null) {

                MovablePiece movablepiece = flagSquare.getMovablePiece();

                if (movablepiece instanceof Eagle) {
                    System.out.println("Eagle Won");
                } else {
                    System.out.println("Shark Won");
                }
            }
        }
    }

    public MovablePiece getEaglePiece(int selectedPieceIndex) {

        return GAME_MODEL.getEAGLE_PLAYER().getMovablePiece(selectedPieceIndex);

    }

    public MovablePiece getSharkPiece(int selectedPieceIndex) {

        return GAME_MODEL.getSHARK_PLAYER().getMovablePiece(selectedPieceIndex);

    }
}
