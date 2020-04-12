package model;

import java.util.LinkedList;
import java.util.List;

public class SharkRed extends Shark {

    private final List<int[]> KNIGHT_COORD = new LinkedList<>();

    public SharkRed(int position, Enum type) {
        super(position, type);

        KNIGHT_COORD.add(new int[]{-1, 2});
        KNIGHT_COORD.add(new int[]{1, 2});
        KNIGHT_COORD.add(new int[]{2, 1});
        KNIGHT_COORD.add(new int[]{2, -1});
        KNIGHT_COORD.add(new int[]{1, -2});
        KNIGHT_COORD.add(new int[]{-1, -2});
        KNIGHT_COORD.add(new int[]{-2, -1});
        KNIGHT_COORD.add(new int[]{-2, 1});
        KNIGHT_COORD.add(new int[]{-1, -1});
        KNIGHT_COORD.add(new int[]{1, 1});
        KNIGHT_COORD.add(new int[]{-1, 1});
        KNIGHT_COORD.add(new int[]{1, -1});
    }

    public List<int[]> getMovableCoords() {
        return KNIGHT_COORD;
    }
}
