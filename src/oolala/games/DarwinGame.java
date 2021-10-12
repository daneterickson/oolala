package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

import java.util.*;

/**
 * DarwinGame class creates a game equipped with components unique to Darwin Simulator application, aka. DarwinGame.
 *
 * @author Norah Tan
 */
public class DarwinGame extends Game {

    private List<Integer> myShuffledIndices;

    // myCreaturesMap = <CreatureIndex, Creature>
    // myInstructionsMap = <InstructionIndex, correspondingCommand>
    private Map<Integer, Command> myInstructionsMap;
    // myIndexMap = <CreatureIndex, currentInstructionIndex>
    private Map<Integer, Integer> myIndexMap;
    // myCommandArgs = <InstructionIndex, correspondingArgs> (If no args -> -1)
    private Map<Integer, Integer> myCommandArgs;

    private int myMaxX, myMaxY, myRadius;

    /**
     * Constructor that sets up the Darwin Game and initialized:
     * myInstructionsMap, which assigns an index (starting from 1) to each instruction
     * myIndexMap, which tells us for each Creature (key is the index of that Creature), (value is) the index of the current instruction to be executed next
     * myCommandArgs, which gives the corresponding argument for each instruction
     */
    public DarwinGame() {
        super();
        myInstructionsMap = new HashMap<>();
        myIndexMap = new HashMap<>();
        myCommandArgs = new HashMap<>();
    }

    /**
     * initialize DarwinGame with user inputs
     *
     * @param maxX is the width (x-axis) of the canvas
     * @param maxY is the height (y-axis) of the canvas
     * @param radius is the radius determining how far "nearby" is for a creature to sense its environment
     */
    public void initialize (int maxX, int maxY, int radius) {
        myMaxX = maxX;
        myMaxY = maxY;
        myRadius = radius;
    }

    /**
     * This method will initialize a Creature with specified location and type to myCreaturesMap in DarwinGame
     * @param type specifies the type of the new Creature
     * @param x is the initial x-location of the new Creature
     * @param y is the initial y-location of the new Creature
     */
    public void addCreature (String type, int x, int y) {
        Creature c = new Creature(x, y);
        c.setType(type);
        getCreaturesMap().put(getCreaturesMap().size() + 1, c);
        myIndexMap.put(myIndexMap.size() + 1, 1);
    }

    /**
     * Getter method that returns the radius
     * @return myRaduis
     */
    public double getRadius () { return myRadius; }

    /**
     * Setter method that sets the radius
     * @param radius
     */
    public void setRadius (int radius) { myRadius = radius; }

    /**
     * Getter method that returns the width of the canvas
     * @return myMaxX
     */
    public double getMaxX () { return myMaxX; }

    /**
     * Getter method that returns the height of the canvas
     * @return myMaxY
     */
    public double getMaxY () { return myMaxY; }

    /**
     * Getter method that returns the instruction map
     * @return myInstructionsMap
     */
    public Map<Integer, Command> getInstructionsMap () { return myInstructionsMap; };

    @Override
    public String compile (String paragraph) {
        for (String command : cleanCommands(paragraph.toLowerCase().split("\n"))) {
            String temp = command.split("#")[0];
            if (temp.equals("")) continue;

            List<String> cleanedCommands = cleanCommands(temp.split(" "));

            Command result = convertStringToCommand(cleanedCommands.get(0));
            myInstructionsMap.put(myInstructionsMap.size() + 1, result);
            if (cleanedCommands.size() == 1)
                myCommandArgs.put(myCommandArgs.size() + 1, -1);
            else
                myCommandArgs.put(myCommandArgs.size() + 1, Integer.valueOf(cleanedCommands.get(1)));
        }
        return null;
    }

    @Override
    public void step (String command) {
        myShuffledIndices = new ArrayList<>(getCreaturesMap().keySet());
        Collections.shuffle(myShuffledIndices);
        for (Integer index: myShuffledIndices) {
            Creature currentCreature = getCreaturesMap().get(index);
            if (currentCreature.getStatusInfection()) {
                currentCreature.setStatusInfection(false);
                continue;
            }
            Integer currentInstruction = myIndexMap.get(index);
            stepForEachCreature(currentCreature, currentInstruction, index);
        }
    }

    private void stepForEachCreature (Creature currentCreature, int currentInstruction, int index) {
        while (true) {
            Command result = myInstructionsMap.get(currentInstruction);
            int arg = myCommandArgs.get(currentInstruction);
            if (result.isAction()) {
                switch (result.getNumArgs()) {
                    case 3 -> result.execute(currentCreature, arg, this); //fd,rt,lt
                    case 2 -> result.execute(currentCreature, this); //infect,other non-action commands except go, ifrandom
                }
                myIndexMap.put(index, myIndexMap.get(index)+1);
                return;
            }
            else {
                boolean ifContinue = false;
                switch (result.getNumArgs()) {
                    case 0 -> ifContinue = result.execute();//go,ifrandom
                    case 2 -> ifContinue = result.execute(currentCreature, this);
                }
                if (ifContinue) currentInstruction = arg;
                else currentInstruction++;
                myIndexMap.put(index, currentInstruction);
            }
        }
    }
}
