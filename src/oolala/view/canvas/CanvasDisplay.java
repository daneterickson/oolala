package oolala.view.canvas;

import javafx.scene.Node;
import oolala.games.Game;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public abstract class CanvasDisplay {


  public CanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */
  public abstract Node makeCanvas ();

  protected abstract Node makeCanvasPanel ();

  public abstract void updateTurtleStatePanel();


  }
