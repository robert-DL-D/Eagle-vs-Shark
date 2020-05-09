package model;

class ShieldDecorator extends AbilityDecorator {

    @Override
    void useAbility(MovablePiece movablePiece) {
        shieldPiece(movablePiece);
    }

    private void shieldPiece(MovablePiece movablePiece) {
        movablePiece.setShielded(true);
    }
}
