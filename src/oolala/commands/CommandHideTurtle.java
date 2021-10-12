package oolala.commands;

import oolala.creatures.Creature;

/**
 * CommandHideTurtle class that creates executable commands for "ht"
 *
 * @author Norah Tan
 */
public class CommandHideTurtle extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandHideTurtle () {
        setName("ht");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setCreatureVisibility(false);
        return false;
    }
}
