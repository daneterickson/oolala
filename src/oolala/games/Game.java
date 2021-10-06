package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

import java.util.*;

public abstract class Game {

    private Map<Integer, Creature> myCreaturesMap;
    private List<Integer> myActiveIndices;

    public Game () {
        myCreaturesMap = new HashMap<>();
        myActiveIndices = new ArrayList<>();
    }

    public Map<Integer, Creature> getCreaturesMap () { return myCreaturesMap; }
    public List<Integer> getActiveIndices () { return myActiveIndices; }

    public abstract void step (String command);

    public abstract String compile (String paragraph);

    public void executeCommand (String command, Creature current) {
        String[] commands = command.split(" ");
        current.setStatusStamp(false);
        Command input = new Command(commands[0]);
        Command result = input.recognize();
        if (result == null ) return;
        if (result.getNumArgs() == 2) result.execute(current, Integer.valueOf(commands[1]));
        else result.execute(current, 0);
    }

    public static boolean isNumeric (String string) {
        int intValue;
        if(string == null || string.equals("")) { return false; }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {}
        return false;
    }
}