package Games;

import Creatures.Creature;

import java.util.List;

public class Game {

    private List<Creature> myListCreatures;
    private Creature myCurrent;
    private Creature myNext;

    public Game () {

    }

    public void step (String command) {
        String[] commands = command.split(" ");
        if (commands[0].equals("fd")) {
            myCurrent.move(Integer.valueOf(commands[1]));
        }
        else if (commands[0].equals(""));
    }
}
