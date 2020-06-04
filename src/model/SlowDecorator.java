package model;

class SlowDecorator extends AbilityDecorator {

    private final boolean SUPER_ABILITY;

    SlowDecorator(boolean superAbility) {
        SUPER_ABILITY = superAbility;
    }

    @Override
    void useAbility(MovablePiece movablePiece) {
        slowPiece(movablePiece);
    }

    private void slowPiece(MovablePiece movablePiece) {
        movablePiece.getMOVEMENT_COORD().clear();

        int movementDistance;

        if (SUPER_ABILITY) {
            movementDistance = MovablePiece.DEFAULT_MOVEMENT_DISTANCE - 2;
        } else {
            movementDistance = MovablePiece.DEFAULT_MOVEMENT_DISTANCE - 1;
        }
        movablePiece.addMovementCoord(movementDistance);

        movablePiece.setSlowed(true);
    }
}
