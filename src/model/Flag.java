package model;

import java.io.Serializable;

public class Flag extends Piece implements Serializable {

    private final Player PLAYER;
    private final String PLAYER_STRING;

    Flag(int position, Player player, String playerString) {
        super(Types.FLAG, position);

        PLAYER = player;
        PLAYER_STRING = playerString;
    }

    public Player getPLAYER() {
        return PLAYER;
    }

    public String getPLAYERSTRING() {
        return PLAYER_STRING;
    }
}
