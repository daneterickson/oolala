package Commands;

import Creatures.Creature;

public class CommandHideTurtle extends Command {
    public CommandHideTurtle () {
        setName("ht");
        setNumArgs(1);
    }

    @Override
    public void execute (Creature current, int val) {
        current.setCreatureVisibility(false);
    }
}
