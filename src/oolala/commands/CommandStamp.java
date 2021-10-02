package oolala.commands;

import oolala.creatures.Creature;

public class CommandStamp extends Command {
    public CommandStamp () {
        setName("stamp");
        setNumArgs(1);
    }

    @Override
    public void execute (Creature current, int val) {
        current.setStatusStamp(true);
    }
}