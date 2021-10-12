package oolala.games;

import oolala.creatures.Creature;

import java.util.*;

/**
 * FractalGame class creates a game equipped with components unique to L-System Visualizer application, aka. FractalGame.
 *
 * @author Norah Tan
 */
public class FractalGame extends Game {

    private int myLevels;
    private double myLength;
    private int myRotation;
    private String myStartSymbol;
    private Map<String, String> myRulesMap;
    private Map<String, String> myCommandsMap = new HashMap<>() {{
        put("F", "pd\nfd LENGTH");
        put("G", "pu\nfd LENGTH");
        put("A", "pu\nbk LENGTH");
        put("B", "pd\nbk LENGTH");
        put("+", "rt ANGLE");
        put("-", "lt ANGLE");
        put("X", "stamp");
    }};

    /**
    /**
     * Constructor that sets up the Fractal Game and initialized:
     * myRulesMap, which tells us how each symbol is evolved into the next level
     */
    public FractalGame () {
        super();
        myRulesMap = new HashMap<>();
    }

    /**
     * initialize FractalGame with user inputs
     *
     * @param numLevels is the number of levels to expand the expression
     * @param length is the length to be used for moves
     * @param angle is the angle to be used for turns
     * @param startX is the specific x-location to start the generation
     * @param startY is the specific y-location to start the generation
     * @param levelSpan is the separation distance between every two levels
     */
    public void initialize (int numLevels, int length, int angle,
                            int startX, int startY, int levelSpan) {
        myLevels = numLevels;
        myLength = length;
        myRotation = angle;
        for (int i = 0; i < numLevels; i++) {
            Creature c = new Creature(startX,startY + i * levelSpan);
            c.changeOrientation(90);
            getCreaturesMap().put(i + 1, c);
        }
    }

    @Override
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
                String[] logoCommand = command.split("\"");
                myCommandsMap.put(commands[1], super.compile(logoCommand[1]));
            }
        }
        String expandedCommands = expand();
        return expandedCommands;
    }

    private String expand () {
        StringBuilder expandedCommands = new StringBuilder();
        String currentSymbols = myStartSymbol;
        for (int i = 0; i < myLevels; i++) {
            expandedCommands.append(String.format("tell %d\n", i + 1));
            String newSymbols = currentSymbols;
            for (int j = 0; j < currentSymbols.length(); j++ ) {
                String symbol = String.valueOf(currentSymbols.charAt(j));
                if (myRulesMap.containsKey(symbol)) {
                    newSymbols = newSymbols.substring(0, j) + myRulesMap.get(symbol) + newSymbols.substring(j+1);
                }

                String command = myCommandsMap.get(symbol);
                command = command.replace("ANGLE", String.valueOf(myRotation))
                        .replace("LENGTH", String.valueOf(myLength / (i + 1)));
                expandedCommands.append(command + "\n");
            }
            currentSymbols = newSymbols;
        }
        return expandedCommands.toString();
    }

    @Override
    public void step (String command) {
        String[] commands = command.toLowerCase().split(" ");

        if (commands[0].equals("tell")) {
            getActiveIndices().clear();
            int index = Integer.valueOf(commands[1]);
            getActiveIndices().add(index);
            getCreaturesMap().get(index);
        }
        else {
            Creature current = getCreaturesMap().get(getActiveIndices().get(0));
            executeCommand(command, current);
        }
    }
}
