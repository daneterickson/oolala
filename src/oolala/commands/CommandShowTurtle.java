package oolala.commands;

import oolala.creatures.Creature;

public class CommandShowTurtle extends Command {
    public CommandShowTurtle () {
        setName("st");
        setNumArgs(1);
    }

    @Override
    public void execute (Creature current, int val) {
        current.setCreatureVisibility(true);
    }
}
