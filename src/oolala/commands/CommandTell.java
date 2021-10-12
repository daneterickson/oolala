package oolala.commands;


/**
 * CommandTell class that creates executable commands for "tell"
 *
 * @author Norah Tan
 */
public class CommandTell extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandTell () {
        setName("tell");
        setNumArgs(-1);//variable length
    }

}
