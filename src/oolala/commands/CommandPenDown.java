package oolala.commands;

import oolala.creatures.Creature;

public class CommandPenDown extends Command {
    public CommandPenDown () {
        setName("pd");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setPenActivity(true);
        return false;
    }
}
