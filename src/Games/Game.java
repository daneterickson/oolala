package Games;

import Creatures.Creature;

import java.util.*;

public abstract class Game {

    private Map<Integer, Creature> myCreaturesMap;

    public Game () {
        myCreaturesMap = new HashMap<>();
    }

    public Map<Integer, Creature> getCreaturesMap () { return myCreaturesMap; }


    public void step (String command) {}

    public void executeCommand (String command, Creature current) {
        String[] commands = command.split(" ");
        current.setStatusStamp(false);
        switch (commands[0]) {
            case "fd" -> current.move(Integer.valueOf(commands[1]));
            case "bk" -> current.move(-Integer.valueOf(commands[1]));
            case "lt" -> current.changeOrientation(-Integer.valueOf(commands[1]));
            case "rt" -> current.changeOrientation(Integer.valueOf(commands[1]));
            case "pd" -> current.setPenActivity(true);
            case "pu" -> current.setPenActivity(false);
            case "st" -> current.setCreatureVisibility(true);
            case "ht" -> current.setCreatureVisibility(false);
            case "home" -> current.reset();
            case "stamp" -> current.setStatusStamp(true);
        }
    }
}
