package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

public class CommandReset extends Command {
    public CommandReset () {
        setName("home");
        setNumArgs(2);
    }

    @Override
    public boolean execute (Creature current, Game game) {
        current.reset(game);
        return false;
    }

}
