package oolala.commands;

import oolala.creatures.Creature;

public class CommandBackward extends Command {

    public CommandBackward () {
        setName("bk");
        setNumArgs(2);
    }

    @Override
    public void execute (Creature current, int val) { current.move(-val); }
}