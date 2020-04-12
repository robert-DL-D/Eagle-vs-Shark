package model;

import java.util.LinkedList;
import java.util.List;

public class EagleBlue extends Eagle {

    private final List<int[]> QUEEN_COORD = new LinkedList<>();

    public EagleBlue(int position, Enum type) {
        super(position, type);

        // Up
        QUEEN_COORD.add(new int[]{-1, 0});

        // Down
        QUEEN_COORD.add(new int[]{1, 0});

        // Left
        QUEEN_COORD.add(new int[]{0, -1});

        // Right
        QUEEN_COORD.add(new int[]{0, 1});

        // Upper-Left
        QUEEN_COORD.add(new int[]{-1, -1});

        // Upper-Right
        QUEEN_COORD.add(new int[]{1, 1});

        // Lower-Left
        QUEEN_COORD.add(new int[]{1, -1});

        // Lower-Right
        QUEEN_COORD.add(new int[]{-1, 1});
    }

    public List<int[]> getMovableCoords() {
        return QUEEN_COORD;
    }
}
