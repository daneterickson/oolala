package oolala.commands;

/**
 * CommandIfRandom class that creates executable commands for "ifrandom"
 *
 * @author Norah Tan
 */
public class CommandIfRandom extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandIfRandom () {
        setName("ifrandom");
        setNumArgs(0);
    }

    @Override
    public boolean execute () {
        return (Math.random() > 0.5);
    }
}
