package oolala.commands;

import oolala.creatures.Creature;


/**
 * CommandShowTurtle class that creates executable commands for "st"
 *
 * @author Norah Tan
 */
public class CommandShowTurtle extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandShowTurtle () {
        setName("st");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setCreatureVisibility(true);
        return false;
    }
}
