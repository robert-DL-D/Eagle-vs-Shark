package model;

class SharkGreen extends Shark {

    SharkGreen(int position, Enum type) {
        super(position, type);

        ability = Abilities.STUN;

        // Upper-Left
        MOVEMENT_COORD.add(new int[]{-1, -1});
        MOVEMENT_COORD.add(new int[]{-2, -2});

        // Upper-Right
        MOVEMENT_COORD.add(new int[]{1, 1});
        MOVEMENT_COORD.add(new int[]{2, 2});

        // Lower-Left
        MOVEMENT_COORD.add(new int[]{1, -1});
        MOVEMENT_COORD.add(new int[]{2, -2});

        // Lower-Right
        MOVEMENT_COORD.add(new int[]{-1, 1});
        MOVEMENT_COORD.add(new int[]{-2, 2});
    }

}