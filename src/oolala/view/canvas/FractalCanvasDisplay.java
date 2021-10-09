package oolala.view.canvas;
// Can't check styling yet
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public class FractalCanvasDisplay extends CanvasDisplay {

  private GameView myFractalView;
  private ScreenDisplayComponents myDisplayComponents;

  public FractalCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myFractalView = gameView;
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
//    pane.getChildren().addAll(canvas, myFractalView.getMyTurtlePane());
    return pane;
  }

  private Node makeFractalPanel () {
    VBox panel = new VBox();
    panel.setId("FractalPanel");
    panel.getChildren().addAll(makeAngleAndLengthPanel(), makeLevelPanel(), makeHomeLocationPanel(), makeImagePanel());
    return panel;
  }

  private Node makeAngleAndLengthPanel() {
    VBox panel = new VBox();
    panel.setId("AngleAndLevelPanel");
    Node angle = myDisplayComponents.makeTextBoxWithLabel("AngleLabel", "AngleBox", e -> temporary());
    Node length = myDisplayComponents.makeTextBoxWithLabel("LengthLabel", "LengthBox", e -> temporary());
    panel.getChildren().addAll(angle, length);
    return panel;
  }

  private Node makeLevelPanel() {
    VBox panel = new VBox();
    panel.setId("LevelPanel");
    Node level = myDisplayComponents.makeTextBoxWithLabel("LevelLabel", "LevelBox", e -> temporary());
    panel.getChildren().add(level);
    return panel;
  }

  private Node makeImagePanel() {
    VBox panel = new VBox();
    panel.setId("ImagePanel");
    Node imageLabel = myDisplayComponents.makeLabel("LeafLabel");
    Node image = myDisplayComponents.makeButton("LeafImage", e -> temporary());
    panel.getChildren().addAll(image);
    return panel;
  }

  private Node makeHomeLocationPanel() {
    VBox panel = new VBox();
    panel.setId("HomeLocationPanel");
    Node homeLocationLabel = myDisplayComponents.makeLabel("HomeLocationLabel");
    Node homeLocationX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX", e -> temporary());
    Node homeLocationY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY", e -> temporary());
    panel.getChildren().addAll(homeLocationLabel, homeLocationX, homeLocationY);
    return panel;
  }

  private void temporary() {

  }
  @Override
  public void updateTurtleStatePanel() {

  }
}
