package oolala.commands;

import oolala.creatures.Creature;
import oolala.games.Game;

/**
 * CommandBackward class that creates executable commands for "bk"
 *
 * @author Norah Tan
 */
public class CommandBackward extends Command {

  /**
   * Constructor that sets the name and sets the number of arguments it takes in for execution
   */
  public CommandBackward() {
    setName("bk");
    setNumArgs(3);
  }

  @Override
  public boolean execute(Creature current, double val, Game game) {
    current.move(-val, game);
    return false;
  }
}
