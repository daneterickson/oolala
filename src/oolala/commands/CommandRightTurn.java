package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

public class CommandRightTurn extends Command {

    public CommandRightTurn () {
        setName("rt");
        setNumArgs(3);
    }

    @Override
    public boolean execute (Creature current, int val, Game game) {
        current.changeOrientation(val);
        return false;
    }

    @Override
    public boolean isAction () { return true; }

    @Override
    public boolean equals (Object obj) {
        Command cmd = (Command) obj;
        return cmd.getName().equals("rt") || cmd.getName().equals("right");
    }
}
