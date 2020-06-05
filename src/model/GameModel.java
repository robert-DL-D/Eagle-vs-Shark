package model;

import java.io.Serializable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.GameView;

public class GameModel implements Serializable {

    private final BoardModel BOARD_MODEL = BoardModel.getInstance();
    private final PlayerManagement PLAYER_MANAGEMENT = new PlayerManagement();
    private final PieceManagement PIECE_MANAGEMENT = new PieceManagement(PLAYER_MANAGEMENT, BOARD_MODEL);
    private final CommandModel COMMAND_MODEL = new CommandModel();

    public boolean movePiece(MovablePiece movablePiece, int[] movementCoord) {

        if (movementCoord != null) {

            int row = movablePiece.getRow();
            int column = movablePiece.getColumn();

            Square currentSquare = BOARD_MODEL.getSQUARE_ARRAY()[row][column];
            Square newSquare = BOARD_MODEL.getSQUARE_ARRAY()[row + movementCoord[0]][column + movementCoord[1]];

            MovablePiece newSquareMovablePiece = newSquare.getMovablePiece();

            if (checkValidNewSquare(movablePiece, newSquareMovablePiece, newSquare.getPiece())) {

                for (int i = 0; i < Math.abs(movementCoord[0]); i++) {
                    if (movementCoord[0] > 0) {
                        movablePiece.moveUp();
                    } else if (movementCoord[0] < 0) {
                        movablePiece.moveDown();
                    }
                }

                for (int i = 0; i < Math.abs(movementCoord[1]); i++) {
                    if (movementCoord[1] > 0) {
                        movablePiece.moveRight();
                    } else if (movementCoord[1] < 0) {
                        movablePiece.moveLeft();
                    }
                }

                getCurrentPlayer().setPieceMoved(true);

                // if the newsquare has a piece, remove it from the moveablepiecelist of the player
                // and remove it from the square
                int index = -1;
                if (newSquareMovablePiece != null) {
                    List<? extends MovablePiece> enemyPieceList = PLAYER_MANAGEMENT.getEnemyPieceList();
                    index = enemyPieceList.indexOf(newSquareMovablePiece);
                    enemyPieceList.remove(newSquareMovablePiece);
                    newSquare.removeMovablePiece();
                }

                currentSquare.removeMovablePiece(); // remove the piece from the currentsquare
                newSquare.setMovablePiece(movablePiece); // set the piece to the newsquare

                MoveCommand moveCommand = new MoveCommand(row, column, movablePiece, newSquareMovablePiece, index, getCurrentPlayer());
                COMMAND_MODEL.addCommand(moveCommand);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkValidNewSquare(MovablePiece currentSquareMovableP, MovablePiece newSquareMovableP, Piece newSquarePiece) {

        // Can move to a square that is empty
        if (newSquarePiece == null && newSquareMovableP == null) {
            return true;
        } else {

            if (newSquarePiece instanceof Island) { // Cannot move to a square that has an island
                return false;
            } else if (newSquarePiece instanceof Flag) { // Cannot move to a flag on the same team
                return !((Flag) newSquarePiece).getPLAYER().getMOVABLEPIECE_LIST().contains(currentSquareMovableP);
            } else {

                if (newSquareMovableP != null) {

                    // Cannot move to a square with a piece that's shielded
                    if (newSquareMovableP.isShielded()) {
                        return false;
                    }

                    Enum currentSquareMovablePType = currentSquareMovableP.getType();
                    Enum newSquareMovablePType = newSquareMovableP.getType();

                    // This checks the two pieces must be on different team,
                    // Red can move to a Green piece,
                    // Green can move to a Blue piece,
                    // Blue can move to a Red piece
                    return (currentSquareMovableP.getClass().getSuperclass() != newSquareMovableP.getClass().getSuperclass())
                            && ((currentSquareMovablePType == Types.RED && newSquareMovablePType == Types.GREEN)
                            || (currentSquareMovablePType == Types.GREEN && newSquareMovablePType == Types.BLUE)
                            || (currentSquareMovablePType == Types.BLUE && newSquareMovablePType == Types.RED));
                } else return false;
            }
        }
    }

    public void undoMove(GameView gameView) {
        int undoTurn = gameView.getUndoTurn();

        List<Command> commandList = COMMAND_MODEL.getCOMMAND_LIST();
        if (!commandList.isEmpty()) {

            int playerUndoCount = 0;
            int otherPlayerUndoCount = 0;
            int loopCount = 0;

            for (int i = commandList.size() - 1; i >= 0; i--) {

                if (((MoveCommand) commandList.get(i)).getPLAYER() == getCurrentPlayer()) {
                    playerUndoCount++;
                } else {
                    otherPlayerUndoCount++;
                }

                loopCount++;
                if (playerUndoCount == undoTurn && otherPlayerUndoCount == undoTurn) {
                    break;
                }
            }

            if (playerUndoCount == undoTurn && otherPlayerUndoCount == undoTurn) {

                for (int j = 0; j < loopCount; j++) {

                    MoveCommand command = (MoveCommand) COMMAND_MODEL.getCommand();
                    PLAYER_MANAGEMENT.undoMove(command);
                    BOARD_MODEL.addPieceToSquare(command.getMOVED_PIECE());
                    BOARD_MODEL.addPieceToSquare(command.getCAPTURED_PIECE());
                    gameView.addMPieceCoord(getCapturedPiece());
                    COMMAND_MODEL.removeCommand();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "One of the player has not performed enough move to undo",
                        "", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void disableSuper() {
        PLAYER_MANAGEMENT.getCurrentPlayer().setSuperAvailable(false);
    }

    public List<? extends MovablePiece> getEnemyPieceList() {
        return PLAYER_MANAGEMENT.getEnemyPieceList();
    }

    public List<? extends MovablePiece> getAllyPieceList() {
        return PLAYER_MANAGEMENT.getOwnPieceList();
    }

    public List<? extends MovablePiece> getStunnedPieceList() {
        return PLAYER_MANAGEMENT.getStunnedPieceList();
    }

    public Player<? extends MovablePiece> getCurrentPlayer() {
        return PLAYER_MANAGEMENT.getCurrentPlayer();
    }

    public Player<Eagle> getEAGLE_PLAYER() {
        return PLAYER_MANAGEMENT.getEAGLE_PLAYER();
    }

    public Player<Shark> getSHARK_PLAYER() {
        return PLAYER_MANAGEMENT.getSHARK_PLAYER();
    }

    public void updateNextTurn() {
        PLAYER_MANAGEMENT.updateNextTurn();
    }

    public void updateMovingMode(String actionCommand, int selectedButtonIndex) {
        PLAYER_MANAGEMENT.updateMovingMode(actionCommand, selectedButtonIndex);
    }

    public boolean isEagleTurn() {
        return PLAYER_MANAGEMENT.isEagleTurn();
    }

    public List<Flag> getFLAG_LIST() {
        return PIECE_MANAGEMENT.getFLAG_LIST();
    }

    public List<Island> getISLAND_LIST() {
        return PIECE_MANAGEMENT.getISLAND_LIST();
    }

    public Square[][] getSQUARE_ARRAY() {
        return BOARD_MODEL.getSQUARE_ARRAY();
    }

    public boolean isSuperUsed() {
        return PLAYER_MANAGEMENT.getCurrentPlayer().isSuperAvailable();
    }

    public int getCOMMAND_LISTsize() {
        return COMMAND_MODEL.getCOMMAND_LIST().size();
    }

    public MovablePiece getCapturedPiece() {
        return ((MoveCommand) COMMAND_MODEL.getCommand()).getCAPTURED_PIECE();
    }

}
