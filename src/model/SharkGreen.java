package model;

import java.util.LinkedList;
import java.util.List;

public class SharkGreen extends Shark {

    private final List<int[]> bishopCoords = new LinkedList<>();

    public SharkGreen(int position, Enum type) {
        super(position, type);

        // Upper-Left
        bishopCoords.add(new int[]{-1, -1});
        bishopCoords.add(new int[]{-2, -2});

        // Upper-Right
        bishopCoords.add(new int[]{1, 1});
        bishopCoords.add(new int[]{2, 2});

        // Lower-Left
        bishopCoords.add(new int[]{1, -1});
        bishopCoords.add(new int[]{2, -2});

        // Lower-Right
        bishopCoords.add(new int[]{-1, 1});
        bishopCoords.add(new int[]{-2, 2});
    }

    public List<int[]> getMovableCoords() {
        return bishopCoords;
    }
}
