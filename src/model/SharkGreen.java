package model;

class SharkGreen extends Shark {

    SharkGreen(int position) {
        super(Types.GREEN, position, Abilities.SPEED);

        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {
        // Upper-Left
        addIncrementMOVEMENT_COORD(new int[]{-1, -1}, movementDistance);

        // Upper-Right
        addIncrementMOVEMENT_COORD(new int[]{-1, 1}, movementDistance);

        // Lower-Left
        addIncrementMOVEMENT_COORD(new int[]{1, -1}, movementDistance);

        // Lower-Right
        addIncrementMOVEMENT_COORD(new int[]{1, 1}, movementDistance);
    }

}