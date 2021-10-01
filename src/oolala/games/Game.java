package oolala.games;


import commands.Command;
import oolala.creatures.Creature;
import org.junit.platform.commons.util.StringUtils;

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
        String[] commands = command.split(" ");
        current.setStatusStamp(false);
        Command input = new Command(commands[0]);
        Command result = input.recognize();
        if (result == null ) return;
        if (result.getNumArgs() == 2) result.execute(current, Integer.valueOf(commands[1]));
        else result.execute(current, 0);
    }

    public String compile (String paragraph) {
        StringBuilder ret = new StringBuilder();
        String[] lines = paragraph.toLowerCase().split("\n");

        for (String command: lines) {
            String[] commands = command.split(" ");
            int i = 0;
            while (i < commands.length) {
                if (commands[i].startsWith("#")) break;
                if (commands[i].equals("")) {
                    i++;
                    continue;
                }
                Command input = new Command(commands[i]);
                Command result = input.recognize();
                try {
                    if (result.getNumArgs() == 2) {
                        ret.append(String.format("%s %s\n", commands[i], commands[i+1]));
                        i += result.getNumArgs();
                    }
                    else if (result.getNumArgs() == 1) {
                        ret.append(String.format("%s\n", commands[i]));
                        i += result.getNumArgs();
                    }
                    else if (result.getNumArgs() == -1) {
                        ret.append(commands[i] + " ");
                        i++;
                        while (i < commands.length && isNumeric(commands[i])) {
                            ret.append(commands[i] + " ");
                            i++;
                        }
                        ret.append("\n");
                    }
                }
                catch (Exception e) {
                    System.out.println("Error: Not a valid command");
                }
            }

        }
        return ret.toString();
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