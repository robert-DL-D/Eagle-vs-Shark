package model;

class SharkRed extends Shark {

    SharkRed(int position, Enum type) {
        super(position, type);

        ability = Abilities.PH2;

        MOVEMENT_COORD.add(new int[]{-1, 2});
        MOVEMENT_COORD.add(new int[]{1, 2});
        MOVEMENT_COORD.add(new int[]{2, 1});
        MOVEMENT_COORD.add(new int[]{2, -1});
        MOVEMENT_COORD.add(new int[]{1, -2});
        MOVEMENT_COORD.add(new int[]{-1, -2});
        MOVEMENT_COORD.add(new int[]{-2, -1});
        MOVEMENT_COORD.add(new int[]{-2, 1});
        MOVEMENT_COORD.add(new int[]{-1, -1});
        MOVEMENT_COORD.add(new int[]{1, 1});
        MOVEMENT_COORD.add(new int[]{-1, 1});
        MOVEMENT_COORD.add(new int[]{1, -1});
    }

}