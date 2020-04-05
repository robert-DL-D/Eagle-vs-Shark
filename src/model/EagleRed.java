package model;

import java.util.LinkedList;
import java.util.List;

public class EagleRed extends Eagle {

    private final List<int[]> rookCoords = new LinkedList<>();

    public EagleRed(int position, Enum type) {
        super(position, type);

        // Up
        rookCoords.add(new int[]{-1, 0});
        rookCoords.add(new int[]{-2, 0});

        // Down
        rookCoords.add(new int[]{1, 0});
        rookCoords.add(new int[]{2, 0});

        // Left
        rookCoords.add(new int[]{0, -1});
        rookCoords.add(new int[]{0, -2});

        // Right
        rookCoords.add(new int[]{0, 1});
        rookCoords.add(new int[]{0, 2});

        //type = Type.RED;
    }

    public List<int[]> getMovableCoords() {
        return rookCoords;
    }
}
