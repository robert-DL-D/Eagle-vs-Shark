package model;

class SlowDecorator extends AbilityDecorator {

    @Override
    void useAbility(MovablePiece movablePiece) {
        slowPiece(movablePiece);
    }

    private void slowPiece(MovablePiece movablePiece) {
        movablePiece.getMOVEMENT_COORD().clear();
        movablePiece.addMovementCoord(MovablePiece.DEFAULT_MOVEMENT_DISTANCE - 1);
        movablePiece.setSlowed(true);
    }
}
