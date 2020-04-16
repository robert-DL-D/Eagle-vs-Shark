package model;

class EagleGreen extends Eagle {

    EagleGreen(int position, Enum type) {
        super(position, type);

        ability = Abilities.PH2;

        // Up
        MOVEMENT_COORD.add(new int[]{-1, 0});

        // Down
        MOVEMENT_COORD.add(new int[]{1, 0});

        // Left
        MOVEMENT_COORD.add(new int[]{0, -1});

        // Right
        MOVEMENT_COORD.add(new int[]{0, 1});

        // Upper-Left
        MOVEMENT_COORD.add(new int[]{-2, -2});

        // Upper-Right
        MOVEMENT_COORD.add(new int[]{2, 2});

        // Lower-Left
        MOVEMENT_COORD.add(new int[]{2, -2});

        // Lower-Right
        MOVEMENT_COORD.add(new int[]{-2, 2});

    }

}