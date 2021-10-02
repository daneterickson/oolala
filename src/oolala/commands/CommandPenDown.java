package oolala.commands;

import oolala.creatures.Creature;

public class CommandPenDown extends Command {
    public CommandPenDown () {
        setName("pd");
        setNumArgs(1);
    }

    @Override
    public void execute (Creature current, int val) {
        current.setPenActivity(true);
    }
}
