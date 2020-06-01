package model;

class CleanseDecorator extends AbilityDecorator {

    @Override
    void useAbility(MovablePiece movablePiece) {
        cleansePiece(movablePiece);
    }

    private void cleansePiece(MovablePiece movablePiece) {
        movablePiece.setStunned(false);
    }
}
