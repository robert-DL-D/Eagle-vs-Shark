package model;

import java.util.List;

public class SharkBlue extends Shark {

    private final Enum ABILITY = Abilities.PH1;

    SharkBlue(int position, Enum type) {
        super(position, type);

    }

    @Override
    public List<int[]> getMovableCoords() {
        return null;
    }

    @Override
    public Enum getABILITY() {
        return ABILITY;
    }
}
