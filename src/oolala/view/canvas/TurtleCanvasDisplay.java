package oolala.view.canvas;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import oolala.games.Game;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

import oolala.view.game.TurtleView;

public class TurtleCanvasDisplay extends CanvasDisplay{

  private TurtleView myTurtleView;
  private Label turtleStateX;
  private Label turtleStateY;
  private Slider lineWidthSlider;
  private Game myTurtleGame;

  public TurtleCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components, Timeline animation) {
    super(components, animation);
    myTurtleView = (TurtleView) gameView;
    myTurtleGame = game;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */

  @Override
  protected Node addCreature(StackPane panel) {
    panel.getChildren().addAll(myTurtleView.getMyCreaturePane());
    return panel;
  }

  @Override
  public Node setupGamePanel () {
    VBox panel = new VBox();
    panel.setId("LogoPanel");
    panel.getChildren().addAll(setupPenThicknessPanel(), setupHomeLocationPanel(), setupTurtleImagePanel(), setupTurtleStatePanel());
    return panel;
  }

  private Node setupPenThicknessPanel () {
    VBox panel = new VBox();
    panel.setId("PenThicknessPanel");
    Node sliderLabel = myDisplayComponents.makeLabel("PenThickness");
    lineWidthSlider = myDisplayComponents.makeSlider("PenSlider", 1, 1, 4);
    panel.getChildren().addAll(sliderLabel, lineWidthSlider);
    return panel;
  }

  /**
   * Getter method to get the slider that changes the line width so ScreenDisplay can access the
   * slider and change the line width each command.
   *
   * @return lineWidthSlider
   */
  public Slider getLineWidthSlider () {
    return lineWidthSlider;
  }

  @Override
  protected Node setupHomeButton() {
    Node renderButton = myDisplayComponents.makeButton("SetHomeLocation", e -> updateHomeLocation((TextField)
            panel.lookup("#LocationX"), (TextField)panel.lookup("#LocationY")));
    return renderButton;
  }

  private Node setupTurtleImagePanel() {
    VBox panel = new VBox();
    panel.setId("TurtleImagePanel");
    Node turtleImageLabel = myDisplayComponents.makeLabel("TurtleImageLabel");
    panel.getChildren().addAll(turtleImageLabel, setupTurtleImagePanelButtons());
    return panel;
  }

  private Node setupTurtleImagePanelButtons() {
    HBox panel = new HBox();
    panel.setId("TurtleImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> myTurtleView.setCreatureImage("cat"));
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> myTurtleView.setCreatureImage("dog"));
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> myTurtleView.setCreatureImage("turtle"));
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }

  private Node setupTurtleStatePanel() {
    VBox panel = new VBox();
    panel.setId("TurtleStatePanel");
    Node turtleStateTitle = myDisplayComponents.makeLabel("TurtleStateTitle");
    turtleStateX = myDisplayComponents.makeLabel("TurtleStateX");
    turtleStateY = myDisplayComponents.makeLabel("TurtleStateY");
    panel.getChildren().addAll(turtleStateTitle, turtleStateX, turtleStateY);
    return panel;
  }

  @Override
  public void updateTurtleStatePanel() {
    turtleStateX.setText("x: " + myTurtleView.getX());
    turtleStateY.setText("y: " + myTurtleView.getY());
  }
  /**
   * Getter method to get the string from the current turtle X position
   *
   * @return turtleStateX.getText() is the string of current position X
   */
  public Label getTurtleStateXLabel () {
    return turtleStateX;
  }

  /**
   * Getter method to get the string from the current turtle Y position
   *
   * @return turtleStateX.getText() is the string of current position Y
   */
  public Label getTurtleStateYLabel () {
    return turtleStateY;
  }


  private void updateHomeLocation (TextField x, TextField y) {
    if (!isInputValid(x) | !isInputValid(y)) {
      showErrorMessage();
    } else {
      myTurtleGame.updateHome(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }
  }
}
