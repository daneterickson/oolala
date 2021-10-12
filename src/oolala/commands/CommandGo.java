package oolala.commands;

/**
 * CommandGo class that creates executable commands for "go"
 *
 * @author Norah Tan
 */
public class CommandGo extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandGo () {
        setName("go");
        setNumArgs(0);
    }

    @Override
    public boolean execute () {
        return true;
    }

}
