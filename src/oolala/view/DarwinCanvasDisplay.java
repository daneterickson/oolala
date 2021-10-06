package oolala.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.DarwinGame;
import oolala.games.Game;
import oolala.games.TurtleGame;

public class DarwinCanvasDisplay extends CanvasDisplay{

  private GameView myDarwinView;
  private ScreenDisplayComponents myDisplayComponents;

  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myDarwinView = gameView;
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
//    pane.getChildren().addAll(canvas, myDarwinView.getMyTurtlePane());
    return pane;
  }

  private Node makeLogoPanel () {
    VBox panel = new VBox();
    return panel;
  }
}
