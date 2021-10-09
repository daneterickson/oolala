package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.FractalGame;
import oolala.games.Game;
import oolala.view.game.FractalView;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public class FractalCanvasDisplay extends CanvasDisplay {

  private int numLevels = 3;

  private FractalView myFractalView;
  private FractalGame myFractalGame;
  private ScreenDisplayComponents myDisplayComponents;

  public FractalCanvasDisplay(FractalView gameView, FractalGame game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myFractalView = gameView;
    myFractalGame = game;
    myDisplayComponents = components;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */
  @Override
  public Node makeCanvas () {
    BorderPane panel = new BorderPane();
    panel.setId("CanvasPanel");
    panel.setLeft(makeCanvasPanel());
    panel.setRight(makeFractalPanel());
    return panel;
  }

  @Override
  protected Node makeCanvasPanel () {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    myFractalGame.initialize(numLevels, 10,45, 650, 300, 2);
    pane.getChildren().addAll(canvas, myFractalView.getMyCreaturePane());
    return pane;
  }

  private Node makeFractalPanel () {
    VBox panel = new VBox();
    return panel;
  }
  @Override
  public void updateTurtleStatePanel() {

  }
}
