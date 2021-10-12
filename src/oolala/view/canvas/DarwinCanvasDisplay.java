package oolala.view.canvas;

import javafx.animation.Timeline;
import javafx.scene.Node;
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
  private DarwinGame myDarwinGame;

  /**
   * Constructor for DarwinCanvasDisplay to initialize a ScreenDisplayComponents object to create UI
   * components
   *
   * @param gameView   instance of DarwinView to create/display Darwin creatures
   * @param game       is the oolala.Creature object that is used as the model for this
   *                   DarwinCanvasDisplay class
   * @param components instance from superclass to create UI components for the ScreenDisplay
   * @param animation  is the application's animation
   */
  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components,
      Timeline animation) {
    super(components, animation);
    myDarwinGame = (DarwinGame) game;
    myDarwinView = (DarwinView) gameView;
  }


  @Override
  protected Node addCreature(StackPane pane) {
    pane.getChildren().add(myDarwinView.getMyCreaturePane());
    return pane;
  }


  @Override
  public Node setupGamePanel() {
    VBox panel = new VBox();
    panel.setId("DarwinPanel");
    panel.getChildren()
        .addAll(setupRadiusPanel(), setupHomeLocationPanel(), setupDarwinImagePanel(),
            animationSettingsPanel());
    return panel;
  }


  private Node setupRadiusPanel() {
    VBox panel = new VBox();
    panel.setId("RadiusPanel");
    Node radius = myDisplayComponents.makeTextBoxWithLabel("RadiusLabel", "RadiusBox");
    Node radiusButton = myDisplayComponents.makeButton("RadiusButton",
        e -> initializeGame((TextField) panel.lookup("#RadiusBox")));
    panel.getChildren().addAll(radius, radiusButton);
    return panel;
  }


  @Override
  protected Node setupHomeButton() {
    Node renderButton = myDisplayComponents.makeButton("SetHomeLocation", e ->
        updateHomeLocation((TextField) panel.lookup("#LocationX"),
            (TextField) panel.lookup("#LocationY")));
    return renderButton;
  }


  private void initializeGame(TextField radiusBox) {
    myDarwinGame.initialize((int) myPane.getLayoutBounds().getWidth(),
        (int) myPane.getLayoutBounds().getHeight(), Integer.parseInt(radiusBox.getText()));
  }

  /**
   * Creates the Darwin Image Panel to set a specific image to a Darwin Creature
   *
   * @return VBox panel containing the buttons to toggle between specific images to set and its
   * label
   */
  private Node setupDarwinImagePanel() {
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
  private Node setupDarwinImagePanelButtons() {
    HBox panel = new HBox();
    panel.setId("DarwinImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> makeCreature("cat"));
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> makeCreature("dog"));
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> makeCreature("turtle"));
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }


  private Node animationSettingsPanel() {
    VBox panel = new VBox();
    panel.setId("AnimationSettingsPanel");
    Node sliderLabel = myDisplayComponents.makeLabel("AnimationSpeed");
    animationSpeedSlider = myDisplayComponents.makeSlider("SpeedSlider", 1, 3, 5);
    Node playPauseButton = myDisplayComponents.makeButton("PlayPauseButton", e -> togglePlay());
    panel.getChildren().addAll(sliderLabel, animationSpeedSlider, playPauseButton);
    return panel;
  }

  private void togglePlay() {
    if (isPlaying) {
      myAnimation.stop();
    } else {
      myAnimation.play();
    }
    isPlaying = !isPlaying;
  }

  private void makeCreature(String type) {
    myDarwinGame.addCreature(type, myDarwinGame.getHomeX(), myDarwinGame.getHomeY());
    myDarwinView.drawCreature(myDarwinGame.getHomeX(), myDarwinGame.getHomeY(),
        myDarwinView.getMyCreatureMap().size() + 1, type);
  }


  private void updateHomeLocation(TextField x, TextField y) {
    if (!isInputValid(x) | !isInputValid(y)) {
      showErrorMessage();
    } else {
      myDarwinGame.updateHome(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }
  }

  /**
   * Method that is overrides but not implemented by Darwin since it is a Turtle method.
   */
  @Override
  public void updateTurtleStatePanel() {

  }
}
