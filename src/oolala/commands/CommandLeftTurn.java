package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

/**
 * CommandLeftTurn class that creates executable commands for "lt" and "left
 *
 * @author Norah Tan
 */
public class CommandLeftTurn extends Command {

    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandLeftTurn () {
        setName("lt");
        setNumArgs(3);
    }

    @Override
    public boolean execute (Creature current, double val, Game game) {
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
