package model;

import java.util.List;

public class EagleGreen extends Eagle {

    private Enum ability = Abilities.PH2;

    public EagleGreen(int position, Enum type) {
        super(position, type);

    }

    @Override
    public List<int[]> getMovableCoords() {
        return null;
    }

    @Override
    public Enum getAbility() {
        return ability;
    }
}
