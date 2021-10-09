package oolala.view.canvas;
// Can't check styling yet
import javafx.scene.Node;
import javafx.scene.control.TextField;
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

  private int myNumLevels = 3;
  private int myLength = 60;
  private int myAngle = 30;

  private FractalView myFractalView;
  private FractalGame myFractalGame;
  private ScreenDisplayComponents myDisplayComponents;

  public FractalCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myFractalView = (FractalView) gameView;
    myFractalGame = (FractalGame) game;
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
    myFractalGame.initialize(myNumLevels, myLength, myAngle, 650, 100, 100); //GET RID OF IMAGINARY NUMBERS
    pane.getChildren().addAll(canvas, myFractalView.getMyCreaturePane());
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
    Node angle = myDisplayComponents.makeTextBoxWithLabel("AngleLabel", "AngleBox");
    Node angleButton = myDisplayComponents.makeButton("AngleButton", e-> temporary());
    Node length = myDisplayComponents.makeTextBoxWithLabel("LengthLabel", "LengthBox");
    Node lengthButton = myDisplayComponents.makeButton("LengthButton", e -> temporary());
    panel.getChildren().addAll(angle, angleButton, length, lengthButton);
    return panel;
  }

  private Node makeLevelPanel() {
    VBox panel = new VBox();
    panel.setId("LevelPanel");
    Node level = myDisplayComponents.makeTextBoxWithLabel("LevelLabel", "LevelBox");
    Node levelButton = myDisplayComponents.makeButton("LevelButton", e -> temporary());
    panel.getChildren().addAll(level, levelButton);
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
    Node homeLocationX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX");
    Node homeLocationY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY");
    Node setHomeLocation = myDisplayComponents.makeButton("SetHomeLocation", e -> updateHomeLocation((TextField)panel.lookup("#LocationX"), (TextField)panel.lookup("#LocationY")));
    panel.getChildren().addAll(homeLocationLabel, homeLocationX, homeLocationY, setHomeLocation);
    return panel;
  }

  private void temporary() {

  }

  private void updateHomeLocation (TextField x, TextField y) {
    myFractalGame.updateHome(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
  }

  @Override
  public void updateTurtleStatePanel() {

  }
}
