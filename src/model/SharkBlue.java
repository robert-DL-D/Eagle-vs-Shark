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
        addMOVEMENT_COORD(new int[]{-2, 0}, movementDistance, 2);

        // Down
        addMOVEMENT_COORD(new int[]{2, 0}, movementDistance, 2);

        // Left
        addMOVEMENT_COORD(new int[]{0, -2}, movementDistance, 2);

        // Right
        addMOVEMENT_COORD(new int[]{0, 2}, movementDistance, 2);

        // Upper-Left
        addMOVEMENT_COORD(new int[]{-1, -1}, movementDistance, 2);

        // Upper-Right
        addMOVEMENT_COORD(new int[]{-1, 1}, movementDistance, 2);

        // Lower-Left
        addMOVEMENT_COORD(new int[]{1, -1}, movementDistance, 2);

        // Lower-Right
        addMOVEMENT_COORD(new int[]{1, 1}, movementDistance, 2);
    }

}