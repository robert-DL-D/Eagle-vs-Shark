package model;

enum Abilities {

    STUN, SPEED, SLOW, SHIELD, HIDE, CLEANSE;

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

            case HIDE:
                return StringText.JUMP;

            case CLEANSE:
                return StringText.CLEANSE;

            default:
                return "NONE";
        }
    }
}
