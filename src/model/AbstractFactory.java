package model;

abstract class AbstractFactory {

    abstract MovablePiece getEaglePiece(int position);

    abstract MovablePiece getSharkPiece(int position);

}
