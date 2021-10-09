package oolala.commands;

public class CommandGo extends Command {
    public CommandGo () {
        setName("go");
        setNumArgs(0);
    }

    public boolean execute () {
        return true;
    }

}
