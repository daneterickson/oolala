package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

public class TurtleGame extends Game {


    private int myHomeX, myHomeY;

    public TurtleGame (int originX, int originY) {
        super();
        myHomeX = originX;
        myHomeY = originY;
        getCreaturesMap().put(1, new Creature(myHomeX, myHomeY));
        getActiveIndices().add(1);
    }

    @Override
    public void step (String command) {
        String[] commands = command.toLowerCase().split(" ");

        if (commands[0].equals("tell")) {
            getActiveIndices().clear();
            for (int i = 1; i < commands.length; i++) {
                int index = Integer.valueOf(commands[i]);
                getActiveIndices().add(index);
                if (!getCreaturesMap().containsKey(index)) getCreaturesMap().put(index, new Creature(myHomeX, myHomeY));
            }
        }
        else {
            for (int index: getActiveIndices()) {
                Creature current = getCreaturesMap().get(index);
                executeCommand(command, current);
            }
        }
    }

    @Override
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



}
