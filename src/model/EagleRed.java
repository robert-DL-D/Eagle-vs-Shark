package model;

class EagleRed extends Eagle {

    EagleRed(int position) {
        super(Types.RED, position, Abilities.STUN);

        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {
        // Up
        addMOVEMENT_COORD(new int[]{-1, 0}, movementDistance, 1);

        // Down
        addMOVEMENT_COORD(new int[]{1, 0}, movementDistance, 1);

        // Left
        addMOVEMENT_COORD(new int[]{0, -1}, movementDistance, 1);

        // Right
        addMOVEMENT_COORD(new int[]{0, 1}, movementDistance, 1);
    }

}