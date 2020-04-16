package model;

class EagleRed extends Eagle {

    EagleRed(int position, Enum type) {
        super(position, type);

        ability = Abilities.STUN;

        // Up
        MOVEMENT_COORD.add(new int[]{-1, 0});
        MOVEMENT_COORD.add(new int[]{-2, 0});

        // Down
        MOVEMENT_COORD.add(new int[]{1, 0});
        MOVEMENT_COORD.add(new int[]{2, 0});

        // Left
        MOVEMENT_COORD.add(new int[]{0, -1});
        MOVEMENT_COORD.add(new int[]{0, -2});

        // Right
        MOVEMENT_COORD.add(new int[]{0, 1});
        MOVEMENT_COORD.add(new int[]{0, 2});
    }

}