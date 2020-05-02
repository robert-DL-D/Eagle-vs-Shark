package model;

class SharkRed extends Shark {

    SharkRed(int position) {
        super(Types.RED, position, Abilities.SLOW);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        movementDistance -= 1;

        addIncrementMOVEMENT_COORD(new int[]{-1, 2}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{1, 2}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{2, 1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{2, -1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{1, -2}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{-1, -2}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{-2, -1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{-2, 1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{-1, -1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{1, 1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{-1, 1}, movementDistance);
        addIncrementMOVEMENT_COORD(new int[]{1, -1}, movementDistance);
    }
}