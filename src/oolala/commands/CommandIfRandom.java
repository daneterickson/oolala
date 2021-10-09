package oolala.commands;

public class CommandIfRandom extends Command {

    public CommandIfRandom () {
        setName("ifrandom");
        setNumArgs(0);
    }

    @Override
    public boolean execute () {
        return (Math.random() > 0.5);
    }
}
