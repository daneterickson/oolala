package Games;

import Creatures.Creature;

import java.util.*;

public class Game {

    private Map<Integer, Creature> myCreaturesMap;
    private int myMaxIndex; // always equal to myListCreatures.size()
    private List<Integer> myActiveIndices;

    public Game () {
        myCreaturesMap = new HashMap<>();
        myCreaturesMap.put(1, new Creature());
        myMaxIndex = 1;
        myActiveIndices = new ArrayList<>();
        myActiveIndices.add(1);
    }

    public Map<Integer, Creature> getListCreatures () { return myCreaturesMap; }
    public List<Integer> getActiveIndices () { return myActiveIndices; }

    public void addNewCreature () {
        myMaxIndex++;
        myCreaturesMap.put(myMaxIndex, new Creature());
    }

    public void step (String command) {
        String[] commands = command.split(" ");

        if (commands[0].equals("tell")) {
            myActiveIndices.clear();
            for (int i = 1; i < commands.length; i++) {
                int index = Integer.valueOf(commands[i]);
                myActiveIndices.add(index);
                while (myMaxIndex < index) addNewCreature();
            }
        }
        else {
            for (int index: myActiveIndices) {
                Creature current = myCreaturesMap.get(index);
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

    }
}
