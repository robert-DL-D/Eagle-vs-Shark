package model;

import java.util.LinkedList;
import java.util.List;

public abstract class MovablePiece
        extends Piece {

    final List<int[]> MOVEMENT_COORD = new LinkedList<>();
    Enum ability;
    static final int DEFAULT_MOVEMENT_DISTANCE = 2;

    private boolean movingMode;
    private boolean stunned;
    private boolean slowed;

    MovablePiece(Enum type, int position, Enum ability) {
        super(type, position);
        this.ability = ability;

        movingMode = true;
    }

    void addIncrementMOVEMENT_COORD(int[] movementCoord, int movementDistance) {
        MOVEMENT_COORD.add(movementCoord);

        int index = MOVEMENT_COORD.size() - 1;

        for (int i = 1; i < movementDistance; i++) {

            MOVEMENT_COORD.add(new int[]{getIncrementPosition(MOVEMENT_COORD.get(index)[0]),
                    getIncrementPosition(MOVEMENT_COORD.get(index)[1])});
            index++;
        }
    }

    void addGapMOVEMENT_COORD(int[] movementCoord, int movementDistance) {
        MOVEMENT_COORD.add(movementCoord);

        int index = MOVEMENT_COORD.size() - 1;

        for (int i = 0; i < movementDistance; i++) {

            MOVEMENT_COORD.add(new int[]{getGapPosition(MOVEMENT_COORD.get(index)[0]),
                    getGapPosition(MOVEMENT_COORD.get(index)[1])});
        }
    }

    private int getIncrementPosition(int i) {
        if (Integer.signum(i) == 1) {
            return i + 1;
        } else if (Integer.signum(i) == -1) {
            return i - 1;
        }

        return 0;
    }

    private int getGapPosition(int i) {
        if (Integer.signum(i) == 1) {
            return i + 2;
        } else if (Integer.signum(i) == -1) {
            return i - 2;
        }

        return 0;
    }

    boolean updatePieceRowColumn(Player<Eagle> eaglePlayer, Player<Shark> sharkPlayer, Square[][] squares, int[] movementCoord) {

        Square currentSquare = squares[row][column];
        Square newSquare = squares[row + movementCoord[0]][column + movementCoord[1]];

        if (checkValidNewSquare(currentSquare, newSquare)) {

            row += movementCoord[0];
            column += movementCoord[1];
            changePieceOnSquare(eaglePlayer, sharkPlayer, currentSquare, newSquare);

            return true;
        }

        return false;
    }

    private boolean checkValidNewSquare(Square currentSquare, Square newSquare) {

        if (newSquare.getPiece() == null && newSquare.getMovablePiece() == null) {
            return true;
        } else {
            MovablePiece movablePieceOnCurrentSquare = currentSquare.getMovablePiece();
            Piece pieceOnNewSquare = newSquare.getPiece();

            if (pieceOnNewSquare instanceof Island) {
                return false;
            } else if (pieceOnNewSquare instanceof Flag) {
                return !((Flag) pieceOnNewSquare).getPLAYER().getMOVABLEPIECE_LIST().contains(movablePieceOnCurrentSquare);
            } else {
                MovablePiece movablePieceOnNewSquare = newSquare.getMovablePiece();

                Enum movablePieceOnCurrentSquareType = movablePieceOnCurrentSquare.getType();
                Enum movablePieceOnNewSquareType = movablePieceOnNewSquare.getType();

                return (movablePieceOnCurrentSquare.getClass().getSuperclass() != movablePieceOnNewSquare.getClass().getSuperclass())
                        && ((movablePieceOnCurrentSquareType == Types.RED && movablePieceOnNewSquareType == Types.GREEN)
                        || (movablePieceOnCurrentSquareType == Types.GREEN && movablePieceOnNewSquareType == Types.BLUE)
                        || (movablePieceOnCurrentSquareType == Types.BLUE && movablePieceOnNewSquareType == Types.RED));
            }
        }
    }

    private void changePieceOnSquare(Player<Eagle> eaglePlayer, Player<Shark> sharkPlayer, Square currentSquare, Square
            newSquare) {

        if (newSquare.getMovablePiece() != null) {

            Player<? extends MovablePiece> player = currentSquare.getMovablePiece() instanceof Eagle ? sharkPlayer : eaglePlayer;

            player.getMOVABLEPIECE_LIST().remove(newSquare.getMovablePiece());

            newSquare.removeMovablePiece();

        }

        currentSquare.removeMovablePiece();
        newSquare.addMovablePiece(this);
    }

    public List<int[]> getMovableCoords() {
        return MOVEMENT_COORD;
    }

    public boolean isStunned() {
        return stunned;
    }

    public boolean isSlowed() {
        return slowed;
    }

    public Enum getAbility() {
        return ability;
    }

    List<int[]> getMOVEMENT_COORD() {
        return MOVEMENT_COORD;
    }

    public boolean isMovingMode() {
        return movingMode;
    }

    public void setMovingMode(boolean movingMode) {
        this.movingMode = movingMode;
    }

    void setSlowed(boolean slowed) {
        this.slowed = slowed;
    }

    void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    abstract void addMovementCoord(int movementDistance);

}
