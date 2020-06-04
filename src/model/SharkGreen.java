package model;

class SharkGreen extends Shark {

    SharkGreen(int position) {
        super(Types.GREEN, position, Abilities.CLEANSE);

        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {
        movementDistance -= 1;

        // Up
        addMOVEMENT_COORD(new int[]{-2, 0}, movementDistance, 2);

        // Down
        addMOVEMENT_COORD(new int[]{2, 0}, movementDistance, 2);

        // Left
        addMOVEMENT_COORD(new int[]{0, -2}, movementDistance, 2);

        // Right
        addMOVEMENT_COORD(new int[]{0, 2}, movementDistance, 2);

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