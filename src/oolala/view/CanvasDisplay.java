package oolala.view;

import javafx.scene.Node;
import oolala.games.Game;
import oolala.games.TurtleGame;

public abstract class CanvasDisplay {


  public CanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
  }

  protected abstract Node makeCanvas ();

  protected abstract Node makeCanvasPanel ();

  }
