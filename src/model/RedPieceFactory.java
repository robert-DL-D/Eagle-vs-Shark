package model;

class RedPieceFactory extends AbstractFactory {

    @Override
    MovablePiece getPiece(String owner, int position) {
        if (owner.equalsIgnoreCase("Eagle")) {
            return new EagleRed(position);
        } else if (owner.equalsIgnoreCase("Shark")) {
            return new SharkRed(position);
        }
        return null;
    }
}
