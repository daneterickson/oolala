package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public class DarwinCanvasDisplay extends CanvasDisplay{

  private GameView myDarwinView;
  private ScreenDisplayComponents myDisplayComponents;

  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myDarwinView = gameView;
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
  @Override
  public void updateTurtleStatePanel() {

  }
}
