package model;

enum Abilities {

    STUN, SPEED, SLOW, PH1, PH2, PH3;

    public String toString() {

        switch (this) {

            case STUN:
                return "STUN";

            case SPEED:
                return "SPEED";

            case SLOW:
                return "SLOW";

            case PH1:
                return "PH1";

            case PH2:
                return "PH2";

            case PH3:
                return "PH3";

            default:
                return "NONE";
        }
    }
}
