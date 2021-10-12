package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

/**
 * CommandInfect class that creates executable commands for "infect"
 *
 * @author Norah Tan
 */
public class CommandInfect extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
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
