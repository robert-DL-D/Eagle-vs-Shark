package model;

enum Abilities {

    STUN, PROTECT, SPEED, SLOW;

    public String toString() {

        switch (this) {

            case STUN:
                return "STUN";

            case PROTECT:
                return "PROTECT";

            case SPEED:
                return "SPEED";

            case SLOW:
                return "SLOW";

            default:
                return "NONE";
        }
    }
}
