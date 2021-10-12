package oolala.commands;

import oolala.creatures.Creature;

/**
 * CommandPenUp class that creates executable commands for "pu"
 *
 * @author Norah Tan
 */
public class CommandPenUp extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandPenUp () {
        setName("pu");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setPenActivity(false);
        return false;
    }
}
