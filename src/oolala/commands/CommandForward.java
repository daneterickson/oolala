package oolala.commands;

import oolala.creatures.Creature;

public class CommandForward extends Command {

    public CommandForward () {
        setName("fd");
        setNumArgs(2);
    }

    public boolean execute (Creature current, int val) {
        current.move(val);
        return false;
    }

    @Override
    public boolean isAction () { return true; }

    @Override
    public boolean equals (Object obj) {
        Command cmd = (Command) obj;
        return cmd.getName().equals("fd") || cmd.getName().equals("move");
    }



}
