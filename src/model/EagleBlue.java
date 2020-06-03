package model;

class EagleBlue extends Eagle {

    EagleBlue(int position) {
        super(Types.BLUE, position, Abilities.RETREAT);

        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        movementDistance -= 1;

        // Up
        addMOVEMENT_COORD(new int[]{-1, 0}, movementDistance, 1);

        // Down
        addMOVEMENT_COORD(new int[]{1, 0}, movementDistance, 1);

        // Left
        addMOVEMENT_COORD(new int[]{0, -1}, movementDistance, 1);

        // Right
        addMOVEMENT_COORD(new int[]{0, 1}, movementDistance, 1);

        // Upper-Left
        addMOVEMENT_COORD(new int[]{-1, -1}, movementDistance, 1);

        // Upper-Right
        addMOVEMENT_COORD(new int[]{-1, 1}, movementDistance, 1);

        // Lower-Left
        addMOVEMENT_COORD(new int[]{1, -1}, movementDistance, 1);

        // Lower-Right
        addMOVEMENT_COORD(new int[]{1, 1}, movementDistance, 1);
    }

}