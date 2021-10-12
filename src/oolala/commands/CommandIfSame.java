package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;


/**
 * CommandIfSame class that creates executable commands for "ifsame"
 *
 * @author Norah Tan
 */
public class CommandIfSame extends Command {
    /**
     * Constructor that sets the name and sets the number of arguments it takes in for execution
     */
    public CommandIfSame () {
        setName("ifsame");
        setNumArgs(2);
    }

    @Override
    public boolean execute (Creature current, Game game) {
        for (Creature c: game.getCreaturesMap().values()) {
            if (current.getType().equals(c.getType())) return true;
        }
        return false;
    }
}
