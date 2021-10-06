package oolala.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.games.TurtleGame;

public class DarwinScreenDisplay extends ScreenDisplay{

  public DarwinScreenDisplay(TurtleView gameView, TurtleGame game, String language, int startX, int startY) {
    super(gameView, game, language, startX, startY);
  }

  @Override
  protected Node makeCanvas () {
    BorderPane panel = new BorderPane();
    panel.setId("CanvasPanel");
    panel.setLeft(makeCanvasPanel());
    // TODO: Implement other panel views
    panel.setRight(makeDarwinPanel());
    return panel;
  }

  @Override
  protected Node makeCanvasPanel () {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    pane.getChildren().addAll(canvas, myGameView.getMyTurtlePane());
    return pane;
  }

  @Override
  protected void setCanvas (Game game) {

  }

  private Node makeDarwinPanel () {
    VBox panel = new VBox();
    return panel;
  }
}
