package model;

class BluePieceFactory extends AbstractFactory {

    @Override
    MovablePiece getEaglePiece(int position) {
        return new EagleBlue(position);
    }

    @Override
    MovablePiece getSharkPiece(int position) {
        return new SharkBlue(position);
    }
}
