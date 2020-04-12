package model;

import java.util.LinkedList;
import java.util.List;

public class EagleRed extends Eagle {

    private final List<int[]> ROOK_COORD = new LinkedList<>();

    public EagleRed(int position, Enum type) {
        super(position, type);

        // Up
        ROOK_COORD.add(new int[]{-1, 0});
        ROOK_COORD.add(new int[]{-2, 0});

        // Down
        ROOK_COORD.add(new int[]{1, 0});
        ROOK_COORD.add(new int[]{2, 0});

        // Left
        ROOK_COORD.add(new int[]{0, -1});
        ROOK_COORD.add(new int[]{0, -2});

        // Right
        ROOK_COORD.add(new int[]{0, 1});
        ROOK_COORD.add(new int[]{0, 2});
    }

    public List<int[]> getMovableCoords() {
        return ROOK_COORD;
    }
}
