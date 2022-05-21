package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player<T extends MovablePiece> implements Serializable {

    private final List<T> MOVABLE_PIECE_LIST = new ArrayList<>();

    private boolean pieceMoved;
    private String abilityUsed;
    private int pieceModeToggledIndex = -1;
    private boolean superAvailable = true;
    private boolean undoAvailable = true;

    void addMovablePiece(T movablePiece) {
        MOVABLE_PIECE_LIST.add(movablePiece);
    }

    public List<T> getMOVABLE_PIECE_LIST() {
        return MOVABLE_PIECE_LIST;
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

    public int getPieceModeToggledIndex() {
        return pieceModeToggledIndex;
    }

    void setPieceModeToggledIndex(int pieceModeToggledIndex) {
        this.pieceModeToggledIndex = pieceModeToggledIndex;
    }

    boolean isSuperAvailable() {
        return superAvailable;
    }

    void setSuperAvailable(boolean superAvailable) {
        this.superAvailable = superAvailable;
    }

    boolean isUndoAvailable() {
        return undoAvailable;
    }

    void setUndoAvailable(boolean undoAvailable) {
        this.undoAvailable = undoAvailable;
    }
}
