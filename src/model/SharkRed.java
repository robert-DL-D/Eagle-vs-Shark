package model;

class SharkRed extends Shark {

    SharkRed(int position) {
        super(Types.RED, position, Abilities.SLOW);
        addMovementCoord(DEFAULT_MOVEMENT_DISTANCE);
    }

    @Override
    void addMovementCoord(int movementDistance) {

        int adjustedMovementDistance = movementDistance - 2;
        int adjustedMovementDistance2 = movementDistance - 1;

        addMOVEMENT_COORD(new int[]{-adjustedMovementDistance, adjustedMovementDistance2}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{adjustedMovementDistance, adjustedMovementDistance2}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{adjustedMovementDistance2, adjustedMovementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{adjustedMovementDistance2, -adjustedMovementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{adjustedMovementDistance, -adjustedMovementDistance2}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{-adjustedMovementDistance, -adjustedMovementDistance2}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{-adjustedMovementDistance2, -adjustedMovementDistance}, adjustedMovementDistance, 1);
        addMOVEMENT_COORD(new int[]{-adjustedMovementDistance2, adjustedMovementDistance}, adjustedMovementDistance, 1);

    }
}