package model;

class SharkBlue extends Shark {

    SharkBlue(int position) {
        super(Types.BLUE, position, Abilities.PROTECT);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        movementDistance /= 3;

        // Up
        addGapMOVEMENT_COORD(new int[]{-2, 0}, movementDistance);

        // Down
        addGapMOVEMENT_COORD(new int[]{2, 0}, movementDistance);

        // Left
        addGapMOVEMENT_COORD(new int[]{0, -2}, movementDistance);

        // Right
        addGapMOVEMENT_COORD(new int[]{0, 2}, movementDistance);

        // Upper-Left
        addGapMOVEMENT_COORD(new int[]{-1, -1}, movementDistance);

        // Upper-Right
        addGapMOVEMENT_COORD(new int[]{-1, 1}, movementDistance);

        // Lower-Left
        addGapMOVEMENT_COORD(new int[]{1, -1}, movementDistance);

        // Lower-Right
        addGapMOVEMENT_COORD(new int[]{1, 1}, movementDistance);
    }

}