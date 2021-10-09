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

    public DarwinGame() {
        myInstructionsMap = new HashMap<>();
        myIndexMap = new HashMap<>();
        myCommandArgs = new HashMap<>();
    }

    public void initialize () {
        // put(CreatureIndex, 1) in myIndexMap when initialize every creature
        // initialize creature's type
    }

    @Override
    public void step (String command) {
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
                    case 2 -> result.execute(currentCreature, arg); //fd,rt,lt
                    case 3 -> result.execute(currentCreature, this); //infect,other non-action commands except go, ifrandom
                }
                myIndexMap.put(index, myIndexMap.get(index)+1);
                return;
            }
            else {
                boolean ifContinue = false;
                switch (result.getNumArgs()) {
                    case 0 -> ifContinue = result.execute();//go,ifrandom
                    case 3 -> ifContinue = result.execute(currentCreature, this);
                }
                if (ifContinue) currentInstruction = arg;
                else currentInstruction++;
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
                    myCommandArgs.put(myInstructionsMap.size() + 1, -1);
                else
                    myCommandArgs.put(myInstructionsMap.size() + 1, Integer.valueOf(commands[1]));
            }
            catch (Exception e) {
                System.out.println("Not a valid command");
                break;
            }

        }
        return null;
    }
}
