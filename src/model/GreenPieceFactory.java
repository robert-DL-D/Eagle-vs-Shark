package model;

class GreenPieceFactory extends AbstractFactory {

    @Override
    MovablePiece getEaglePiece(int position) {
        return new EagleGreen(position);
    }

    @Override
    MovablePiece getSharkPiece(int position) {
        return new SharkGreen(position);
    }
}
