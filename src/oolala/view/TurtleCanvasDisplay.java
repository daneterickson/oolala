package oolala.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.games.TurtleGame;

public class TurtleCanvasDisplay extends CanvasDisplay{

  private GameView myTurtleView;
  private ScreenDisplayComponents myDisplayComponents;

  public TurtleCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myTurtleView = gameView;
    myDisplayComponents = components;
  }

  @Override
  protected Node makeCanvas () {
    BorderPane panel = new BorderPane();
    panel.setId("CanvasPanel");
    panel.setLeft(makeCanvasPanel());
    panel.setRight(makeLogoPanel());
    return panel;
  }

  @Override
  protected Node makeCanvasPanel () {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    pane.getChildren().addAll(canvas, myTurtleView.getMyCreaturePane());
    return pane;
  }

  private Node makeLogoPanel () {
    VBox panel = new VBox();
    return panel;
  }
}
