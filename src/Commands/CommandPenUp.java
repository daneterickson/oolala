package Commands;

import oolala.creatures.Creature;

public class CommandPenUp extends Command {

    public CommandPenUp () {
        setName("pu");
        setNumArgs(1);
    }

    @Override
    public void execute (Creature current, int val) {
        current.setPenActivity(false);
    }
}
