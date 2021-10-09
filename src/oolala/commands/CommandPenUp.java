package oolala.commands;

import oolala.creatures.Creature;

public class CommandPenUp extends Command {

    public CommandPenUp () {
        setName("pu");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setPenActivity(false);
        return false;
    }
}
