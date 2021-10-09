package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Command {

    private List<Command> myCommandsList = new ArrayList<>();
    private String myName;
    private int myNumArgs;

    public Command () {}

    public Command (String name) {
        myName = name.toLowerCase();
        myCommandsList.add(new CommandForward());
        myCommandsList.add(new CommandBackward());
        myCommandsList.add(new CommandLeftTurn());
        myCommandsList.add(new CommandRightTurn());
        myCommandsList.add(new CommandPenDown());
        myCommandsList.add(new CommandPenUp());
        myCommandsList.add(new CommandShowTurtle());
        myCommandsList.add(new CommandHideTurtle());
        myCommandsList.add(new CommandReset());
        myCommandsList.add(new CommandStamp());
        myCommandsList.add(new CommandTell());
        myCommandsList.add(new CommandGo());
        myCommandsList.add(new CommandIfEnemy());
        myCommandsList.add(new CommandInfect());
        myCommandsList.add(new CommandIfRandom());
        myCommandsList.add(new CommandIfEmpty());
        myCommandsList.add(new CommandIfSame());
        myCommandsList.add(new CommandIfWall());
    }

    public String getName () { return myName; }
    public void setName (String name) { myName = name; }
    public int getNumArgs () { return myNumArgs; }
    public void setNumArgs (int num) { myNumArgs = num; }

    public Command recognize () {
        for (Command cd: myCommandsList)  {
            if (cd.getName().equals(myName)) {
                return cd;
            }
        }
        return null;
    }


    public boolean execute () { return false; }
    public boolean execute (Creature current) { return false; }
    public boolean execute (Creature current, int val, Game game) { return false; }
    public boolean execute (Creature current, Game game) { return false; }

    public boolean isAction () { return false; }

    @Override
    public boolean equals (Object obj) {
        return myName.equals(((Command) obj).getName());
    }


}
