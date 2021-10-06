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
                if (result.getNumArgs() == 2) {
                    ret.append(String.format("%s %s \n", temp, commandIterator.next()));
                }
                else if (result.getNumArgs() == 1) {
                    ret.append(String.format("%s \n", temp));
                }
                else if (result.getNumArgs() == -1) {
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
        if (result == null ) return;
        if (result.getNumArgs() == 2) result.execute(current, Integer.valueOf(commands[1]));
        else result.execute(current, 0);
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