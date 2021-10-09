package oolala.commands;

import oolala.creatures.Creature;

public class CommandShowTurtle extends Command {
    public CommandShowTurtle () {
        setName("st");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setCreatureVisibility(true);
        return false;
    }
}
