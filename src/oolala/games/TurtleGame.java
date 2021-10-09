package oolala.games;

import oolala.creatures.Creature;


public class TurtleGame extends Game {


    public TurtleGame (int defaultX, int defaultY) {
        super();
        updateHome(defaultX, defaultY);
        getCreaturesMap().put(1, new Creature(getHomeX(), getHomeY()));
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
                if (!getCreaturesMap().containsKey(index)) {
                    getCreaturesMap().put(index, new Creature(getHomeX(), getHomeY()));
                }
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
