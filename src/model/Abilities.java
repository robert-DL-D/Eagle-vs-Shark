package model;

enum Abilities {

    STUN, SPEED, SLOW, SHIELD, JUMP, CLEANSE;

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

            case JUMP:
                return StringText.JUMP;

            case CLEANSE:
                return StringText.CLEANSE;

            default:
                return "NONE";
        }
    }
}
