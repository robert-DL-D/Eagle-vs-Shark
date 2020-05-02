package model;

class EagleRed extends Eagle {

    EagleRed(int position) {
        super(Types.RED, position, Abilities.STUN);

        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
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
    }

}