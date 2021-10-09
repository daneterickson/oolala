package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

public class CommandBackward extends Command {

    public CommandBackward () {
        setName("bk");
        setNumArgs(3);
    }

    @Override
    public boolean execute (Creature current, int val, Game game) {
        current.move(-val, game);
        return false;
    }
}
