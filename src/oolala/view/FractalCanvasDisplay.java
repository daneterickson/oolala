package oolala.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.FractalGame;
import oolala.games.Game;
import oolala.games.TurtleGame;

public class FractalCanvasDisplay extends CanvasDisplay {

  private GameView myFractalView;
  private ScreenDisplayComponents myDisplayComponents;

  public FractalCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myFractalView = gameView;
    myDisplayComponents = components;
  }

  @Override
  protected Node makeCanvas () {
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
//    pane.getChildren().addAll(canvas, myFractalView.getMyTurtlePane());
    return pane;
  }

  private Node makeFractalPanel () {
    VBox panel = new VBox();
    return panel;
  }
}
