package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

public class CommandIfEmpty extends Command {

    public CommandIfEmpty () {
        setName("ifempty");
        setNumArgs(2);
    }

    @Override
    public boolean execute (Creature current, Game game) {
        for (Creature c: game.getCreaturesMap().values()) {
            if (current.isNearbyAhead(c, game)) return false;
        }
        return true;
    }

}
