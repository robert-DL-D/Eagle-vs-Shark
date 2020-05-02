package model;

class EagleBlue extends Eagle {

    EagleBlue(int position) {
        super(Types.BLUE, position, Abilities.PH2);

        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE - 1);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        // Up
        addIncrementMOVEMENT_COORD(new int[]{-1, 0}, movementDistance);

        // Down
        addIncrementMOVEMENT_COORD(new int[]{1, 0}, movementDistance);

        // Left
        addIncrementMOVEMENT_COORD(new int[]{0, -1}, movementDistance);

        // Right
        addIncrementMOVEMENT_COORD(new int[]{0, 1}, movementDistance);

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