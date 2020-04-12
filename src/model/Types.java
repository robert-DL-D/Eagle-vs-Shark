package model;

public enum Types {

    RED, GREEN, BLUE, FLAG;

    public String toString() {

        switch (this) {

            case RED:
                return "RED";

            case GREEN:
                return "GREEN";

            case BLUE:
                return "BLUE";

            case FLAG:
                return "FLAG";

            default:
                return "NONE";
        }
    }
}