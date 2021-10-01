package Commands;

import oolala.creatures.Creature;

public class CommandLeftTurn extends Command {

    public CommandLeftTurn () {
        setName("lt");
        setNumArgs(2);
    }

    @Override
    public void execute (Creature current, int val) { current.changeOrientation(-val); }
}
