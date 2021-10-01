package oolala.games;


import commands.Command;
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

  public void step (String command) {}

    public void executeCommand (String command, Creature current) {
        String[] commands = command.toLowerCase().split(" ");
        current.setStatusStamp(false);
        Command input = new Command(commands[0]);
        Command result = input.recognize();
        if (result == null ) return;
        if (result.getNumArgs() == 2) result.execute(current, Integer.valueOf(commands[1]));
        else result.execute(current, 0);
    }

    public String compile (String paragraph) {
        String[] lines = paragraph.split("\n");
        for (String command: lines) {

        }
        return null;
    }
}