package model;

import java.util.List;

public class EagleGreen extends Eagle {

    private final Enum ABILITY = Abilities.PH2;

    EagleGreen(int position, Enum type) {
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
