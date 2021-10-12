package oolala.view.canvas;


import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import oolala.games.DarwinGame;
import oolala.games.Game;
import oolala.view.game.DarwinView;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

/**
 * Class that displays the Canvas for Darwin.
 *
 * @author: Evelyn Cupil-Garcia, Dane Erickson
 */
public class DarwinCanvasDisplay extends CanvasDisplay {

  private DarwinView myDarwinView;
  private Slider animationSpeedSlider;
  private DarwinGame myDarwinGame;

  /**
   * Constructor for DarwinCanvasDisplay to initialize a ScreenDisplayComponents object to create UI components
   * @param gameView instance of DarwinView to create/display Darwin creatures
   * @param game is the oolala.Creature object that is used as the model for this DarwinCanvasDisplay class
   * @param components instance from superclass to create UI components for the ScreenDisplay
   */
  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(components);
    myDarwinGame = (DarwinGame) game;
    myDarwinView = (DarwinView) gameView;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   *
   * @return BorderPane panel
   */

  @Override
  protected Node addCreature(StackPane pane) {
    pane.getChildren().add(myDarwinView.getMyCreaturePane());
    return pane;
  }

  /**
   * Creates the game panel for the Darwin game that is on the right side of the ScreenDisplay with the components of
   * Radius panel, Home Location panel, Darwin Image Panel, and Animation Settings Panel.
   *
   * @return VBox panel
   */
  @Override
  public Node setupGamePanel() {
    VBox panel = new VBox();
    panel.setId("DarwinPanel");
    panel.getChildren().addAll(setupRadiusPanel(), setupHomeLocationPanel(), setupDarwinImagePanel(), animationSettingsPanel());
    return panel;
  }

  /**
   * Creates the Radius Panel that is within the Game Panel to set the Radius of Darwin Creatures.
   *
   * @return VBox panel
   */
  private Node setupRadiusPanel() {
    VBox panel = new VBox();
    panel.setId("RadiusPanel");
    Node radius = myDisplayComponents.makeTextBoxWithLabel("RadiusLabel", "RadiusBox");
    Node radiusButton = myDisplayComponents.makeButton("RadiusButton", e -> initializeGame((TextField) panel.lookup("#RadiusBox")));
    panel.getChildren().addAll(radius, radiusButton);
    return panel;
  }

  /**
   * Creates the Home Button for Darwin Game where we update the Home Location
   *
   * @return Node of the Button renderButton
   */
  @Override
  protected Node setupHomeButton() {
    Node renderButton = myDisplayComponents.makeButton("SetHomeLocation", e ->
            updateHomeLocation((TextField) panel.lookup("#LocationX"), (TextField) panel.lookup("#LocationY")));
    return renderButton;
  }

  /**
   * Method that initializes the DarwinGame with a TextField that holds the radius
   *
   * @param radiusBox TextField that holds the value of the radius
   */
  private void initializeGame (TextField radiusBox){
    myDarwinGame.initialize((int) myPane.getLayoutBounds().getWidth(), (int) myPane.getLayoutBounds().getHeight(), Integer.parseInt(radiusBox.getText()));
  }

  /**
   * Creates the Darwin Image Panel to set a specific image to a Darwin Creature
   *
   * @return VBox panel containing the buttons to toggle between specific images to set and its label
   */
  private Node setupDarwinImagePanel () {
      VBox panel = new VBox();
      panel.setId("DarwinImagePanel");
      Node turtleImageLabel = myDisplayComponents.makeLabel("DarwinImageLabel");
      panel.getChildren().addAll(turtleImageLabel, setupDarwinImagePanelButtons());
      return panel;
  }

  /**
   * Creates Image Panel Buttons for the Darwin Image Panel
   *
   * @return HBox panel containing the buttons to toggle between images to set the Darwin Creature
   */
  private Node setupDarwinImagePanelButtons () {
    HBox panel = new HBox();
    panel.setId("DarwinImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> makeCreature("cat"));
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> makeCreature("dog"));
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> makeCreature("turtle"));
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }

  /**
   * Creates the Animation Settings Panel containing speed slider and the play/pause button.
   *
   * @return VBox panel containing speed slider, play/pause button, and its corresponding label.
   */
  private Node animationSettingsPanel () {
    VBox panel = new VBox();
    panel.setId("AnimationSettingsPanel");
    Node sliderLabel = myDisplayComponents.makeLabel("AnimationSpeed");
    animationSpeedSlider = myDisplayComponents.makeSlider("SpeedSlider", 1, 3, 5);
    Node playPauseButton = myDisplayComponents.makeButton("PlayPauseButton", e -> isPlaying = !isPlaying);
    panel.getChildren().addAll(sliderLabel, animationSpeedSlider, playPauseButton);
    return panel;
  }

  /**
   * Method that makes a Darwin Creature
   *
   * @param type the type of creature being created either cat, dog, or turtle.
   */
  private void makeCreature (String type) {
    myDarwinGame.addCreature(type, myDarwinGame.getHomeX(), myDarwinGame.getHomeY());
    myDarwinView.drawCreature(myDarwinGame.getHomeX(), myDarwinGame.getHomeY(), myDarwinView.getMyCreatureMap().size() + 1, type);
  }

  /**
   * Set's the home location of Darwin Creature from x and y TextFields
   *
   * @param x TextField that holds the x-value
   * @param y TextField that holds the y-value
   */
  private void updateHomeLocation (TextField x, TextField y){
    if (!isInputValid(x) | !isInputValid(y)) {
      showErrorMessage();
    } else {
      myDarwinGame.updateHome(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }
  }

  /**
   * Method that is overriden but not implemented by Darwin since it is a Turtle method.
   */
  @Override
    public void updateTurtleStatePanel () {

    }
  }
