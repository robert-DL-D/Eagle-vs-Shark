package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player<T extends MovablePiece> implements Serializable {

    private final List<T> MOVABLEPIECE_LIST = new ArrayList<>();

    private boolean pieceMoved;
    private String abilityUsed;
    private boolean pieceModeToggled;
    private int pieceModeToggledIndex = -1;

    void addMovablePiece(T movablePiece) {
        MOVABLEPIECE_LIST.add(movablePiece);
    }

    public List<T> getMOVABLEPIECE_LIST() {
        return MOVABLEPIECE_LIST;
    }

    public boolean isPieceMoved() {
        return pieceMoved;
    }

    void setPieceMoved(boolean pieceMoved) {
        this.pieceMoved = pieceMoved;
    }

    public String getAbilityUsed() {
        return abilityUsed;
    }

    public void setAbilityUsed(String abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    public boolean isPieceModeToggled() {
        return pieceModeToggled;
    }

    public void setPieceModeToggled(boolean pieceModeToggled) {
        this.pieceModeToggled = pieceModeToggled;
    }

    public int getPieceModeToggledIndex() {
        return pieceModeToggledIndex;
    }

    public void setPieceModeToggledIndex(int pieceModeToggledIndex) {
        this.pieceModeToggledIndex = pieceModeToggledIndex;
    }
}
