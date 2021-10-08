package oolala.commands;

import oolala.creatures.Creature;

public class CommandLeftTurn extends Command {

    public CommandLeftTurn () {
        setName("lt");
        setNumArgs(2);
    }

    @Override
    public boolean execute (Creature current, int val) {
        current.changeOrientation(-val);
        return false;
    }

    @Override
    public boolean isAction () { return true; }

    @Override
    public boolean equals (Object obj) {
        Command cmd = (Command) obj;
        return cmd.getName().equals("lt") || cmd.getName().equals("left");
    }
}
