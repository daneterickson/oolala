package oolala.games;

import oolala.commands.Command;
import oolala.creatures.Creature;

import java.util.*;

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

    public DarwinGame() {
        myInstructionsMap = new HashMap<>();
        myIndexMap = new HashMap<>();
        myCommandArgs = new HashMap<>();
    }

    public void initialize (int maxX, int maxY, int radius) {
        myMaxX = maxX;
        myMaxY = maxY;
        myRadius = radius;
    }

    public void addCreature (String type, int x, int y) {
        Creature c = new Creature(x, y);
        c.setType(type);
        getCreaturesMap().put(getCreaturesMap().size() + 1, c);
        myIndexMap.put(myIndexMap.size() + 1, 1);
    }

    public double getRadius () { return myRadius; }
    public void setRadius (int radius) { myRadius = radius; }
    public double getMaxX () { return myMaxX; }
    public double getMaxY () { return myMaxY; }

    @Override
    public void step (String command) {
        myShuffledIndices = new ArrayList<>(getCreaturesMap().keySet());
//        getActiveIndices().clear();
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
            System.out.println(currentInstruction + " " + index);
            int arg = myCommandArgs.get(currentInstruction);
            if (result.isAction()) {
                switch (result.getNumArgs()) {
                    case 3 -> result.execute(currentCreature, arg, this); //fd,rt,lt
                    case 2 -> result.execute(currentCreature, this); //infect,other non-action commands except go, ifrandom
                }
//                getActiveIndices().add(index);
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

    @Override
    public String compile (String paragraph) {
        for (String command : paragraph.toLowerCase().split("\n")) {
            String temp = command.split("#")[0];
            if (temp.equals("")) continue;
            String[] commands = temp.split(" ");

            Command input = new Command(commands[0]);
            Command result = input.recognize();
            try {
                myInstructionsMap.put(myInstructionsMap.size() + 1, result);
                if (commands.length == 1)
                    myCommandArgs.put(myCommandArgs.size() + 1, -1);
                else
                    myCommandArgs.put(myCommandArgs.size() + 1, Integer.valueOf(commands[1]));
            }
            catch (Exception e) {
                System.out.println("Not a valid command");
                break;
            }

        }
        return null;
    }
}
