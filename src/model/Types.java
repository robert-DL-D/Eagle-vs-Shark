package model;

public enum Types {

    RED, GREEN, BLUE, FLAG, ISLAND;

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

            case ISLAND:
                return "ISLAND";

            default:
                return "NONE";
        }
    }
}