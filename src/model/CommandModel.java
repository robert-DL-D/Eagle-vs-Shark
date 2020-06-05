package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

class CommandModel implements Serializable {

    private final List<Command> COMMAND_LIST = new LinkedList<>();

    void addCommand(MoveCommand moveCommand) {

        if (COMMAND_LIST.size() == 6) {
            COMMAND_LIST.remove(0);
        }

        COMMAND_LIST.add(moveCommand);
    }

    void removeCommand() {
        COMMAND_LIST.remove(COMMAND_LIST.size() - 1);
    }

    List<Command> getCOMMAND_LIST() {
        return COMMAND_LIST;
    }

    Command getCommand() {
        return COMMAND_LIST.get(COMMAND_LIST.size() - 1);
    }

}
