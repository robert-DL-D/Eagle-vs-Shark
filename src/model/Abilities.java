package model;

enum Abilities {

    STUN, SPEED, SLOW, SHIELD, RETREAT, CLEANSE;

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

            case RETREAT:
                return StringText.RETREAT;

            case CLEANSE:
                return StringText.CLEANSE;

            default:
                return "NONE";
        }
    }
}
