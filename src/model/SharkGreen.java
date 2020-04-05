package model;

import java.util.LinkedList;
import java.util.List;

public class SharkGreen extends Shark {

    private final List<int[]> BISHOP_COORD = new LinkedList<>();

    public SharkGreen(int position, Enum type) {
        super(position, type);

        // Upper-Left
        BISHOP_COORD.add(new int[]{-1, -1});
        BISHOP_COORD.add(new int[]{-2, -2});

        // Upper-Right
        BISHOP_COORD.add(new int[]{1, 1});
        BISHOP_COORD.add(new int[]{2, 2});

        // Lower-Left
        BISHOP_COORD.add(new int[]{1, -1});
        BISHOP_COORD.add(new int[]{2, -2});

        // Lower-Right
        BISHOP_COORD.add(new int[]{-1, 1});
        BISHOP_COORD.add(new int[]{-2, 2});
    }

    public List<int[]> getMovableCoords() {
        return BISHOP_COORD;
    }
}
