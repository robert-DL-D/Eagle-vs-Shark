package model;

enum Abilities {

    STUN, SPEED, SLOW, SHIELD, PH2, CLEANSE;

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

            case CLEANSE:
                return "CLEANSE";

            default:
                return "NONE";
        }
    }
}
