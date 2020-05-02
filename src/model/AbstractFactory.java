package model;

abstract class AbstractFactory {

    abstract MovablePiece getPiece(String player, int position);
}
