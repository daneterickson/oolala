package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Command class that creates any executable commands
 *
 * @author Norah Tan
 */
public class Command {

  private List<Command> myCommandsList = new ArrayList<>();
  private String myName;
  private int myNumArgs;

  /**
   * Default constructor
   */
  public Command() {
  }

  /**
   * Constructor for a Command object such that we can later evaluate whether it is one of the valid
   * commands for each application
   *
   * @param name is the name for the command
   */
  public Command(String name) {
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

  /**
   * Getter method that returns the name of the Command
   *
   * @return myName
   */
  public String getName() {
    return myName;
  }

  /**
   * Setter method that sets the name of the Command
   *
   * @param name
   */
  public void setName(String name) {
    myName = name;
  }

  /**
   * Getter method that returns the number of arguments needed for the Command
   *
   * @return myNumArgs
   */
  public int getNumArgs() {
    return myNumArgs;
  }

  /**
   * Setter method that sets the number of arguments needed for the Command
   *
   * @param num
   */
  public void setNumArgs(int num) {
    myNumArgs = num;
  }

  /**
   * This method will check whether the current Command's name gives a valid command by comparing it
   * with an instance from every children Command classes.
   *
   * @return null if the Command is not valid; otherwise return its corresponding instance from ths
   * child Command class
   */
  public Command recognize() {
    for (Command cd : myCommandsList) {
      if (cd.equals(this)) {
        return cd;
      }
    }
    return null;
  }

  /**
   * One of the overloaded classes for command execution
   *
   * @return whether the Command return true or false (will be overridden in corresponding children
   * Command classes)
   */
  public boolean execute() {
    return false;
  }

  /**
   * One of the overloaded classes for command execution
   *
   * @param current is the Creature the user wants to execute on
   * @return whether the Command return true or false (will be overridden in corresponding children
   * Command classes)
   */
  public boolean execute(Creature current) {
    return false;
  }

  /**
   * One of the overloaded classes for command execution
   *
   * @param current is the Creature the user wants to execute on
   * @param game    the current game that gives information about radius, canvas size, etc.
   * @return whether the Command return true or false (will be overridden in corresponding children
   * Command classes)
   */
  public boolean execute(Creature current, Game game) {
    return false;
  }

  /**
   * One of the overloaded classes for command execution
   *
   * @param current is the Creature the user wants to execute on
   * @param val     is the value needed for execution, e.g. distance to move or angle to rotate
   * @param game    the current game that gives information about radius, canvas size, etc.
   * @return whether the Command return true or false (will be overridden in corresponding children
   * Command classes)
   */
  public boolean execute(Creature current, double val, Game game) {
    return false;
  }

  /**
   * Getter method that returns whether the Command is an action
   *
   * @return
   */
  public boolean isAction() {
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    return myName.equals(((Command) obj).getName());
  }


}
