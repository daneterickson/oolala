package oolala.commands;

import oolala.creatures.Creature;

import java.util.Collection;

public class CommandInfect extends Command {
    public CommandInfect () {
        setName("infect");
        setNumArgs(4);
    }

    @Override
    public boolean execute (Creature current, Collection<Creature> creaturesList) {
        for (Creature c: creaturesList) {
            current.infect(c, 10);//need to change this
        }
        return false;
    }
}
