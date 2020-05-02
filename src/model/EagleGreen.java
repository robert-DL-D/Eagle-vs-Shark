package model;

class EagleGreen extends Eagle {

    EagleGreen(int position) {
        super(Types.GREEN, position, Abilities.SLOW);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        movementDistance /= 3;

        // Up
        addGapMOVEMENT_COORD(new int[]{-1, 0}, movementDistance);

        // Down
        addGapMOVEMENT_COORD(new int[]{1, 0}, movementDistance);

        // Left
        addGapMOVEMENT_COORD(new int[]{0, -1}, movementDistance);

        // Right
        addGapMOVEMENT_COORD(new int[]{0, 1}, movementDistance);

        // Upper-Left
        addGapMOVEMENT_COORD(new int[]{-2, -2}, movementDistance);

        // Upper-Right
        addGapMOVEMENT_COORD(new int[]{-2, 2}, movementDistance);

        // Lower-Left
        addGapMOVEMENT_COORD(new int[]{2, -2}, movementDistance);

        // Lower-Right
        addGapMOVEMENT_COORD(new int[]{2, 2}, movementDistance);
    }

}