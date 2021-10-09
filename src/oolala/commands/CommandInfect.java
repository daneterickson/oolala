package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

public class CommandInfect extends Command {
    public CommandInfect () {
        setName("infect");
        setNumArgs(2);
    }

    @Override
    public boolean execute (Creature current, Game game) {
        for (Creature c: game.getCreaturesMap().values()) {
            current.infect(c, game);
        }
        return false;
    }

    @Override
    public boolean isAction () { return true; }
}
