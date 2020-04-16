package model;

class EagleBlue extends Eagle {

    EagleBlue(int position, Enum type) {
        super(position, type);

        ability = Abilities.PH1;

        // Up
        MOVEMENT_COORD.add(new int[]{-1, 0});

        // Down
        MOVEMENT_COORD.add(new int[]{1, 0});

        // Left
        MOVEMENT_COORD.add(new int[]{0, -1});

        // Right
        MOVEMENT_COORD.add(new int[]{0, 1});

        // Upper-Left
        MOVEMENT_COORD.add(new int[]{-1, -1});

        // Upper-Right
        MOVEMENT_COORD.add(new int[]{1, 1});

        // Lower-Left
        MOVEMENT_COORD.add(new int[]{1, -1});

        // Lower-Right
        MOVEMENT_COORD.add(new int[]{-1, 1});
    }

}