package Games;

import Creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class TurtleGame extends Game {

    private List<Integer> myActiveIndices;
    private int myOriginX, myOriginY;

    public TurtleGame (int originX, int originY) {
        super();
        myOriginX = originX;
        myOriginY = originY;
        myActiveIndices = new ArrayList<>();
        getCreaturesMap().put(1, new Creature(myOriginX, myOriginY));
        getActiveIndices().add(1);
    }

    public List<Integer> getActiveIndices () { return myActiveIndices; }

    @Override
    public void step (String command) {
        String[] commands = command.split(" ");

        if (commands[0].equals("tell")) {
            getActiveIndices().clear();
            for (int i = 1; i < commands.length; i++) {
                int index = Integer.valueOf(commands[i]);
                getActiveIndices().add(index);
                getCreaturesMap().putIfAbsent(index, new Creature(myOriginX, myOriginY));
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
