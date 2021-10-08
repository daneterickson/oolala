package oolala.commands;

import oolala.creatures.Creature;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CommandIfEnemy extends Command {
    public CommandIfEnemy () {
        setName("ifenemy");
        setNumArgs(4);
    }

    public boolean execute (Creature current, Collection<Creature> creaturesList) {
        for (Creature c: creaturesList) {
            if (!current.getType().equals(c.getType())) return true;
        }
        return false;
    }

}
