package oolala.games;

import oolala.creatures.Creature;


public class TurtleGame extends Game {


    private final int myHomeX, myHomeY;

    public TurtleGame (int originX, int originY) {
        super();
        myHomeX = originX;
        myHomeY = originY;
        getCreaturesMap().put(1, new Creature(myHomeX, myHomeY));
        getActiveIndices().add(1);
    }

    @Override
    public void step (String command) {
        String[] commands = command.toLowerCase().split(" ");

        if (commands[0].equals("tell")) {
            getActiveIndices().clear();
            for (int i = 1; i < commands.length; i++) {
                int index = Integer.valueOf(commands[i]);
                getActiveIndices().add(index);
                if (!getCreaturesMap().containsKey(index)) getCreaturesMap().put(index, new Creature(myHomeX, myHomeY));
            }
        }
        else {
            for (int index: getActiveIndices()) {
                Creature current = getCreaturesMap().get(index);
                executeCommand(command, current);
            }
        }
    }

}
