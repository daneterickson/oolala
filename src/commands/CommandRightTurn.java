package commands;

import oolala.creatures.Creature;

public class CommandRightTurn extends Command {

    public CommandRightTurn () {
        setName("rt");
        setNumArgs(2);
    }

    @Override
    public void execute (Creature current, int val) {
        current.changeOrientation(val);
    }
}
