package model;

import java.util.LinkedList;
import java.util.List;

public abstract class MovablePiece
        extends Piece
        implements MoveStrategy {

    private final List<int[]> MOVEMENT_COORD = new LinkedList<>();
    private final Enum ability;
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

    public void useAbility(MovablePiece targetedMovablePiece) {

        AbilityDecorator abilityDecorator = null;

        switch (ability.toString()) {
            case StringText.STUN:
                abilityDecorator = new StunDecorator();
                break;
            case StringText.SPEED:
                abilityDecorator = new SpeedDecorator();
                break;
            case StringText.SLOW:
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

    void setMovingMode(boolean movingMode) {
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

    public void moveUp() {
        row++;
    }

    public void moveDown() {
        row--;
    }

    public void moveLeft() {
        column--;
    }

    public void moveRight() {
        column++;
    }

    abstract void addMovementCoord(int movementDistance);

}
