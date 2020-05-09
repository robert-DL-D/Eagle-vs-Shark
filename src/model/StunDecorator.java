package model;

class StunDecorator extends AbilityDecorator {

    @Override
    void useAbility(MovablePiece movablePiece) {
        stunPiece(movablePiece);
    }

    private void stunPiece(MovablePiece movablePiece) {
        movablePiece.setStunned(true);
    }
}
