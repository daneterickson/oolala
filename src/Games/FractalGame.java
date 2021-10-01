package Games;

import Creatures.Creature;

import java.util.*;

public class FractalGame extends Game {

    private int myLevels;
    private double myLength;
    private int myRotation;
    private String myStartSymbol;
    private Map<String, String> myRulesMap;
    private Map<String, List<String>> myCommandsMap = new HashMap<>() {{
        put("F", Arrays.asList("pd", "fd LENGTH"));
        put("G", Arrays.asList("pu", "fd LENGTH"));
        put("A", Arrays.asList("pu", "bk LENGTH"));
        put("B", Arrays.asList("pd", "bk LENGTH"));
        put("+", Arrays.asList("rt ANGLE"));
        put("-", Arrays.asList("lt ANGLE"));
        put("X", Arrays.asList("stamp"));
    }};

    public FractalGame () {
        super();
        myRulesMap = new HashMap<>();
    }

    public void initialize (int numLevels, double length, int angle) {
        myLevels = numLevels;
        myLength = length;
        myRotation = angle;
        for (int i = 0; i < numLevels; i++) {
            getCreaturesMap().put(i, new Creature(0,0));
            //need to figure out the initial positions for these fractal creatures
        }
    }
    
    @Override
    public void step (String command) {
        // same as the Turtle game
    }

    public String compile (String paragraph) {
        String[] lines = paragraph.split("\n");
        for (String command: lines) {
            String [] commands = command.split(" ");
            if (commands[0].equals("start")) {
                myStartSymbol = commands[1];
            }
            else if (commands[0].equals("rule")) {
                myRulesMap.put(commands[1], commands[2]);
            }
            else if (commands[0].equals("set")) {
                myCommandsMap.put(commands[1], parseLogoCommand(commands[2]));
            }
        }
        String expandedCommands = expand();
        return expandedCommands;
    }


    public String expand () {
        StringBuilder expandedCommands = new StringBuilder();
        String currentSymbols = myStartSymbol;
        for (int i = 0; i < myLevels; i++) {
            expandedCommands.append(String.format("tell %d\n", i + 1));
            String newSymbols = currentSymbols;
            for (int j = 0; j < currentSymbols.length(); j++ ) {
                String symbol = String.valueOf(currentSymbols.charAt(j));
                newSymbols = newSymbols.replaceAll(symbol, myRulesMap.get(symbol));
                for (String command: myCommandsMap.get(symbol)) {
                    command = command.replace("ANGLE", String.valueOf(myRotation))
                            .replace("LENGTH", String.valueOf(myLength / (i + 1)));
                    expandedCommands.append(command+"\n");
                }
            }
        }
        return expandedCommands.toString();
    }

    public List<String> parseLogoCommand (String command) {
        String[] commands = command.substring(1, command.length() - 1).split(" ");
        if (commands.length == 3) {
            return Arrays.asList(commands[0], commands[1]+commands[2]);
        }
        else{
            return Arrays.asList(command);
        }
    }
}
