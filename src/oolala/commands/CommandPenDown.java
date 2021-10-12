package oolala.commands;

import oolala.creatures.Creature;


/**
 * CommandPenDown class that creates executable commands for "pd"
 *
 * @author Norah Tan
 */
public class CommandPenDown extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandPenDown () {
        setName("pd");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setPenActivity(true);
        return false;
    }
}
