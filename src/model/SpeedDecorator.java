package model;

class SpeedDecorator extends AbilityDecorator {

    @Override
    void useAbility(MovablePiece movablePiece) {
        speedPiece(movablePiece);
    }

    private void speedPiece(MovablePiece movablePiece) {
        movablePiece.getMOVEMENT_COORD().clear();
        movablePiece.addMovementCoord(MovablePiece.DEFAULT_MOVEMENT_DISTANCE + 1);
    }
}
