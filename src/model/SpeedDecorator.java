package model;

class SpeedDecorator extends AbilityDecorator {

    private final boolean SUPER_ABILITY;

    SpeedDecorator(boolean superAbility) {
        SUPER_ABILITY = superAbility;
    }

    @Override
    void useAbility(MovablePiece movablePiece) {
        speedPiece(movablePiece);
    }

    private void speedPiece(MovablePiece movablePiece) {
        movablePiece.getMOVEMENT_COORD().clear();

        int movementDistance;

        if (SUPER_ABILITY) {
            movementDistance = MovablePiece.DEFAULT_MOVEMENT_DISTANCE + 2;
        } else {
            movementDistance = MovablePiece.DEFAULT_MOVEMENT_DISTANCE + 1;
        }

        movablePiece.addMovementCoord(movementDistance);
    }
}
