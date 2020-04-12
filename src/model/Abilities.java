package model;

enum Abilities {

    STUN, PROTECT, PH1, PH2;

    public String toString() {

        switch (this) {

            case STUN:
                return "STUN";

            case PROTECT:
                return "PROTECT";

            case PH1:
                return "PH1";

            case PH2:
                return "PH2";

            default:
                return "NONE";
        }
    }
}
