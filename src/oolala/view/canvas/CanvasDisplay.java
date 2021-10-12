package oolala.view.canvas;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.view.ScreenDisplayComponents;

/**
 * Superclass for the display of the Canvas for all the Game types
 *
 * @author: Evelyn Cupil-Garcia, Dane Erickson
 */
public abstract class CanvasDisplay {
  protected BorderPane myPane;
  protected VBox panel;
  protected ScreenDisplayComponents myDisplayComponents;
  protected boolean isPlaying = true;
  protected Timeline myAnimation;
  protected Slider animationSpeedSlider;


  /**
   * Constructor for CanvasDisplay to initialize a ScreenDisplayComponents object to create UI components
   *
   * @param components is the ScreenDisplayComponents that is used to create UI components
   */
  protected CanvasDisplay(ScreenDisplayComponents components, Timeline animation) {
    myDisplayComponents = components;
    myAnimation = animation;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */
  public Node setupCanvas () {
    myPane = new BorderPane();
    myPane.setId("CanvasPanel");
    myPane.setLeft(setupCanvasPanel());
    myPane.setRight(setupGamePanel());
    return myPane;
  }

  /**
   * Creates a StackPane where the window displays the drawing area
   * @return StackPane panel
   */
  protected StackPane setupCanvasStackPane() {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    pane.getChildren().add(canvas);
    return pane;
  }

  /**
   * Creates the Game Panel that is populated on the right side for each game and called in setupCanvas.
   * @return Node that contains the Game Panel.
   */
  protected abstract Node setupGamePanel();

  protected Node setupCanvasPanel () {
    StackPane myPane = setupCanvasStackPane();
    return addCreature(myPane);
  }

  /**
   * Creates the Home Location panel where the user can input an x and y and set as Home Location.
   * @return Returns a Node that contains the Home Location panel.
   */
  protected Node setupHomeLocationPanel() {
    panel = new VBox();
    panel.setId("HomeLocationPanel");
    Node homeLocationLabel = myDisplayComponents.makeLabel("HomeLocationLabel");
    Node homeLocationX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX");
    Node homeLocationY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY");
    panel.getChildren().addAll(homeLocationLabel, homeLocationX, homeLocationY, setupHomeButton());
    return panel;
  }

  /**
   * Method that checks if an input is only an integer by checking the edge cases of null, empty, or if it contains any
   * letters.
   * @param text
   * @return true if input is numeric, false if not numeric/null/empty
   */
  protected boolean isInputValid(TextField text) {
    String regex = "^[a-zA-Z]*$";
    if (text.getText().equals("") || text.getText().matches(regex) || text.getText() == null) {
      return false;
    }
    return true;
  }

  /**
   * Method that creates an Alert error message when input is not valid and displays the error.
   */
  protected void showErrorMessage() {
    Alert myError = myDisplayComponents.createErrorMessage("ValuesNull", "ValuesNullContent", Alert.AlertType.ERROR);
    myError.showAndWait();
  }

  /**
   * Method that set's up the home button used in the Home Location panel because it's functionality differs across game types.
   * @return Node containing the button.
   */
  protected abstract Node setupHomeButton();

  /**
   * Method that grabs the pane associated with each creature for each Game (ex. creatures for Darwin, turtle for Logo, etc.)
   * @param pane
   * @return Node containing the pane with it's creature
   */
  protected abstract Node addCreature(StackPane pane);

  /**
   * Method that updates the Turtle's State by displaying it's x and y
   */
  public abstract void updateTurtleStatePanel();

  /**
   * Method that returns playing status for Darwin.
   * @return
   */
  public boolean getPlayingStatus() {
    return isPlaying;
  }

  /**
   * Getter method to get the slider that adjusts the animation speed for Darwin.
   * Used in ScreenDisplay to apply the adjusted speed to the animation.
   *
   * @return animationSpeedSlider is the slider that adjusts the animation speed
   */
  public Slider getAnimationSpeedSlider () {
    return animationSpeedSlider;
  }

  }
