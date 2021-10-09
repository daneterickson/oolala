package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

import java.util.*;

public abstract class Game {

    private Map<Integer, Creature> myCreaturesMap;
    private List<Integer> myActiveIndices;

    private int myHomeX, myHomeY;

    public Game () {
        myCreaturesMap = new HashMap<>();
        myActiveIndices = new ArrayList<>();
    }

    public void updateHome (int x, int y) {
        myHomeX = x;
        myHomeY = y;
    }

    public int getHomeX () { return myHomeX; }
    public int getHomeY () { return myHomeY; }

    public Map<Integer, Creature> getCreaturesMap () { return myCreaturesMap; }
    public List<Integer> getActiveIndices () { return myActiveIndices; }

    public abstract void step (String command);

    public String compile (String paragraph) {
        StringBuilder compiledText = new StringBuilder();
        String[] lines = paragraph.toLowerCase().split("\n");

        for (String command: lines) {
            String[] commands = command.split(" ");
            compiledText.append(breakCommands(commands));
        }
        return compiledText.toString();
    }

    private StringBuilder breakCommands (String[] commands) {
        StringBuilder ret = new StringBuilder();
        ListIterator<String> commandIterator = Arrays.asList(commands).listIterator();
        while (commandIterator.hasNext()) {
            String temp = commandIterator.next();
            if (temp.startsWith("#")) break;
            if (temp.equals("")) continue;

            Command input = new Command(temp);
            Command result = input.recognize();

            try {
                switch (result.getNumArgs()) {
                    case 3 -> ret.append(String.format("%s %s \n", temp, commandIterator.next()));
                    case 1, 2 -> ret.append(String.format("%s \n", temp));//
                    case -1 -> {
                        ret.append(String.format("%s ", temp));
                        while (commandIterator.hasNext()) {
                            temp = commandIterator.next();
                            if (isNumeric(temp)) {
                                ret.append(String.format("%s ", temp));
                            }
                            else {
                                commandIterator.previous();
                                break;
                            }
                        }
                        ret.append("\n");
                        break;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Error: Not a valid command");
                break;
            }
        }
        return ret;
    }

    public void executeCommand (String command, Creature current) {
        String[] commands = command.split(" ");
        current.setStatusStamp(false);
        Command input = new Command(commands[0]);
        Command result = input.recognize();
        switch (result.getNumArgs()) {
            case 1 -> result.execute(current); //ht,st,pd,pu,stamp
            case 2 -> result.execute(current, this); // for reset()
            case 3 -> result.execute(current, Integer.valueOf(commands[1]), this); //bk,fk,lt,rt
            case 0 -> result.execute(); // go
//            case -1 -> //tell, indefinite number of arguments
        }
    }

    private static boolean isNumeric (String string) {
        if (string == null || string.equals("")) { return false; }

        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}