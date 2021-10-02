package oolala.commands;

import oolala.creatures.Creature;

public class CommandForward extends Command {

    public CommandForward () {
        setName("fd");
        setNumArgs(2);
    }

    @Override
    public void execute (Creature current, int val) {
        current.move(val);
    }



}
