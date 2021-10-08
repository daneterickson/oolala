package oolala.commands;

import oolala.creatures.Creature;

public class CommandStamp extends Command {
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
