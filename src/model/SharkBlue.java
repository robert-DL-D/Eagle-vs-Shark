package model;

class SharkBlue extends Shark {

    SharkBlue(int position) {
        super(Types.BLUE, position, Abilities.SHIELD);
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
        addMOVEMENT_COORD(new int[]{-2, -2}, movementDistance, 2);

        // Upper-Right
        addMOVEMENT_COORD(new int[]{-2, 2}, movementDistance, 2);

        // Lower-Left
        addMOVEMENT_COORD(new int[]{2, -2}, movementDistance, 2);

        // Lower-Right
        addMOVEMENT_COORD(new int[]{2, 2}, movementDistance, 2);

    }

}