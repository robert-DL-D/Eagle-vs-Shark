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
    private boolean shielded;

    MovablePiece(Enum type, int position, Enum ability) {
        super(type, position);
        this.ability = ability;

        movingMode = true;
    }

    void addMOVEMENT_COORD(int[] movementCoord, int movementDistance, int increment) {
        if (movementDistance != 0) {
            MOVEMENT_COORD.add(movementCoord);

            int startIndex = increment == 2 ? 1 : increment;
            int index = MOVEMENT_COORD.size() - 1;

            for (int i = startIndex; i < movementDistance; i++) {
                MOVEMENT_COORD.add(new int[]{getPosition(MOVEMENT_COORD.get(index)[0], increment),
                        getPosition(MOVEMENT_COORD.get(index)[1], increment)});
                index++;
            }
        }

    }

    private int getPosition(int coord, int increment) {
        if (Integer.signum(coord) == 1) {
            return coord + increment;
        } else if (Integer.signum(coord) == -1) {
            return coord - increment;
        }

        return 0;
    }

    boolean updatePieceRowColumn(Player<Eagle> eaglePlayer, Player<Shark> sharkPlayer,
                                 Square[][] squares, int[] movementCoord) {

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

                if (movablePieceOnNewSquare.shielded) {
                    return false;
                }

                Enum movablePieceOnCurrentSquareType = movablePieceOnCurrentSquare.getType();
                Enum movablePieceOnNewSquareType = movablePieceOnNewSquare.getType();

                return (movablePieceOnCurrentSquare.getClass().getSuperclass() != movablePieceOnNewSquare.getClass().getSuperclass())
                        && ((movablePieceOnCurrentSquareType == Types.RED && movablePieceOnNewSquareType == Types.GREEN)
                        || (movablePieceOnCurrentSquareType == Types.GREEN && movablePieceOnNewSquareType == Types.BLUE)
                        || (movablePieceOnCurrentSquareType == Types.BLUE && movablePieceOnNewSquareType == Types.RED));
            }
        }
    }

    private void changePieceOnSquare(Player<Eagle> eaglePlayer, Player<Shark> sharkPlayer,
                                     Square currentSquare, Square newSquare) {

        if (newSquare.getMovablePiece() != null) {

            Player<? extends MovablePiece> player = currentSquare.getMovablePiece() instanceof Eagle ? sharkPlayer : eaglePlayer;

            player.getMOVABLEPIECE_LIST().remove(newSquare.getMovablePiece());

            newSquare.removeMovablePiece();

        }

        currentSquare.removeMovablePiece();
        newSquare.addMovablePiece(this);
    }

    public void useAbility(MovablePiece targetedMovablePiece) {

        AbilityDecorator abilityDecorator = null;

        switch (ability.toString()) {
            case "STUN":
                abilityDecorator = new StunDecorator();
                break;
            case StringText.SPEED:
                abilityDecorator = new SpeedDecorator();
                break;
            case "SLOW":
                abilityDecorator = new SlowDecorator();
                break;
            case StringText.SHIELD:
                abilityDecorator = new ShieldDecorator();
                break;
        }

        abilityDecorator.useAbility(targetedMovablePiece);

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

    public boolean isShielded() {
        return shielded;
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

    void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    abstract void addMovementCoord(int movementDistance);

}
