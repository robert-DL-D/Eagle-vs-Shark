package model;

class BluePieceFactory extends AbstractFactory {

    @Override
    MovablePiece getPiece(String owner, int position) {
        if (owner.equalsIgnoreCase("Eagle")) {
            return new EagleBlue(position);
        } else if (owner.equalsIgnoreCase("Shark")) {
            return new SharkBlue(position);
        }
        return null;
    }
}
