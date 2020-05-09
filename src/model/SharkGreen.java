package model;

class SharkGreen extends Shark {

    SharkGreen(int position) {
        super(Types.GREEN, position, Abilities.PH3);

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