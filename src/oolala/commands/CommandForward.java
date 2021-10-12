package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

/**
 * CommandForward class that creates executable commands for "fd" and "move"
 *
 * @author Norah Tan
 */
public class CommandForward extends Command {

    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandForward () {
        setName("fd");
        setNumArgs(3);
    }

    @Override
    public boolean execute (Creature current, double val, Game game) {
        current.move(val, game);
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
