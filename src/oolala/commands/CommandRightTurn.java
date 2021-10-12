package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

/**
 * CommandRightTurn class that creates executable commands for "rt" and "right"
 *
 * @author Norah Tan
 */
public class CommandRightTurn extends Command {

    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandRightTurn () {
        setName("rt");
        setNumArgs(3);
    }

    @Override
    public boolean execute (Creature current, double val, Game game) {
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
