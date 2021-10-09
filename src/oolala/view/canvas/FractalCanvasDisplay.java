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
  private BorderPane myPane;

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
    myPane = new BorderPane();
    myPane.setId("CanvasPanel");
    myPane.setLeft(makeCanvasPanel());
    myPane.setRight(makeFractalPanel());
    return myPane;
  }

  @Override
  protected Node makeCanvasPanel () {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    // myFractalGame.initialize(myNumLevels, myLength, myAngle, 650, 100, 100); //GET RID OF IMAGINARY NUMBERS
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
    Node length = myDisplayComponents.makeTextBoxWithLabel("LengthLabel", "LengthBox");
    panel.getChildren().addAll(angle, length);
    return panel;
  }

  private Node makeLevelPanel() {
    VBox panel = new VBox();
    panel.setId("LevelPanel");
    Node level = myDisplayComponents.makeTextBoxWithLabel("LevelLabel", "LevelBox");
    panel.getChildren().addAll(level);
    return panel;
  }

  private Node makeImagePanel() {
    VBox panel = new VBox();
    panel.setId("ImagePanel");
    Node imageLabel = myDisplayComponents.makeLabel("LeafLabel");
    Node image = myDisplayComponents.makeButton("LeafImage", e -> temporary());
    panel.getChildren().addAll(imageLabel, image);
    return panel;
  }

  private Node makeHomeLocationPanel() {
    VBox panel = new VBox();
    panel.setId("HomeLocationPanel");
    Node homeLocationLabel = myDisplayComponents.makeLabel("HomeLocationLabel");
    Node homeLocationX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX");
    Node homeLocationY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY");
    Node renderButton = myDisplayComponents.makeButton("SetButton", e -> renderFractal((TextField)myPane.lookup("#LevelBox"),
            (TextField)myPane.lookup("#LengthBox"), (TextField)myPane.lookup("#AngleBox"), (TextField)myPane.lookup("#LocationX")
    , (TextField)myPane.lookup("#LocationY")));
    panel.getChildren().addAll(homeLocationLabel, homeLocationX, homeLocationY, renderButton);
    return panel;
  }

  private void renderFractal(TextField level, TextField length, TextField angle, TextField startX, TextField startY) {
    int levelNum = Integer.parseInt(level.getText());
    int lengthNum = Integer.parseInt(length.getText());
    int angleNum = Integer.parseInt(angle.getText());
    int xNum = Integer.parseInt(startX.getText());
    int yNum = Integer.parseInt(startY.getText());
    myFractalGame.initialize(levelNum, lengthNum, angleNum, xNum, yNum, 100);

  }

  private void temporary() {}

  @Override
  public void updateTurtleStatePanel() {

  }
}
