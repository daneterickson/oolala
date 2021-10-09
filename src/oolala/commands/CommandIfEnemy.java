package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

public class CommandIfEnemy extends Command {
    public CommandIfEnemy () {
        setName("ifenemy");
        setNumArgs(2);
    }

    public boolean execute (Creature current, Game game) {
        for (Creature c: game.getCreaturesMap().values()) {
            if (!current.getType().equals(c.getType())) return true;
        }
        return false;
    }

}
