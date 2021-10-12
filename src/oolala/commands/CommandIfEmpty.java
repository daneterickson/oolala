package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;


/**
 * CommandIfEmpty class that creates executable commands for "ifenemy"
 *
 * @author Norah Tan
 */
public class CommandIfEmpty extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
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
