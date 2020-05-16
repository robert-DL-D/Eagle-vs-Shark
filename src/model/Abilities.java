package model;

enum Abilities {

    STUN, SPEED, SLOW, SHIELD, PH2, PH3;

    public String toString() {

        switch (this) {

            case STUN:
                return StringText.STUN;

            case SPEED:
                return StringText.SPEED;

            case SLOW:
                return StringText.SLOW;

            case SHIELD:
                return StringText.SHIELD;

            case PH2:
                return "PH2";

            case PH3:
                return "PH3";

            default:
                return "NONE";
        }
    }
}
