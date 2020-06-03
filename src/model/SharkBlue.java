package model;

class SharkBlue extends Shark {

    SharkBlue(int position) {
        super(Types.BLUE, position, Abilities.SHIELD);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        // Lower-Right
        addMOVEMENT_COORD(new int[]{1, 1}, movementDistance, 2);
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