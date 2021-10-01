package commands;

import oolala.creatures.Creature;

public class CommandReset extends Command {
    public CommandReset () {
        setName("reset");
        setNumArgs(1);
    }

    @Override
    public void execute (Creature current, int val) {
        current.reset();
    }

}
