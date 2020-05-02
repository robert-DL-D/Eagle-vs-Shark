package model;

class GreenPieceFactory extends AbstractFactory {

    @Override
    MovablePiece getPiece(String owner, int position) {
        if (owner.equalsIgnoreCase("Eagle")) {
            return new EagleGreen(position);
        } else if (owner.equalsIgnoreCase("Shark")) {
            return new SharkGreen(position);
        }
        return null;
    }
}
