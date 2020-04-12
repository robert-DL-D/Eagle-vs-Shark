package model;

import java.util.List;

public class SharkBlue extends Shark {

    private Enum ability = Abilities.PH1;

    public SharkBlue(int position, Enum type) {
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
