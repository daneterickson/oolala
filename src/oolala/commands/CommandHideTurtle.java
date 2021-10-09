package oolala.commands;

import oolala.creatures.Creature;

public class CommandHideTurtle extends Command {
    public CommandHideTurtle () {
        setName("ht");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setCreatureVisibility(false);
        return false;
    }
}
