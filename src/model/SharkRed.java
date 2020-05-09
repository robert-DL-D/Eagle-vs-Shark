package model;

class SharkRed extends Shark {

    SharkRed(int position) {
        super(Types.RED, position, Abilities.SLOW);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        int adjustedMovementDistance = movementDistance - 1;

        addMOVEMENT_COORD(new int[]{-adjustedMovementDistance, movementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{adjustedMovementDistance, movementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{movementDistance, adjustedMovementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{movementDistance, -adjustedMovementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{adjustedMovementDistance, -movementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{-adjustedMovementDistance, -movementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{-movementDistance, -adjustedMovementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{-movementDistance, adjustedMovementDistance}, adjustedMovementDistance, 1);
    }
}