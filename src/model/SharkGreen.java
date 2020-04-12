package model;

import java.util.LinkedList;
import java.util.List;

public class SharkGreen extends Shark {

    private final List<int[]> BISHOP_COORD = new LinkedList<>();
    private Enum ability = Abilities.STUN;

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

    @Override
    public List<int[]> getMovableCoords() {
        return BISHOP_COORD;
    }

    @Override
    public Enum getAbility() {
        return ability;
    }
}
