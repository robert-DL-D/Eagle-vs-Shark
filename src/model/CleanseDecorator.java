package model;

class CleanseDecorator extends AbilityDecorator {

    private final boolean SUPER_ABILITY;

    CleanseDecorator(boolean superAbility) {
        SUPER_ABILITY = superAbility;
    }

    @Override
    void useAbility(MovablePiece movablePiece) {
        cleansePiece(movablePiece);
    }

    private void cleansePiece(MovablePiece movablePiece) {
        movablePiece.setStunned(false);

        if (SUPER_ABILITY) {
            movablePiece.setImmune(true);
        }
    }
}
