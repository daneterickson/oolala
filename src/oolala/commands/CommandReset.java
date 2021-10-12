package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;


/**
 * CommandReset class that creates executable commands for "home"
 *
 * @author Norah Tan
 */
public class CommandReset extends Command {

  /**
   * Constructor that sets the name and sets the number of arguments it takes in for execution
   */
  public CommandReset() {
    setName("home");
    setNumArgs(2);
  }

  @Override
  public boolean execute(Creature current, Game game) {
    current.reset(game);
    return false;
  }

}
