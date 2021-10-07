package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class DarwinGame extends Game {

    private Map<Integer, String> myInstructionsMap;
    private int myIndex;

    public DarwinGame() {
        myInstructionsMap = new HashMap<>();
        myIndex = 1;
    }

    @Override
    public void step(String command) {
        String[] commands = myInstructionsMap.get(myIndex).split(" ");

        for (Creature current : getCreaturesMap().values()) {
            Command input = new Command(commands[0]);
            Command result = input.recognize();
//            try {
//                result.execute(current, Integer.valueOf(commands[1]));
//            }
//            catch () {
//                
//            }
        }
    }

    @Override
    public String compile (String paragraph) {
        for (String command : paragraph.toLowerCase().split("\n")) {
            String temp = command.split("#")[0];
            if (temp.equals("")) continue;
            myInstructionsMap.put(myInstructionsMap.size() + 1, temp);
        }
        return null;
    }
}
