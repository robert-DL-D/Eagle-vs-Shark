package model;

import java.io.Serializable;
import java.util.List;

public class GameModel implements Serializable {

    private final BoardModel SQUARES_MODEL = BoardModel.getInstance();
    private final PlayerModel PLAYER_MODEL = new PlayerModel();
    private final AddPieceModel PIECE_MODEL = new AddPieceModel(PLAYER_MODEL, SQUARES_MODEL);

    public boolean movePiece(int selectedButtonIndex, int[] movementCoord) {

        if (movementCoord != null) {
            MovablePiece movablePiece = PLAYER_MODEL.getOwnPieceList().get(selectedButtonIndex);

            int row = movablePiece.getRow();
            int column = movablePiece.getColumn();

            Square currentSquare = SQUARES_MODEL.getSQUARE_ARRAY()[row][column];
            Square newSquare = SQUARES_MODEL.getSQUARE_ARRAY()[row + movementCoord[0]][column + movementCoord[1]];

            if (checkValidNewSquare(movablePiece, newSquare.getMovablePiece(), newSquare.getPiece())) {

                // set the new coord for moveablepiece
                movablePiece.setRow(movablePiece.getRow() + movementCoord[0]);
                movablePiece.setColumn(movablePiece.getColumn() + movementCoord[1]);

                // if the newsquare has a piece, remove it from the moveablepiecelist of the player
                // and remove it from the square
                if (newSquare.getMovablePiece() != null) {
                    PLAYER_MODEL.getEnemyPieceList().remove(newSquare.getMovablePiece());
                    newSquare.removeMovablePiece();
                }

                currentSquare.removeMovablePiece(); // remove the piece from the currentsquare
                newSquare.addMovablePiece(movablePiece); // set the piece to the newsquare

                // Checks if a piece is on the same square as the enemy flag
                for (Flag flag : PIECE_MODEL.getFLAG_LIST()) {
                    Square flagSquare = SQUARES_MODEL.getSQUARE_ARRAY()[flag.getRow()][flag.getColumn()];
                    if (flagSquare.getMovablePiece() != null) {
                        System.out.println(flagSquare.getMovablePiece() instanceof Eagle ? StringText.EAGLE_WON : StringText.SHARK_WON);
                    }
                }

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
            }
        }
    }

    public List<? extends MovablePiece> getEnemyPieceList() {
        return PLAYER_MODEL.getEnemyPieceList();
    }

    public List<? extends MovablePiece> getAllyPieceList() {
        return PLAYER_MODEL.getOwnPieceList();
    }

    public Player<? extends MovablePiece> getCurrentPlayer() {
        return PLAYER_MODEL.getCurrentPlayer();
    }

    public Player<Eagle> getEAGLE_PLAYER() {
        return PLAYER_MODEL.getEAGLE_PLAYER();
    }

    public Player<Shark> getSHARK_PLAYER() {
        return PLAYER_MODEL.getSHARK_PLAYER();
    }

    public void updateNextTurn() {
        PLAYER_MODEL.updateNextTurn();
    }

    public void updateMovingMode(String actionCommand, int selectedButtonIndex) {
        PLAYER_MODEL.updateMovingMode(actionCommand, selectedButtonIndex);
    }

    public boolean isEagleTurn() {
        return PLAYER_MODEL.isEagleTurn();
    }

    public List<Flag> getFLAG_LIST() {
        return PIECE_MODEL.getFLAG_LIST();
    }

    public List<Island> getISLAND_LIST() {
        return PIECE_MODEL.getISLAND_LIST();
    }

    public Square[][] getSQUARE_ARRAY() {
        return SQUARES_MODEL.getSQUARE_ARRAY();
    }

}
