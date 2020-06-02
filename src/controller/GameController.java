package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import model.BoardConfig;
import model.GameModel;
import model.MovablePiece;
import model.StringText;
import view.GameView;

public class GameController
        implements ActionListener, MouseListener {

    private GameModel gameModel = new GameModel();
    private final GameView GAME_VIEW = new GameView(this, this);
    private final AbilityController ABILITY_CONTROLLER = new AbilityController();
    private final GameStateController gameStateController = new GameStateController();

    public GameController() {
        GAME_VIEW.initializeGameView(gameModel.getSQUARE_ARRAY(),
                gameModel.getEAGLE_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getSHARK_PLAYER().getMOVABLEPIECE_LIST(),
                gameModel.getFLAG_LIST(),
                gameModel.getISLAND_LIST(),
                gameModel.getAllyPieceList(),
                gameModel.isEagleTurn());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();

        if (StringText.NEXT_TURN.equals(actionCommand)) {

            gameModel.updateNextTurn();
            GAME_VIEW.updateNextTurn(gameModel.getAllyPieceList(), gameModel.isEagleTurn());

        } else if (StringText.STUN.equals(actionCommand) || StringText.SLOW.equals(actionCommand)) {

            GAME_VIEW.selectedAbility(actionCommand, gameModel.getEnemyPieceList());

        } else if (StringText.SPEED.equals(actionCommand) || StringText.SHIELD.equals(actionCommand)) {

            GAME_VIEW.selectedAbility(actionCommand, gameModel.getAllyPieceList());

        } else if (StringText.CLEANSE.equals(actionCommand)) {

            GAME_VIEW.selectedAbility(actionCommand, gameModel.getStunnedPieceList());

        } else if (actionCommand.contains(StringText.USE)) {

            ABILITY_CONTROLLER.useAbility(GAME_VIEW.getPieceJListSelectedItem(), actionCommand, gameModel, GAME_VIEW);

        } else if (actionCommand.contains(StringText.MOVING_MODE) || actionCommand.contains(StringText.ABILITY_MODE)) {

            int selectedButtonIndex = GAME_VIEW.getSelectedButtonIndex();
            GAME_VIEW.togglePieceMode(selectedButtonIndex, gameModel.getAllyPieceList());
            gameModel.updateMovingMode(actionCommand, selectedButtonIndex);

        } else if (StringText.SAVE_GAME.equals(actionCommand)) {

            gameStateController.saveGame(gameModel, GAME_VIEW.getTurnTime());

        } else if (StringText.LOAD_GAME.equals(actionCommand)) {

            GameModel gameModel = gameStateController.loadGame(GAME_VIEW);
            if (gameModel != null) {
                this.gameModel = gameModel;
            }

        }
    }

    public GameView getGAME_VIEW() {
        return GAME_VIEW;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (!gameModel.getCurrentPlayer().isPieceMoved()) {

            MovablePiece selectedMovablePiece = GAME_VIEW.getSelectedMovablePiece();

            List<int[]> movablePieceCoord = GAME_VIEW.getMovablePieceCoord();

            for (int i = 0; i < movablePieceCoord.size(); i++) {
                int[] ints = movablePieceCoord.get(i);
                int picSize = GAME_VIEW.getPicSize();

                if ((ints[0] <= e.getY() && e.getY() <= ints[0] + picSize)
                        && (ints[1] <= e.getX() && e.getX() <= ints[1] + picSize)) {

                    MovablePiece movablePiece = GAME_VIEW.getMovablePieceList().get(i);

                    if (selectedMovablePiece == null) {
                        String movablePieceTeam = movablePiece.getClass().getSuperclass().getSimpleName();

                        if (gameModel.isEagleTurn() ? "Eagle".equals(movablePieceTeam) : "Shark".equals(movablePieceTeam)) {
                            GAME_VIEW.showValidSquares(movablePiece);
                            for (int[] movableCoord : movablePiece.getMovableCoords()) {

                                int[] validCoord = new int[2];
                                validCoord[0] = movablePiece.getRow() + movableCoord[0] + 1;
                                validCoord[1] = movablePiece.getColumn() + movableCoord[1] + 1;

                                if (!(validCoord[0] < 1 || validCoord[0] > BoardConfig.BOARD_ROWS
                                        || validCoord[1] < 1 || validCoord[1] > BoardConfig.BOARD_COLUMNS)) {
                                    GAME_VIEW.getMovableSquareCoord().add(movableCoord);
                                }

                            }

                            break;
                        }

                    } else {
                        if (selectedMovablePiece == movablePiece) {
                            GAME_VIEW.removeMovablePiece();
                            break;
                        }
                    }
                }
            }

            if (selectedMovablePiece != null && selectedMovablePiece.isMovingMode()
                    && !selectedMovablePiece.isStunned()) {
                int squareSize = GAME_VIEW.getSquareSize();

                for (int[] ints2 : GAME_VIEW.getMovableSquareCoord()) {

                    int row = GAME_VIEW.gridCoord(ints2[0] + selectedMovablePiece.getRow());
                    int column = GAME_VIEW.gridCoord(ints2[1] + selectedMovablePiece.getColumn());

                    if ((row <= e.getY() && e.getY() <= row + squareSize)
                            && (column <= e.getX() && e.getX() <= column + squareSize)) {
                        if (gameModel.movePiece(GAME_VIEW, selectedMovablePiece, ints2)) {
                            GAME_VIEW.updateViewAfterPieceMove(gameModel.getAllyPieceList(), GAME_VIEW.getSelectedMovablePiece());
                        }
                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
