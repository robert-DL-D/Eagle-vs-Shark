package model;

class EagleGreen extends Eagle {

    EagleGreen(int position) {
        super(Types.GREEN, position, Abilities.SPEED);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

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