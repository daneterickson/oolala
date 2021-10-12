package oolala.commands;

import oolala.creatures.Creature;


/**
 * CommandStamp class that creates executable commands for "stamp"
 *
 * @author Norah Tan
 */
public class CommandStamp extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandStamp () {
        setName("stamp");
        setNumArgs(1);
    }

    @Override
    public boolean execute (Creature current) {
        current.setStatusStamp(true);
        return false;
    }
}
