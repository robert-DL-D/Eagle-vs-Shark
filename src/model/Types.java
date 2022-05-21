package model;

public enum Types {

    RED, GREEN, BLUE, FLAG, ISLAND;

    public String toString() {

        return switch (this) {
            case RED -> "RED";
            case GREEN -> "GREEN";
            case BLUE -> "BLUE";
            case FLAG -> "FLAG";
            case ISLAND -> "ISLAND";
        };
    }
}