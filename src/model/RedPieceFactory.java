package model;

class RedPieceFactory extends AbstractFactory {

    @Override
    MovablePiece getEaglePiece(int position) {
        return new EagleRed(position);
    }

    @Override
    MovablePiece getSharkPiece(int position) {
        return new SharkRed(position);
    }

}
