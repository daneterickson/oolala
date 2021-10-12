package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

import java.util.*;

/**
 * Game abstract class creates a game with the essential/basic components.
 *
 * @author Norah Tan
 */
public abstract class Game {

    private Map<Integer, Creature> myCreaturesMap;
    private List<Integer> myActiveIndices;

    private int myHomeX, myHomeY;

    /**
     * Constructor for Game that initializes:
     * myCreaturesMap is a HashMap that pairs a CreatureIndex with the Creature.
     * myActiveIndices is an ArrayList that stores all the indices of Creatures that are updated/active.
     */
    public Game () {
        myCreaturesMap = new HashMap<>();
        myActiveIndices = new ArrayList<>();
    }

    /**
     * Setter method that updates myHomeX and myHomeY
     * @param x is thd value for myHomeX
     * @param y is thd value for myHomeY
     */
    public void updateHome (int x, int y) {
        myHomeX = x;
        myHomeY = y;
    }

    /**
     * Getter method that gives myHomeX
     * @return myHomeX
     */
    public int getHomeX () { return myHomeX; }

    /**
     * Getter method that gives myHomeY
     * @return myHomeY
     */
    public int getHomeY () { return myHomeY; }

    /**
     * Getter method that returns myCreaturesMap, which assigns each Creature with an index starting from 1
     * @return myCreaturesMap
     */
    public Map<Integer, Creature> getCreaturesMap () { return myCreaturesMap; }

    /**
     * Getter method that returns the current indices of Creatures that have been changed
     * @return myActiveIndices
     */
    public List<Integer> getActiveIndices () { return myActiveIndices; }

    /**
     * An abstract method that executes exactly one step of the program and then return to the frontend
     *
     * @param command
     */
    public abstract void step (String command);

    /**
     * compile will read the entire paragraph the user inputs in the TextBox, then
     * clean the format (remove newlines, comments, extra whitespaces),
     * make each line contain only one step to execute, and
     * check whether all the commands are valid (if not, throw an error so that the frontend and catch it)
     *
     * @param paragraph is what the user types in the TextBox at the frontend
     * @return the compiled text
     */
    public String compile (String paragraph) {
        StringBuilder compiledText = new StringBuilder();
        String[] lines = paragraph.toLowerCase().split("\n");

        for (String command: lines) {
            if (command.equals("")) continue;
            String[] commands = command.split(" ");
            List<String> cleanedCommands = cleanCommands(commands);
            compiledText.append(breakCommands(cleanedCommands));
        }
        return compiledText.toString();
    }

    /**
     * A helper method that breaks one line of commands into lines, each of which contain only one step to execute
     *
     * @param commands is one line of commands taken from frontend's TextBox and split by whitespaces
     * @return a StringBuilder such that each line is a step
     */
    private StringBuilder breakCommands (List<String> commands) {
        StringBuilder ret = new StringBuilder();
        ListIterator<String> commandIterator = commands.listIterator();
        while (commandIterator.hasNext()) {
            String temp = commandIterator.next();
            if (temp.startsWith("#")) break;

            Command result = convertStringToCommand(temp);
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
        return ret;
    }

    /**
     * This method exactly executes one step/command using its corresponding arguments
     *
     * @param command is the command in its String format. Need to be converted into a Command object.
     * @param current the Creature that is executing the command
     */
    public void executeCommand (String command, Creature current) {
        String[] commands = command.split(" ");
        current.setStatusStamp(false);
        Command result = convertStringToCommand(commands[0]);
        switch (result.getNumArgs()) {
            case 1 -> result.execute(current); //ht,st,pd,pu,stamp
            case 2 -> result.execute(current, this); // for reset()
            case 3 -> result.execute(current, Double.valueOf(commands[1]), this); //bk,fk,lt,rt
            case 0 -> result.execute(); // go
//            case -1 -> //tell, indefinite number of arguments
        }
    }

    /**
     * This helper method converts a String into a Command
     * @param str is the command in String format
     * @return the corresponding Command class object, otherwise throw an exception
     */
    public Command convertStringToCommand (String str) {
        Command input = new Command(str);
        Command result = input.recognize();
        if (result == null) throw new NullPointerException();

        return result;
    }

    /**
     * A helper method that removes all the extra whitespaces in an array of commands
     * @param commands is an array of commands split by newline or whitespace
     * @return the List of cleaned commands
     */
    public List<String> cleanCommands (String[] commands) {
        List<String> cleanedCommands = new ArrayList<>(Arrays.asList(commands));
        ListIterator<String> itr = cleanedCommands.listIterator();
        while(itr.hasNext()) {
            if (itr.next().trim().equals("")) {
                itr.remove();
            }
        }
        return cleanedCommands;
    }

    /**
     * A static method that checks whether a String is numeric
     * e.g. isNumeric("123") = true but isNumeric("n0ra") = false
     *
     * @param string
     * @return
     */
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